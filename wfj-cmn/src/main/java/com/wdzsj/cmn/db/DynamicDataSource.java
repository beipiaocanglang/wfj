package com.wdzsj.cmn.db;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import com.wdzsj.cmn.holder.DataSourceContextHolder;

public class DynamicDataSource extends AbstractRoutingDataSource{

	@Override
	protected Object determineCurrentLookupKey() {
		return DataSourceContextHolder.getDataSourceType(); 
	}
	
}
