package com.wdzsj.mgr.service.arct;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.alibaba.fastjson.JSONObject;
import com.wdzsj.cmn.base.BaseDao;
import com.wdzsj.cmn.base.BaseService;
import com.wdzsj.mgr.dao.arct.DeptDao;
import com.wdzsj.mgr.entity.arct.Dept;

@Service
@SuppressWarnings({"unchecked", "rawtypes"})
public class DeptService extends BaseService<Dept>{

	@Resource
	private DeptDao deptDao;
	
	@Override
	protected BaseDao<Dept> getEntityDao() {
		// TODO Auto-generated method stub
		return null;
	}
		
	/**
	 * 获取所有根节点
	 * @return
	 */
	public List<JSONObject> findRoot() {
		List<JSONObject> result = deptDao.findRoot();
		return result;
	}
	
	/**
	 * 根据上级ID获取数据列表
	 * @param parId
	 * @return
	 */
	public List<JSONObject> findByParId(Long parId) {
		List<JSONObject> result = deptDao.findByParId(parId);
		for(JSONObject object : result) {
			object.put(" nocheck", true);
		}
		return result;
	}
	/**
	 * 保存结构节点
	 * @param dept
	 */
	public Dept save(Dept dept) {		
		deptDao.updateIsParent(dept.getParId());	
			
		deptDao.insert(dept);
		return dept;
	}

	/**
	 * 保存结构节点
	 * @param id
	 * @param name
	 */
	public void update(Long id,String name) {		
		Dept dept = new Dept();
		dept.setId(id);
		dept.setName(name);
		deptDao.update(dept);
	}

	/**
	 * 删除结构节点
	 * @param id
	 */
	 public void delete(Long id) {
		 deptDao.delete(id);		 
		}
}




