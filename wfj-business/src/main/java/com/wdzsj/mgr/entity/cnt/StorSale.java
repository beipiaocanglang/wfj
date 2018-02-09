package com.wdzsj.mgr.entity.cnt;

import com.wdzsj.cmn.base.BaseEntity;

public class StorSale extends BaseEntity {
	
	private static final long serialVersionUID = 1L;
	
	//广场ID   
	private Long plazaId;
	//店铺ID   
	private Long storId;
	//销售额   
	private String saleVal;

	public void setPlazaId(Long value) {
		this.plazaId = value;
	}
	
	public Long getPlazaId() {
		return this.plazaId;
	}
	
	public void setStorId(Long value) {
		this.storId = value;
	}
	
	public Long getStorId() {
		return this.storId;
	}
	
	public void setSaleVal(String value) {
		this.saleVal = value;
	}
	
	public String getSaleVal() {
		return this.saleVal;
	}
}

