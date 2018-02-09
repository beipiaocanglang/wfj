package com.wdzsj.mgr.castor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.wdzsj.mgr.entity.acl.Acl;
import com.wdzsj.mgr.entity.acl.Func;
import com.wdzsj.mgr.entity.acl.Mdul;
import com.wdzsj.mgr.entity.acl.Role;
import com.wdzsj.mgr.entity.acl.SysUser;
import com.wdzsj.mgr.entity.acl.UserRole;

public class UserMetaData implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private List<SysUser> sysUserList = new ArrayList<SysUser>();
	
	private List<UserRole> userRoleList = new ArrayList<UserRole>();
	
	private List<Role> roleList = new ArrayList<Role>();
	
	private List<Mdul> mdulList = new ArrayList<Mdul>();
	
	private List<Func> funcList = new ArrayList<Func>();
	
	private List<Acl> aclList = new ArrayList<Acl>();

	public List<SysUser> getSysUserList() {
		return sysUserList;
	}

	public void setSysUserList(List<SysUser> sysUserList) {
		this.sysUserList = sysUserList;
	}

	public List<UserRole> getUserRoleList() {
		return userRoleList;
	}

	public void setUserRoleList(List<UserRole> userRoleList) {
		this.userRoleList = userRoleList;
	}

	public List<Role> getRoleList() {
		return roleList;
	}

	public void setRoleList(List<Role> roleList) {
		this.roleList = roleList;
	}

	public List<Mdul> getMdulList() {
		return mdulList;
	}

	public void setMdulList(List<Mdul> mdulList) {
		this.mdulList = mdulList;
	}

	public List<Func> getFuncList() {
		return funcList;
	}

	public void setFuncList(List<Func> funcList) {
		this.funcList = funcList;
	}

	public List<Acl> getAclList() {
		return aclList;
	}

	public void setAclList(List<Acl> aclList) {
		this.aclList = aclList;
	}
}
