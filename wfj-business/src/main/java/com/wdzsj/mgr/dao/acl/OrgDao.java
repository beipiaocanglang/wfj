package com.wdzsj.mgr.dao.acl;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.alibaba.fastjson.JSONObject;

@Repository
public interface OrgDao {
	
	/**
	 * 获取所有数据节点
	 * @return
	 */
	List<JSONObject> findAll(Long userId);
	
	/**
	 * 根据上级节点获取数据列表
	 * @param parId
	 * @return
	 */
	List<JSONObject> findByParId(Long parId);
}
