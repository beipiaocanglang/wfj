package com.wdzsj.mgr.dao.pvuv;


import org.springframework.stereotype.Repository;

import com.alibaba.fastjson.JSONObject;
import com.wdzsj.cmn.base.BaseDao;

import com.wdzsj.mgr.entity.pvuv.Pvuv;
@Repository
public interface PvuvDao  extends BaseDao<Pvuv>{
	JSONObject findJson();
}
