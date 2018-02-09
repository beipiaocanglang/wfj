package com.wdzsj.mgr.entity.acl;


import com.wdzsj.cmn.base.BaseEntity;

public class Acl extends BaseEntity {
	
	public static final String TYPE_ROLE = "role";
	public static final String TYPE_USER = "user";
	
	public static final int ACL_TRI_STATE_EXTENDS = 0xFFFFFFFF;
	
	public static final int ACL_TRI_STATE_UNEXTENDS = 0;
	
	public static final int ACL_YES = 1;
	
	public static final int ACL_NO = 0;
	
	public static final int ACL_NEUTRAL = -1;
	
    //主体类型     
	private String prinType;
    //主体id     
	private Long prinId;
    //模块id       
	private Long mdulId;
    //操作权限      
	private Integer aclstate;
    //操作是否有效     
	private Integer acltristate;

	public Acl(){
	}

	public String getPrinType() {
		return prinType;
	}

	public void setPrinType(String prinType) {
		this.prinType = prinType;
	}

	public Long getPrinId() {
		return prinId;
	}

	public void setPrinId(Long prinId) {
		this.prinId = prinId;
	}

	public Long getMdulId() {
		return mdulId;
	}

	public void setMdulId(Long mdulId) {
		this.mdulId = mdulId;
	}

	public void setAclstate(Integer value) {
		this.aclstate = value;
	}
	
	public Integer getAclstate() {
		return this.aclstate;
	}
	public void setAcltristate(Integer value) {
		this.acltristate = value;
	}
	
	public Integer getAcltristate() {
		return this.acltristate;
	}

	public void setPermission(int permission,boolean yes){
		if(aclstate == null) {
			aclstate = 0;
		}
		int temp = 1;
		temp = temp << permission;
		if(yes){
			aclstate |= temp;
		}else{
			aclstate &= ~temp;
		}
	}
	
	public int getPermission(int permission){
		if(aclstate == null) {
			aclstate = 0;
		}
		if(acltristate == null) {
			acltristate = 0;
		}
		if(acltristate == ACL_TRI_STATE_EXTENDS){
			return ACL_NEUTRAL;
		}
		
		int temp = 1;
		temp = temp << permission;
		temp &= aclstate;
		if(temp != 0){
			return ACL_YES;
		}
		return ACL_NO;
	}
	
	public void setExtends(boolean yes){
		if(yes){
			acltristate = ACL_TRI_STATE_EXTENDS;
		}else{
			acltristate = ACL_TRI_STATE_UNEXTENDS;
		}
	}
}

