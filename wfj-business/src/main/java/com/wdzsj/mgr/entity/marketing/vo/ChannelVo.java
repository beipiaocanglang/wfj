package com.wdzsj.mgr.entity.marketing.vo;

/**
 * 选择渠道页面数据封装
 */
public class ChannelVo {
    /**
     * 短信渠道对应的ID
     */
    private Long s_smsChannelId;
    /**
     * 朋友圈渠道对应的ID
     */
    private Long s_friChannelId;
    /**
     * 活动ID
     */
    private Long s_actId;

    /**
     * 活动kpi
     */
    private String activeKpi;

    /**
     * 种子用户数
     */
    private Long seedNum;

    /**
     * 选择了短信渠道
     * 页面取值是固定的：0   有值就说明选择了短信渠道
     */
    private String sms_selectChannel;

    /**
     * 短信渠道是否包含种子用户
     * 取值：
     *      0：是
     *      1：否
     */
    private Integer sms_ContainSeedUser;

    /**
     * 短信渠道投放时间
     */
    private String sms_deliveryStartTime;

    /**
     * 短信渠道投放时间
     */
    private String sms_deliveryEndTime;

    /**
     * 短信渠道建议投放人群数
     */
    private String sms_deliveryNum;

    /**
     * 短信的转化率
     */
    private String sms_conversionRate;

    /**
     * 选择了朋友圈渠道
     * 页面取值是固定的：0   有值就说明选择了短信渠道
     */
    private String fri_selectChannel;

    /**
     * 朋友圈渠道是否包含种子用户
     * 取值：
     *      0：是
     *      1：否
     */
    private Integer fri_ContainSeedUser;

    /**
     * 朋友圈渠道投放时间
     */
    private String fri_deliveryStartTime;

    /**
     * 朋友圈渠道投放时间
     */
    private String fri_deliveryEndTime;

    /**
     * 朋友圈渠道建议投放人群数
     */
    private String fri_deliveryNum;

    /**
     * 朋友圈的转化率
     */
    private String fri_conversionRate;


    public Long getS_smsChannelId() {
        return s_smsChannelId;
    }

    public void setS_smsChannelId(Long s_smsChannelId) {
        this.s_smsChannelId = s_smsChannelId;
    }

    public Long getS_friChannelId() {
        return s_friChannelId;
    }

    public void setS_friChannelId(Long s_friChannelId) {
        this.s_friChannelId = s_friChannelId;
    }

    public Long getS_actId() {
        return s_actId;
    }

    public void setS_actId(Long s_actId) {
        this.s_actId = s_actId;
    }

    public String getActiveKpi() {
        return activeKpi;
    }

    public void setActiveKpi(String activeKpi) {
        this.activeKpi = activeKpi;
    }

    public Long getSeedNum() {
        return seedNum;
    }

    public void setSeedNum(Long seedNum) {
        this.seedNum = seedNum;
    }

    public String getSms_selectChannel() {
        return sms_selectChannel;
    }

    public void setSms_selectChannel(String sms_selectChannel) {
        this.sms_selectChannel = sms_selectChannel;
    }

    public String getSms_conversionRate() {
        return sms_conversionRate;
    }

    public void setSms_conversionRate(String sms_conversionRate) {
        this.sms_conversionRate = sms_conversionRate;
    }

    public String getFri_selectChannel() {
        return fri_selectChannel;
    }

    public void setFri_selectChannel(String fri_selectChannel) {
        this.fri_selectChannel = fri_selectChannel;
    }

    public Integer getSms_ContainSeedUser() {
        return sms_ContainSeedUser;
    }

    public void setSms_ContainSeedUser(Integer sms_ContainSeedUser) {
        this.sms_ContainSeedUser = sms_ContainSeedUser;
    }

    public Integer getFri_ContainSeedUser() {
        return fri_ContainSeedUser;
    }

    public void setFri_ContainSeedUser(Integer fri_ContainSeedUser) {
        this.fri_ContainSeedUser = fri_ContainSeedUser;
    }

    public String getSms_deliveryNum() {
        return sms_deliveryNum;
    }

    public void setSms_deliveryNum(String sms_deliveryNum) {
        this.sms_deliveryNum = sms_deliveryNum;
    }

    public String getFri_deliveryNum() {
        return fri_deliveryNum;
    }

    public void setFri_deliveryNum(String fri_deliveryNum) {
        this.fri_deliveryNum = fri_deliveryNum;
    }

    public String getFri_conversionRate() {
        return fri_conversionRate;
    }

    public void setFri_conversionRate(String fri_conversionRate) {
        this.fri_conversionRate = fri_conversionRate;
    }

    public String getSms_deliveryStartTime() {
        return sms_deliveryStartTime;
    }

    public void setSms_deliveryStartTime(String sms_deliveryStartTime) {
        this.sms_deliveryStartTime = sms_deliveryStartTime;
    }

    public String getSms_deliveryEndTime() {
        return sms_deliveryEndTime;
    }

    public void setSms_deliveryEndTime(String sms_deliveryEndTime) {
        this.sms_deliveryEndTime = sms_deliveryEndTime;
    }

    public String getFri_deliveryStartTime() {
        return fri_deliveryStartTime;
    }

    public void setFri_deliveryStartTime(String fri_deliveryStartTime) {
        this.fri_deliveryStartTime = fri_deliveryStartTime;
    }

    public String getFri_deliveryEndTime() {
        return fri_deliveryEndTime;
    }

    public void setFri_deliveryEndTime(String fri_deliveryEndTime) {
        this.fri_deliveryEndTime = fri_deliveryEndTime;
    }
}
