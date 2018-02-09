package com.wdzsj.cmn.base;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.wdzsj.cmn.entity.Page;
import com.wdzsj.cmn.entity.PageRequest;

public abstract class BaseService<T> {
	
	protected abstract BaseDao<T> getEntityDao();
	
	public Integer insert(T t) {
		return getEntityDao().insert(t);
	}

    public void delete(Long id) {
		getEntityDao().delete(id);
	}

	public Integer update(T t) {
		return getEntityDao().update(t);
	}

	public T getById(Serializable id) {
		return getEntityDao().getById(id);
	}

	public List<T> findList(Object o) {
		return getEntityDao().findList(o);
	}

	public Integer count(Object o) {
		return getEntityDao().count(o);
	}

	public Page findPage(PageRequest<Map<String,Object>> pageRequest) {
		int totalCount = getEntityDao().count(pageRequest.getFilters());
		List<T> list = getEntityDao().findList(pageRequest.getAllFilters());
		Page page = new Page(pageRequest,totalCount, list);
		return page;
	}
}
