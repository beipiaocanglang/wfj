package com.wdzsj.mgr.entity.marketing;

public class KpiDetail {
    /**
     * t_wfj_kpi_detail表中的act_id 对应 t_wfj_active 表中的act_id
     */
    private Long actId;
    /**
     * t_wfj_kpi_detail表中的kpi指标类型kpi_type
     * 取值：
     *      1：销售额
     *      2：人流
     *      3：
     *      存储多个类型以逗号分隔(例如：1,2,3)
     */
    private String kpiType;
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

    public Long getActId() {
        return actId;
    }

    public void setActId(Long actId) {
        this.actId = actId;
    }

    public String getKpiType() {
        return kpiType;
    }

    public void setKpiType(String kpiType) {
        this.kpiType = kpiType == null ? null : kpiType.trim();
    }

    public String getKpiValue() {
        return kpiValue;
    }

    public void setKpiValue(String kpiValue) {
        this.kpiValue = kpiValue == null ? null : kpiValue.trim();
    }

    public String getUpToStandard() {
        return upToStandard;
    }

    public void setUpToStandard(String upToStandard) {
        this.upToStandard = upToStandard == null ? null : upToStandard.trim();
    }
}