package com.wdzsj.mgr.dao.cac;

import java.util.List;
import java.util.Map;

import com.wdzsj.mgr.entity.cac.*;
import org.springframework.stereotype.Repository;
import com.wdzsj.cmn.base.BaseDao;

@SuppressWarnings("all")
@Repository
public interface CheckAccountDao extends BaseDao<CheckAccountVo> {

    /**
     * 根据posId统计
     * @param obj
     * @return
     */
    Integer countByPosId(Object obj);

    /**
     * 门店二级消费报表根据首页posid查询详情
     * @param possid
     * @return
     */
	List<CheckAccountVo> findCheckAccountVoByPosid(Map<String, Object> param);

    /**
     * 门店二级消费报表页面搜索
     * @param parammap
     * @return
     */
	List<CheckAccountVo> findCheckAccountVoByPosidAndTime(Map<String,Object> parammap);

    /**
     * 门店消费报表导出Excel表格
     * @param parammap
     * @return
     */
    List<CheckAccountVo> findCheckAccountVoByPosidAndTime02(Map<String,Object> parammap);

    /**
     * 门店消费二级表查询导出Excel表格
     * @param parammap
     * @return
     */
    List<CheckAccountDetailVo> findCheckAccountVoByPosidAndTime03(Map<String,Object> parammap);

    //**************************以下是消费核对表的数据操作**************start***********

    /**
     * 核对消费表首页获取数据
     * @param param
     * @return
     */
    List<ConsumeCheckVo> findConsumeList(Map<String, Object> param);

    /**
     * 消费核对表导出数据到Excel
     * @param param
     * @return
     */
    List<ConsumeCheckVo> exportIndexExcel(Map<String, Object> param);

    /**
     * 消费核对表根据首页transtime查询二级消费核对表列表
     * @param model
     * @param request
     * @param transtime
     * @return
     */
    List<ConsumeCheckSecondLevelDetailVo> findCheckAccountVoByTransTime(Map<String, Object> param);

    /**
     * 根据storename查询三级消费核销对账列表
     * @param model
     * @param request
     * @param storename
     * @return
     */
    List<ConsumeCheckThreeLevelDetailVo> findCheckAccountVoByStorename(Map<String, Object> param);

    //**************************以下是消费核对表的数据操作**************end***********
}
