package com.wdzsj.mgr.dao.acl;

import com.alibaba.fastjson.JSONObject;
import com.wdzsj.cmn.base.BaseDao;
import com.wdzsj.mgr.entity.acl.OrgUser;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface OrgUserDao extends BaseDao<OrgUser> {
	
	/**
	 * 删除用户所在的节点
	 * @param orgId
	 * @param userId
	 * @return
	 */
	Integer deleteByOrgIdAndUserId(@Param("orgId")Long orgId, @Param("userId")Long userId);

	/**
	 * 根据用户id删除节点
	 * @param userId
	 * @return
	 */
	Integer deleteByUserId(@Param("userId")Long userId);
	
	/**
	 * 根据用户获取所有节点
	 * @param userId
	 * @return
	 */
	List<OrgUser> findByUserId(@Param("userId")Long userId);
	
	/**
	 * 统计用户是否存在该节点
	 * @param orgId
	 * @param userId
	 * @return
	 */
	Integer countByOrgIdAndUserId(@Param("orgId")Long orgId, @Param("userId")Long userId);

	/**
	 * 获取分配给用户的叶子节点结构id与name
	 * @param userId
	 * @return
	 */
	List<JSONObject> findLeafOrgByUserId(@Param("userId")Long userId);
}
