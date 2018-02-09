package com.wdzsj.mgr.service.marketing;

import com.wdzsj.mgr.dao.marketing.KpiDetailDao;
import com.wdzsj.mgr.entity.marketing.KpiDetail;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@SuppressWarnings({"all", "rawtypes"})
public class KpiDetailService {
    @Resource
    private KpiDetailDao kpiDetailDao;

    //根据活动表的ID保存kpi表的数据
    public Integer insertSelective(KpiDetail kpiDetail){

        return kpiDetailDao.insertSelective(kpiDetail);
    }

    /**
     * 根据活动id 动态修改
     * @param kpiDetail
     * @return
     */
    public int updateByPrimaryKeySelective(KpiDetail kpiDetail){
        return kpiDetailDao.updateByPrimaryKeySelective(kpiDetail);
    }

    /**
     * 根据活动ID查询
     * @param actId
     * @return
     */
    public KpiDetail selectByPrimaryKey(Long actId){
        return kpiDetailDao.selectByPrimaryKey(actId);
    }
}
