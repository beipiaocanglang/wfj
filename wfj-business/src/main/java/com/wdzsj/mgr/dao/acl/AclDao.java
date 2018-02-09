package com.wdzsj.mgr.dao.acl;

import com.wdzsj.cmn.base.BaseDao;
import com.wdzsj.mgr.entity.acl.Acl;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Map;

@Repository
public interface AclDao extends BaseDao<Acl> {

	/**
	 * 获取权限对象
	 * @param map
	 * @return
	 */
	public Acl getByMap(Map<Object, Object> map);
	
	/**
	 * 获取权限列表
	 * @param map
	 * @return
	 */
	public List<Acl> findAcl(Map<Object, Object> map);
	
	/**
	 * 获取我的角色id列表
	 * @param userId
	 * @return
	 */
	public List<Map<Object,Object>> findRoleids(Long userId);
}
