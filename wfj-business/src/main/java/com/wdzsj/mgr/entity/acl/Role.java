package com.wdzsj.mgr.entity.acl;

import com.wdzsj.cmn.base.BaseEntity;

public class Role extends BaseEntity {
	
    //角色名称       
	private String name;
    //角色编码       
	private String code;
	//描述
	private String description;

	public Role(){
	}

	public void setName(String value) {
		this.name = value;
	}
	
	public String getName() {
		return this.name;
	}
	public void setCode(String value) {
		this.code = value;
	}
	
	public String getCode() {
		return this.code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}

