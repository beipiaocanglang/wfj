package com.wdzsj.mgr.service.acl;

import com.alibaba.fastjson.JSONObject;
import com.wdzsj.cmn.base.BaseDao;
import com.wdzsj.cmn.base.BaseService;
import com.wdzsj.mgr.dao.acl.OrgUserDao;
import com.wdzsj.mgr.entity.acl.OrgUser;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
@SuppressWarnings({"unchecked", "rawtypes"})
public class OrgUserService extends BaseService<OrgUser> {

	@Resource
	private OrgUserDao orgUserDao;
	
	@Override
	protected BaseDao<OrgUser> getEntityDao() {
		return orgUserDao;
	}

	/**
	 * 获取分配给用户的叶子节点结构id与name
	 * @param userId
	 * @return
	 */
	public List<JSONObject> findLeafOrgByUserId(Long userId) {
		return orgUserDao.findLeafOrgByUserId(userId);
	}
}
