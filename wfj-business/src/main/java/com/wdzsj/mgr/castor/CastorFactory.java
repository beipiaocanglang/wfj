package com.wdzsj.mgr.castor;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class CastorFactory {
	static Log logger = LogFactory.getLog(CastorFactory.class);
	
	private static CastorFactory factory = new CastorFactory();
	
	public static CastorFactory getFactory(){
		return factory;
	}
	
	public ICastor getCastor(String implClazz){
		ICastor castor = null;
		
		try {
			castor = (ICastor)Class.forName(implClazz).newInstance();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return castor;
	}
}
