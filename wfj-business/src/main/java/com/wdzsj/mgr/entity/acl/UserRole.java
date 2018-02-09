package com.wdzsj.mgr.entity.acl;

import com.wdzsj.cmn.base.BaseEntity;

public class UserRole extends BaseEntity {
	
    //用户id       
	private Long userId;
    //角色id      
	private Long roleId;
    //优先级    
	private int level;

	public UserRole(){
	}

	public void setUserId(long value) {
		this.userId = value;
	}
	public long getUserId() {
		return this.userId;
	}
	public void setRoleId(long value) {
		this.roleId = value;
	}
	public long getRoleId() {
		return this.roleId;
	}
	public void setLevel(int value) {
		this.level = value;
	}
	public int getLevel() {
		return this.level;
	}
}

