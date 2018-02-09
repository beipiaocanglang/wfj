package com.wdzsj.mgr.castor;

import java.util.List;
import com.wdzsj.cmn.base.BeanManager;
import com.wdzsj.mgr.dao.sys.DictDao;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;
import com.wdzsj.mgr.castor.util.CastorUtils;
import com.wdzsj.mgr.castor.util.EntityResource;
import com.wdzsj.mgr.entity.sys.Dict;

@Component
public class DicDataCastor implements ICastor {
	static Log logger = LogFactory.getLog(DicDataCastor.class);
	
	public void cast(EntityResource entityResource) {
		DicMetaData dicData = CastorUtils.getInstance().loadByCastor(DicMetaData.class, entityResource.getConfMappingLocation(), entityResource.getConfLocation());
		
		/**用户**/
		updateDictList(dicData.getDictList());
	}
	
	private void updateDictList(List<Dict> DictList){
		DictDao dictDao = BeanManager.getBean("dictDao", DictDao.class);
		for (Dict dict : DictList) {
			try{
				Dict entity = dictDao.getById(dict.getId());
				if(null == entity) {
					dictDao.insert(dict);
				} else {
					dictDao.update(dict);
				}
			}catch(Exception e){
				logger.error(e.getMessage(), e);
			}
		}
	}
}
