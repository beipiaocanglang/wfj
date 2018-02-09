package com.wdzsj.mgr.service.sys;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.wdzsj.cmn.base.BaseDao;
import com.wdzsj.cmn.base.BaseService;
import com.wdzsj.mgr.dao.sys.DictDao;
import com.wdzsj.mgr.entity.sys.Dict;

@Service
@SuppressWarnings({"unchecked", "rawtypes"})
public class DictService extends BaseService<Dict> {

	@Resource
	private DictDao dictDao;
	
	@Override
	protected BaseDao<Dict> getEntityDao() {
		return dictDao;
	}

	/**
	  * 根据上级id获取下级列表
	  * @param parId
	  * @return
	  */
	public List<Dict> findByParId(long parId) {
		List<Dict> list = dictDao.findByParId(parId);
		return list;
	}
	
	/**
	 * 根据上级code获取下级列表
	 * @param code
	 * @return
	 */
	public List<Dict> findByCode(String code) {
		List<Dict> list = dictDao.findByCode(code);
		return list;
	}

	/**
	 * 根据id获取数据字典对象
	 * @param id
	 * @return
	 */
	public Dict getById(long id) {
		Dict dictionary = dictDao.getById(id);
		return dictionary;
	}
	
	/**
	 * 根据code获取数据字典对象
	 * @param code
	 * @return
	 */
	public Dict getByCode(String code) {
		Dict dictionary = dictDao.getByCode(code);
		return dictionary;
	}

	public Integer countByParId(long parId) {
		return dictDao.countByParId(parId);
	}

	public Integer countByCode(String code) {
		return dictDao.countByCode(code);
	}
}
