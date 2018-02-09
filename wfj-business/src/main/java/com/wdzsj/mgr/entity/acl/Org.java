package com.wdzsj.mgr.entity.acl;

import com.wdzsj.cmn.base.BaseEntity;

public class Org extends BaseEntity {
	
	private static final long serialVersionUID = 1L;
	
	//上级部门   
	private java.lang.Long parId;
	//部门名称   
	private java.lang.String name;
	//图片   
	private java.lang.String imag;
	//显示排序   
	private java.lang.Long seq;
	
	public void setParId(java.lang.Long value) {
		this.parId = value;
	}
	
	public java.lang.Long getParId() {
		return this.parId;
	}
	
	public void setName(java.lang.String value) {
		this.name = value;
	}
	
	public java.lang.String getName() {
		return this.name;
	}
	
	public void setImag(java.lang.String value) {
		this.imag = value;
	}
	
	public java.lang.String getImag() {
		return this.imag;
	}
	
	public void setSeq(java.lang.Long value) {
		this.seq = value;
	}
	
	public java.lang.Long getSeq() {
		return this.seq;
	}
}

