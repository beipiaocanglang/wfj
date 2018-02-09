package com.wdzsj.mgr.entity.acl;

import com.wdzsj.cmn.base.BaseEntity;

public class OrgUser extends BaseEntity {
	
	private static final long serialVersionUID = 1L;
	
	//组织架构id   
	private java.lang.Long orgId;
	//用户id   
	private java.lang.Long userId;
	//操作用户   
	private java.lang.String actUser;

	public void setOrgId(java.lang.Long value) {
		this.orgId = value;
	}
	
	public java.lang.Long getOrgId() {
		return this.orgId;
	}
	
	public void setUserId(java.lang.Long value) {
		this.userId = value;
	}
	
	public java.lang.Long getUserId() {
		return this.userId;
	}
	
	public void setActUser(java.lang.String value) {
		this.actUser = value;
	}
	
	public java.lang.String getActUser() {
		return this.actUser;
	}
}

