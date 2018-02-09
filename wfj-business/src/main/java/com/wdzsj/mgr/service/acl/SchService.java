package com.wdzsj.mgr.service.acl;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.wdzsj.cmn.base.BaseDao;
import com.wdzsj.cmn.base.BaseService;
import com.wdzsj.mgr.dao.acl.SchDao;
import com.wdzsj.mgr.entity.acl.Sch;

@Service
@SuppressWarnings({"unchecked", "rawtypes"})
public class SchService extends BaseService<Sch> {
	@Resource
	private SchDao schDao;
	
	@Override
	protected BaseDao<Sch> getEntityDao() {
		return schDao;
	}
	
//	public List<Sch> findAll() {
//		return schDao.findAll();
//	}

}
