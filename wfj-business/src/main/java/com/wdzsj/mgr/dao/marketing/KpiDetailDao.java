package com.wdzsj.mgr.dao.marketing;

import com.wdzsj.mgr.entity.marketing.KpiDetail;
import java.util.Map;

public interface KpiDetailDao {
    KpiDetail selectByPrimaryKey(Long actId);

    int insert(KpiDetail kpiDetail);

    int insertSelective(KpiDetail kpiDetail);

    int countByMap(Map<String, Object> map);

    int updateByPrimaryKeySelective(KpiDetail kpiDetail);
}