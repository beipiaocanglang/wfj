package com.wdzsj.mgr.entity.acl;

import java.util.List;

public class AclView {

	// 模块id
	private Long mdulId;
	// 操作是否有效
	private Integer acltristate;
	//权限列表
	private List<Integer> aclList;
	
	public Long getMdulId() {
		return mdulId;
	}
	public void setMdulId(Long mdulId) {
		this.mdulId = mdulId;
	}
	public Integer getAcltristate() {
		return acltristate;
	}
	public void setAcltristate(Integer acltristate) {
		this.acltristate = acltristate;
	}
	public List<Integer> getAclList() {
		return aclList;
	}
	public void setAclList(List<Integer> aclList) {
		this.aclList = aclList;
	}
}

