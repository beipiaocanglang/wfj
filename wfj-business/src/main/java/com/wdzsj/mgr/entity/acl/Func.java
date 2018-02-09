package com.wdzsj.mgr.entity.acl;

import com.wdzsj.cmn.base.BaseEntity;

public class Func extends BaseEntity {
	
    //功能名称     
	private String name;
    //权限级别      
	private Integer levels;
    //模块id      
	private Long mdulId;
	//url
	private String url;

	public Func(){
	}

	public void setName(String value) {
		this.name = value;
	}
	
	public String getName() {
		return this.name;
	}
	public void setLevels(Integer value) {
		this.levels = value;
	}
	
	public Integer getLevels() {
		return this.levels;
	}
	public void setMdulId(Long value) {
		this.mdulId = value;
	}
	
	public Long getMdulId() {
		return this.mdulId;
	}
	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}

