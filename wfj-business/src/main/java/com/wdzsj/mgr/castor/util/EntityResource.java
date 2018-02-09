package com.wdzsj.mgr.castor.util;

import java.io.Serializable;

public class EntityResource implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String dataType;
	private String bizType;
	private boolean enable;
	private boolean async;
	private String confLocation;
	private String confMappingLocation;
	private String implClazz;
	
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("EntityResource [")
		  .append("dataType:").append(dataType).append(", ")
		  .append("bizType:").append(bizType).append(", ")
		  .append("enable:").append(enable).append(", ")
		  .append("async:").append(async).append(", ")
		  .append("confLocation:").append(confLocation).append(", ")
		  .append("confMappingLocation:").append(confMappingLocation).append(", ")
		  .append("implClazz:").append(implClazz)
		  .append("]");
		return sb.toString();
	}
	
	public String getDataType() {
		return dataType;
	}
	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	public String getBizType() {
		return bizType;
	}
	public void setBizType(String bizType) {
		this.bizType = bizType;
	}

	public boolean isEnable() {
		return enable;
	}
	public void setEnable(boolean enable) {
		this.enable = enable;
	}
	
	public String getConfLocation() {
		return confLocation;
	}
	public void setConfLocation(String confLocation) {
		this.confLocation = confLocation;
	}
	
	public String getConfMappingLocation() {
		return confMappingLocation;
	}
	public void setConfMappingLocation(String confMappingLocation) {
		this.confMappingLocation = confMappingLocation;
	}

	public String getImplClazz() {
		return implClazz;
	}
	public void setImplClazz(String implClazz) {
		this.implClazz = implClazz;
	}

	public boolean isAsync() {
		return async;
	}
	public void setAsync(boolean async) {
		this.async = async;
	}
}
