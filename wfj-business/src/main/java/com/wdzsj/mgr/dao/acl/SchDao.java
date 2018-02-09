package com.wdzsj.mgr.dao.acl;

import org.springframework.stereotype.Repository;
import com.wdzsj.cmn.base.BaseDao;
import com.wdzsj.mgr.entity.acl.Sch;

@Repository
public interface SchDao extends BaseDao<Sch> {
	 /**
     * 查询所有角色
     * @return
     */
    //public List<Sch> findAll();
}
