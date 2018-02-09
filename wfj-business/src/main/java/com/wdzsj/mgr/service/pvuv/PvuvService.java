package com.wdzsj.mgr.service.pvuv;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.alibaba.fastjson.JSONObject;
import com.wdzsj.cmn.base.BaseDao;
import com.wdzsj.cmn.base.BaseService;
import com.wdzsj.mgr.dao.pvuv.PvuvDao;
import com.wdzsj.mgr.entity.pvuv.Pvuv;

@Service
public class PvuvService extends BaseService<Pvuv> {

	@Resource
	private PvuvDao pvuvDao;

	@Override
	protected BaseDao<Pvuv> getEntityDao() {
		return pvuvDao;
	}
	
	public JSONObject findData(){
		
		return pvuvDao.findJson();
	}
}
