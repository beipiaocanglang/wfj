package com.wdzsj.mgr.service.acl;

import javax.annotation.Resource;
import com.alibaba.fastjson.JSONObject;
import com.wdzsj.cmn.helper.CacheHelper;
import org.springframework.stereotype.Service;
import com.wdzsj.cmn.base.BaseDao;
import com.wdzsj.cmn.base.BaseService;
import com.wdzsj.mgr.dao.acl.UserRoleDao;
import com.wdzsj.mgr.entity.acl.UserRole;
import java.util.List;

@Service
@SuppressWarnings({"unchecked", "rawtypes"})
public class UserRoleService extends BaseService<UserRole> {

	@Resource
	private UserRoleDao userRoleDao;
	
	@Override
	protected BaseDao<UserRole> getEntityDao() {
		return userRoleDao;
	}

	/**
	 * 根据用户ID获取他的角色
	 * @param userId
	 * @return
	 */
	public List<JSONObject> findRoleByUserId(Long userId) {
		return userRoleDao.findRoleByUserId(userId);
	}

	public void deleteByUserIdAndRoleId(Long userId, Long roleId) {
		userRoleDao.deleteByUserIdAndRoleId(userId,roleId);

		String key = "Role_" + userId;
		CacheHelper.remove(key);
	}
}
