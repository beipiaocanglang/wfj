package com.wdzsj.cmn.base;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

public class BaseEntity {

	// id 
	private Long id;
	
	// 创建时间 
	private Date creatTm;
	
	// 更新时间
	private Date updtTm;
	
	// 0:不可用 1:可用 
	private Integer enabFlag;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getCreatTm() {
		return creatTm;
	}

	public void setCreatTm(Date creatTm) {
		this.creatTm = creatTm;
	}

	public Date getUpdtTm() {
		return updtTm;
	}

	public void setUpdtTm(Date updtTm) {
		this.updtTm = updtTm;
	}

	public Integer getEnabFlag() {
		return enabFlag;
	}

	public void setEnabFlag(Integer enabFlag) {
		this.enabFlag = enabFlag;
	}

}
