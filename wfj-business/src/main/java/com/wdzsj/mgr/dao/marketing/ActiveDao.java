package com.wdzsj.mgr.dao.marketing;

import com.wdzsj.mgr.entity.marketing.Active;
import com.wdzsj.mgr.entity.marketing.vo.ActiveVo;
import com.wdzsj.mgr.entity.marketing.vo.*;

import java.util.List;
import java.util.Map;

public interface ActiveDao{

    ActiveVo selectByPrimaryKey(Long actId);

    /**
     * 编辑活动数据时  根据活动表的主键查询 活动表 kpi表 附件表数据
     * @param actId
     * @return
     */
    EditActiveAndKpiVo selectEditActiveDataByActId(Long actId);

    ActiveSeedVo selectEditActiveSeedByActId(Long actId);

    /**
     * 新建活动数据时 可以返回编辑的功能时 的查询 用于回显
     * @param actId
     * @return
     */
    CreateEditActiveVo selectCreateEditActiveDataByActId(Long actId);

    int insert(Active active);

    int insertSelective(Active active);

    Integer count(Map<String, Object> map);

    int updateByPrimaryKeySelective(Active active);



    //******************************** 以下是页面重构后的服务 ********************************

    List<ActiveVo> findList(Map<String, Object> map);
}