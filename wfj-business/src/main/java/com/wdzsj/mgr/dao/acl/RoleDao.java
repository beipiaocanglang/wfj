package com.wdzsj.mgr.dao.acl;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.wdzsj.cmn.base.BaseDao;
import com.wdzsj.mgr.entity.acl.Role;

@Repository
public interface RoleDao extends BaseDao<Role> {
	
    /**
     * 查询所有角色
     * @return
     */
    public List<Role> findAll();
}
