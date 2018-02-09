package com.wdzsj.mgr.dao.arct;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import com.alibaba.fastjson.JSONObject;
import com.wdzsj.cmn.base.BaseDao;
import com.wdzsj.mgr.entity.arct.Pst;

@Repository
public interface PstDao extends BaseDao<Pst>{
	/**
	 * 获取根数据节点
	 * @return
	 */
	List<JSONObject> findRoot();
	
	/**
	 * 根据上级节点获取数据列表
	 * @param parId
	 * @return
	 */
	List<JSONObject> findByParId(Long parId);

	/**
	 * 根据id
	 * @param id
	 * @return
	 */
	Pst getById(Long id);
	
	/**
	 * 删除ID所在的节点
	 * @param id
	 * @return
	 */	
	//Integer delete(@Param("id")Long id);
	
	/**
	 * 删除
	 * @param id
	 * 标识ID 可以是自增长ID，也可以是唯一标识。
	 */
	void delete(Long id);

	/**
	 * 保存一个实体
	 * 
	 * @param pst
	 */
	Integer insert(Pst pst);
	
	/**
	 * 统计是否存在该节点
	 * @param parId
	 * @param name	
	 * @return
	 */
	Integer countByParIdAndName(@Param("parId")Long parId, @Param("name")String name);
	
	/**
	 * 更新一个实体
	 * 
	 * @param pst
	 */
	Integer update(Pst pst);

	/**
	 * 更新一个实体
	 * 
	 * @param id
	 */
	Integer updateIsParent(@Param("id")Long id);
}
