package com.wdzsj.mgr.entity.marketing.vo;

import com.wdzsj.mgr.entity.marketing.Active;

/**
 * 营销活动首页列表关联kpi表的封装
 */
public class ActiveVo extends Active {

    /**
     * t_wfj_kpi_detail表中的act_id 对应 t_wfj_active 表中的act_id
     */
    private Integer kdActId;
    /**
     * t_wfj_kpi_detail表中的kpi指标类型kpi_type 取值1、2、3以逗号分隔(例如：1,2,3)
     */
    private String kpaType;
    /**
     * t_wfj_kpi_detail表中的kpi指标类型kpi_type 对应的值kpi_value 以 kpi_type_数值的形式存储(例如：1_5000,2_3000,3_10000)
     */
    private String kpiValue;
    /**
     * kpi是否达标
     * 取值：
     *      Y：是
     *      N：否
     *      X：未知
     */
    private String upToStandard;
    /**
     * 封装着kpi_type和kpi_value整合后的数据，例如：销售额(50000),人流(10000),毛利(100000)
     */
    private String activeKpi;
    /**
     * 投放渠道：
     * 取值：
     *      0：短信
     *      1：朋友圈
     */
    private String channelDesc;

    public Integer getKdActId() {
        return kdActId;
    }

    public void setKdActId(Integer kdActId) {
        this.kdActId = kdActId;
    }

    public String getKpaType() {
        return kpaType;
    }

    public void setKpaType(String kpaType) {
        this.kpaType = kpaType;
    }

    public String getKpiValue() {
        return kpiValue;
    }

    public void setKpiValue(String kpiValue) {
        this.kpiValue = kpiValue;
    }

    public String getUpToStandard() {
        return upToStandard;
    }

    public void setUpToStandard(String upToStandard) {
        this.upToStandard = upToStandard;
    }

    public String getActiveKpi() {
        return activeKpi;
    }

    public void setActiveKpi(String activeKpi) {
        this.activeKpi = activeKpi;
    }

    public String getChannelDesc() {
        return channelDesc;
    }

    public void setChannelDesc(String channelDesc) {
        this.channelDesc = channelDesc;
    }
}
