package com.wdzsj.mgr.dao.srm;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.wdzsj.cmn.base.BaseDao;
import com.wdzsj.mgr.entity.srm.Srvr;

@Repository
public interface SrvrDao extends BaseDao<Srvr> {
	 /**
     * 查询所有服 务 器 资 源
     * @return
     */
    public List<Srvr> findAll();
}
