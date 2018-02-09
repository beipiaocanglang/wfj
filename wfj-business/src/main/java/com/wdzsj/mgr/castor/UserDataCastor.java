package com.wdzsj.mgr.castor;

import java.util.List;
import com.wdzsj.cmn.base.BeanManager;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;
import com.wdzsj.mgr.castor.util.CastorUtils;
import com.wdzsj.mgr.castor.util.EntityResource;
import com.wdzsj.mgr.dao.acl.AclDao;
import com.wdzsj.mgr.dao.acl.FuncDao;
import com.wdzsj.mgr.dao.acl.MdulDao;
import com.wdzsj.mgr.dao.acl.RoleDao;
import com.wdzsj.mgr.dao.acl.SysUserDao;
import com.wdzsj.mgr.dao.acl.UserRoleDao;
import com.wdzsj.mgr.entity.acl.Acl;
import com.wdzsj.mgr.entity.acl.Func;
import com.wdzsj.mgr.entity.acl.Mdul;
import com.wdzsj.mgr.entity.acl.Role;
import com.wdzsj.mgr.entity.acl.SysUser;
import com.wdzsj.mgr.entity.acl.UserRole;

@Component
public class UserDataCastor implements ICastor {
	static Log logger = LogFactory.getLog(UserDataCastor.class);
	
	public void cast(EntityResource entityResource) {
		UserMetaData userData = CastorUtils.getInstance().loadByCastor(UserMetaData.class, entityResource.getConfMappingLocation(), entityResource.getConfLocation());
		
		/**用户**/
		updateSysUserList(userData.getSysUserList());
		updateUserRoleList(userData.getUserRoleList());
		updateRoleList(userData.getRoleList());
		updateMdulList(userData.getMdulList());
		updateFuncList(userData.getFuncList());
		updateAcllList(userData.getAclList());
	}
	
	private void updateSysUserList(List<SysUser> SysUserList){
		SysUserDao sysUserDao = BeanManager.getBean("sysUserDao", SysUserDao.class);
		for (SysUser sysUser : SysUserList) {
			try{
				SysUser entity = sysUserDao.getById(sysUser.getId());
				sysUser.setStatus(1);
				if(null == entity) {
					sysUserDao.insert(sysUser);
				} else {
					//SysUserDao.update(sysUser);
				}
			}catch(Exception e){
				logger.error(e.getMessage(), e);
			}
		}
	}
	
	private void updateAcllList(List<Acl> bgMngaclList) {
		AclDao aclDao = BeanManager.getBean("aclDao", AclDao.class);
		for (Acl acl : bgMngaclList) {
			try{
				Acl entity = aclDao.getById(acl.getId());
				if(null == entity) {
					aclDao.insert(acl);
				} else {
					aclDao.update(acl);
				}
			}catch(Exception e){
				logger.error(e.getMessage(), e);
			}
		}
	}

	private void updateFuncList(List<Func> FuncList) {
		FuncDao funcDao = BeanManager.getBean("funcDao", FuncDao.class);
		for (Func func : FuncList) {
			try{
				Func entity = funcDao.getById(func.getId());
				if(null == entity) {
					funcDao.insert(func);
				} else {
					funcDao.update(func);
				}
			}catch(Exception e){
				logger.error(e.getMessage(), e);
			}
		}
	}

	private void updateMdulList(List<Mdul> MdulList) {
		MdulDao mdulDao = BeanManager.getBean("mdulDao", MdulDao.class);
		for (Mdul mdul : MdulList) {
			try{
				Mdul entity = mdulDao.getById(mdul.getId());
				if(null == entity) {
					mdulDao.insert(mdul);
				} else {
					mdulDao.update(mdul);
				}
			}catch(Exception e){
				logger.error(e.getMessage(), e);
			}
		}
	}

	private void updateRoleList(List<Role> RoleList) {
		RoleDao roleDao = BeanManager.getBean("roleDao", RoleDao.class);
		for (Role role : RoleList) {
			try{
				Role entity = roleDao.getById(role.getId());
				if(null == entity) {
					role.setCode("SYSTEM");
					roleDao.insert(role);
				} else {
					role.setCode("SYSTEM");
					roleDao.update(role);
				}
			}catch(Exception e){
				logger.error(e.getMessage(), e);
			}
		}
	}

	private void updateUserRoleList(List<UserRole> UserRoleList) {
		UserRoleDao userRoleDao = BeanManager.getBean("userRoleDao", UserRoleDao.class);
		for (UserRole userRole : UserRoleList) {
			try{
				UserRole entity = userRoleDao.getById(userRole.getId());
				if(null == entity) {
					userRoleDao.insert(userRole);
				} else {
					userRoleDao.update(userRole);
				}
			}catch(Exception e){
				logger.error(e.getMessage(), e);
			}
		}
	}
}
