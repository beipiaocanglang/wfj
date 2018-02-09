package com.wdzsj.mgr.entity.sys;

public class Dict {
	
	private Long id;
    //名称
	private String name;
	//编码   
	private String code;
	//父id 
	private Long parId;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Long getParId() {
		return parId;
	}

	public void setParId(Long parId) {
		this.parId = parId;
	}
}

