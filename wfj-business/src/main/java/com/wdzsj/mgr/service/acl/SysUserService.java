package com.wdzsj.mgr.service.acl;

import javax.annotation.Resource;
import com.wdzsj.cmn.constant.Status;
import com.wdzsj.cmn.exception.WDException;
import com.wdzsj.cmn.helper.CacheHelper;
import com.wdzsj.cmn.util.StringUtil;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import com.wdzsj.cmn.base.BaseDao;
import com.wdzsj.cmn.base.BaseService;
import com.wdzsj.cmn.util.MD5;
import com.wdzsj.mgr.dao.acl.RoleDao;
import com.wdzsj.mgr.dao.acl.SysUserDao;
import com.wdzsj.mgr.dao.acl.UserRoleDao;
import com.wdzsj.mgr.entity.acl.SysUser;
import com.wdzsj.mgr.entity.acl.UserRole;

@Component
public class SysUserService extends BaseService<SysUser> {

	@Resource
	private SysUserDao sysUserDao;
	
	@Resource
	private UserRoleDao userRoleDao;
	
	@Resource
	private RoleDao roleDao;
	
	
	@Override
	protected BaseDao<SysUser> getEntityDao() {
		return sysUserDao;
	}
	
	/**
	 * 添加系统用户
	 * @param sysUser
	 */
	@Transactional
	public void addSysUser(SysUser sysUser) {
		int count = countByUserName(sysUser.getUname());
		if (count > 0) {
			throw new WDException(Status.EXCEPTION_ERROR,"用户账号已经存在");
		}

		if(StringUtil.isNotEmpty(sysUser.getPswd())) {
			sysUser.setPswd(MD5.encrypt(sysUser.getPswd()));
		}
		sysUserDao.insert(sysUser);
	}
	
	/**
	 * 修改系统用户
	 * @param sysUser
	 */
	@Transactional
	public void updateSysUser(SysUser sysUser) {
		sysUserDao.update(sysUser);
	}

	/**
	 * 给用户分配角色
	 * @param userId
	 * @param roleIds
	 * @param level
	 */
	@Transactional
	public void assignRole(Long userId,String roleIds,Integer level) {
//		int count = userRoleDao.countByRoleAndUserId(userId,roleId);
//		if(count == 0) {
//			UserRole userRole = new UserRole();
//			userRole.setUserId(userId);
//			userRole.setRoleId(roleId);
//			userRole.setLevel(level);
//			userRoleDao.insert(userRole);
//
//			String key = "Role_" + userId;
//			CacheHelper.remove(key);
//		}

		userRoleDao.deleteByUserId(userId);

		String[] data = StringUtil.splitString(roleIds, ",");
		for(String roleId : data) {
			if (StringUtil.isNotEmpty(roleId)) {
				UserRole userRole = new UserRole();
				userRole.setUserId(userId);
				userRole.setRoleId(Long.parseLong(roleId));
				userRole.setLevel(level);
				userRoleDao.insert(userRole);
			}
		}
		String key = "Role_" + userId;
		CacheHelper.remove(key);
	}

	public Integer countByUserName(String userName) {
		return sysUserDao.countByUserName(userName);
	}

	/**
	 * 修改密码
	 * @param userId
	 * @param pswd
	 * @param newPswd
	 */
	public void updatePswd(Long userId,String pswd,String newPswd) {
		SysUser sysUser = sysUserDao.getById(userId);

		if(StringUtil.isEmpty(pswd) || StringUtil.isEmpty(newPswd)) {
			throw new WDException(Status.EXCEPTION_ERROR,"参数不正确！");
		}

		if(!MD5.encrypt(pswd).equals(sysUser.getPswd())) {
			throw new WDException(Status.EXCEPTION_ERROR,"原密码不正确！");
		}

		sysUser.setPswd(MD5.encrypt(newPswd));
		sysUserDao.update(sysUser);
	}

	/**
	 * 重置密码
	 * @param userId
	 * @param newPswd
	 */
	public void resetPswd(Long userId,String newPswd) {
		SysUser sysUser = sysUserDao.getById(userId);

		if(StringUtil.isEmpty(newPswd)) {
			throw new WDException(Status.EXCEPTION_ERROR,"参数不正确！");
		}

		sysUser.setPswd(MD5.encrypt(newPswd));
		sysUserDao.update(sysUser);
	}
}
