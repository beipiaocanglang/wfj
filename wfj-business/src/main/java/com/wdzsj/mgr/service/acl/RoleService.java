package com.wdzsj.mgr.service.acl;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.wdzsj.cmn.base.BaseDao;
import com.wdzsj.cmn.base.BaseService;
import com.wdzsj.mgr.dao.acl.RoleDao;
import com.wdzsj.mgr.entity.acl.Role;

@Service
@SuppressWarnings({"unchecked", "rawtypes"})
public class RoleService extends BaseService<Role> {

	@Resource
	private RoleDao roleDao;
	
	@Override
	protected BaseDao<Role> getEntityDao() {
		return roleDao;
	}
	
	public List<Role> findAll() {
		return roleDao.findAll();
	}
}
