package com.wdzsj.mgr.listener;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.web.context.ConfigurableWebApplicationContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.XmlWebApplicationContext;
import com.wdzsj.cmn.base.BeanManager;
import com.wdzsj.mgr.castor.CastorFactory;
import com.wdzsj.mgr.castor.ICastor;
import com.wdzsj.mgr.castor.util.CastorUtils;
import com.wdzsj.mgr.castor.util.EntityResource;
import com.wdzsj.mgr.castor.util.MginfConstants;
import com.wdzsj.mgr.castor.util.Resources;

public class MginfServletContextListener implements ServletContextListener {
	static Log logger = LogFactory.getLog(MginfServletContextListener.class);

	private static ScheduledExecutorService executor = null;

	@Override
	public void contextInitialized(ServletContextEvent event) {
		executor = Executors.newScheduledThreadPool(5);

		String contextConfigLocation = event.getServletContext().getInitParameter("contextConfigLocation");
		ConfigurableWebApplicationContext applicationCtx = BeanUtils.instantiateClass(XmlWebApplicationContext.class);
		applicationCtx.setServletContext(event.getServletContext());
		applicationCtx.setConfigLocation(contextConfigLocation);
		applicationCtx.refresh();
		event.getServletContext().setAttribute(WebApplicationContext.class.getName() + ".ROOT", applicationCtx);

		BeanManager.setApplicationCtx(applicationCtx);
		BeanManager.setServletCtx(event.getServletContext());

		initResourceData();
	}

	@Override
	public void contextDestroyed(ServletContextEvent event) {

	}

	private void initResourceData() {
		try {
			List<Resources> resourcesList = CastorUtils.getInstance().listByCastor(Resources.class,
					MginfConstants.DATA_MAPPING_FILE, MginfConstants.DATA_CONF_FILE);
			if (null != resourcesList) {
				for (Resources resources : resourcesList) {
					List<EntityResource> entityResourceList = resources.getEntityResourceList();

					for (EntityResource entityResource : entityResourceList) {
						logger.info(entityResource.toString());
						if (!entityResource.isEnable())
							continue;

						ICastor castor = null;
						try {
							castor = CastorFactory.getFactory().getCastor(entityResource.getImplClazz());
						} catch (Exception e) {
							logger.error(e.getMessage(), e);
						}
						if (null == castor)
							continue;

						// 同步
						if (!entityResource.isAsync()) {
							try {
								castor.cast(entityResource);
							} catch (Exception e) {
								logger.error(e.getMessage(), e);
							}
						} else {// 异步
							executor.submit(new CastorRunnable(castor, entityResource));
						}
					}
				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

	private class CastorRunnable implements Runnable {
		private ICastor castor = null;
		private EntityResource entityResource = null;

		private CastorRunnable(ICastor castor, EntityResource entityResource) {
			this.castor = castor;
			this.entityResource = entityResource;
		}

		@Override
		public void run() {
			try {
				castor.cast(entityResource);
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
			}
		}
	}
}
