package com.wdzsj.mgr.entity.pvuv;

import com.wdzsj.cmn.base.BaseEntity;

public class Pvuv  extends BaseEntity {
	
	private Long pv;
	private Long uv;
	private String  ip;

	public Pvuv() {
		super();
	}
	public Long getPv() {
		return pv;
	}
	public void setPv(Long pv) {
		this.pv = pv;
	}
	public Long getUv() {
		return uv;
	}
	public void setUv(Long uv) {
		this.uv = uv;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	
	@Override
	public String toString() {
		return "Pvuv [pv=" + pv + ", uv=" + uv + ", ip=" + ip + "]";
	}
}
