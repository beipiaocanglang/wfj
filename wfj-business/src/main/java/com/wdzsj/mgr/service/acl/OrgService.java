package com.wdzsj.mgr.service.acl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.wdzsj.cmn.util.StringUtil;
import com.wdzsj.mgr.dao.acl.OrgDao;
import com.wdzsj.mgr.dao.acl.OrgUserDao;
import com.wdzsj.mgr.entity.acl.OrgUser;
import org.springframework.transaction.annotation.Transactional;

@Service
@SuppressWarnings({"unchecked", "rawtypes"})
public class OrgService {

	@Resource
	private OrgDao orgDao;
	
	@Resource
	private OrgUserDao orgUserDao;
	
	/**
	 * 根据用户ID获取所有节点
	 * @param userId
	 * @return
	 */
	public List<JSONObject> findAll(Long userId) {
		List<JSONObject> result = orgDao.findAll(userId);
		return result;
	}
	
	/**
	 * 根据上级ID获取数据列表
	 * @param parId
	 * @return
	 */
	public List<JSONObject> findByParId(Long parId) {
		List<JSONObject> result = orgDao.findByParId(parId);
		for(JSONObject object : result) {
			object.put(" nocheck", true);
		}
		return result;
	}
	
	/**
	 * 保存用户所在的结构节点
	 * @param orgIds
	 * @param userId
	 */
	@Transactional
	public void save(String orgIds, Long userId) {
		orgUserDao.deleteByUserId(userId);

		String[] data = StringUtil.splitString(orgIds, ",");
		for(String orgId : data) {
			if(StringUtil.isNotEmpty(orgId)) {
				long curOrgId = Long.parseLong(orgId);
//				int count = orgUserDao.countByOrgIdAndUserId(curOrgId, userId);
//				if(count == 0) {
//					OrgUser orgUser = new OrgUser();
//					orgUser.setUserId(userId);
//					orgUser.setOrgId(curOrgId);
//					orgUserDao.insert(orgUser);
//				}
				OrgUser orgUser = new OrgUser();
				orgUser.setUserId(userId);
				orgUser.setOrgId(curOrgId);
				orgUserDao.insert(orgUser);
			}
		}
	}
	
	/**
	 * 删除用户所在的节点
	 * @param orgIds
	 * @param userId
	 */
	public void delete(String orgIds, Long userId) {
		String[] data = StringUtil.splitString(orgIds, ",");
		for(String orgId : data) {
			if(StringUtil.isNotEmpty(orgId)) {
				orgUserDao.deleteByOrgIdAndUserId(Long.parseLong(orgId), userId);
			}
		}
	}
}
