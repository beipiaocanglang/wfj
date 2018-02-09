package com.wdzsj.cmn.base;

import java.io.Serializable;
import java.util.List;

/**
 * 
 * @author qy
 *
 * @param <T>
 */
public interface BaseDao<T> {

	/**
	 * 保存一个实体
	 * 
	 * @param t
	 */
	Integer insert(T t);

	/**
	 * 删除
	 * 
	 * @param id
	 *            标识ID 可以是自增长ID，也可以是唯一标识。
	 */
	void delete(Serializable id);

	/**
	 * 更新一个实体
	 * 
	 * @param t
	 */
	Integer update(T t);

	/**
	 * 通过一个标识ID 获取一个唯一实体
	 * 
	 * @param id
	 *            标识ID 可以是自增长ID，也可以是唯一标识。
	 * @return
	 */
	T getById(Serializable id);
	/**
	 * 返回所有数据
	 * @param t
	 * @return
	 */
	List<T> findList(Object o);
	
	Integer count(Object o);
}
