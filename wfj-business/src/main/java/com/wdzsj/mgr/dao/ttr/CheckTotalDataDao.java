package com.wdzsj.mgr.dao.ttr;

import com.wdzsj.mgr.entity.ttr.CardActivation;
import com.wdzsj.mgr.entity.ttr.CardActiveVo;
import com.wdzsj.mgr.entity.ttr.SecondLevelTotalTableVo;
import com.wdzsj.mgr.entity.ttr.TotalTableVo;

import java.util.List;
import java.util.Map;

public interface CheckTotalDataDao{

    Integer countAll(Object o);

    List<TotalTableVo> findActiveCardList(Object obj);

    List<TotalTableVo> findBackCardList(Object obj);

    /**
     * 汇总二级报表查询根据首页payFinishTime查询详情
     * @param param
     * @return
     */
    List<CardActivation> findTTRByPayFinishTime(Map<String, Object> param);

    /**
     * 汇总二级报表页面搜索
     * @param param
     * @return
     */
    List<SecondLevelTotalTableVo> searchTTRByTime(Map<String,Object> param);

    /**
     * 二级汇总表的导出
     */
    List<SecondLevelTotalTableVo> exportSecondLevelTTRByTime(Map<String, Object> param);


    /**
     * 根据TransId查询三级汇总表详情
     */
    List<CardActiveVo> findTTRByTransId(Map<String, Object> param);

    /**
     * 汇总三级报表页面搜索
     * @param parammap
     * @return
     */
    List<CardActiveVo> searchTTRByPriceAndTransId(Map<String,Object> parammap);
}
