package com.wdzsj.mgr.entity.acl;

import com.wdzsj.cmn.base.BaseEntity;

public class SysUser extends BaseEntity {
	
	private static final long serialVersionUID = 1L;
	
	//用户名   
	private String uname;
	//密码   
	private String pswd;
	//真实姓名   
	private String rname;
	//手机   
	private String tel;
	//头像   
	private String headUrl;
	//邮箱   
	private String email;
	//万达用户平台ID   
	private String appId;
	//万达用户code   
	private String userCode;
	//职位   
	private String jobName;
	//机构ID   
	private String orgId;
	//机构名称   
	private String orgName;
	//状态（0未激活 1 激活 2 冻结）   
	private Integer status;

	public void setUname(String value) {
		this.uname = value;
	}
	
	public String getUname() {
		return this.uname;
	}
	
	public void setPswd(String value) {
		this.pswd = value;
	}
	
	public String getPswd() {
		return this.pswd;
	}
	
	public void setRname(String value) {
		this.rname = value;
	}
	
	public String getRname() {
		return this.rname;
	}
	
	public void setTel(String value) {
		this.tel = value;
	}
	
	public String getTel() {
		return this.tel;
	}
	
	public void setHeadUrl(String value) {
		this.headUrl = value;
	}
	
	public String getHeadUrl() {
		return this.headUrl;
	}
	
	public void setEmail(String value) {
		this.email = value;
	}
	
	public String getEmail() {
		return this.email;
	}
	
	public void setAppId(String value) {
		this.appId = value;
	}
	
	public String getAppId() {
		return this.appId;
	}
	
	public void setUserCode(String value) {
		this.userCode = value;
	}
	
	public String getUserCode() {
		return this.userCode;
	}
	
	public void setJobName(String value) {
		this.jobName = value;
	}
	
	public String getJobName() {
		return this.jobName;
	}
	
	public void setOrgId(String value) {
		this.orgId = value;
	}
	
	public String getOrgId() {
		return this.orgId;
	}
	
	public void setOrgName(String value) {
		this.orgName = value;
	}
	
	public String getOrgName() {
		return this.orgName;
	}
	
	public void setStatus(Integer value) {
		this.status = value;
	}
	
	public Integer getStatus() {
		return this.status;
	}
}

