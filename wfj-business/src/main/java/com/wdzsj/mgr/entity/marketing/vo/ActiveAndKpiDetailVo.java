package com.wdzsj.mgr.entity.marketing.vo;

/**
 * 用于营销活动页面的from表单提交时的对象绑定
 */
public class ActiveAndKpiDetailVo {
    /**
     * 活动ID
     */
    private Long s_actId;
    /**
     * 主题
     */
    private String s_theme;
    /**
     * 活动开始时间
     */
    private String s_startTime;
    /**
     * 活动结束时间
     */
    private String s_endTime;
    /**
     * 活动给针对的门店
     */
    private String s_country;
    /**
     * 销售额
     */
    private String s_sellAmount;
    /**
     * 人流
     */
    private String s_passengerFlow;
    /**
     * 毛利
     */
    private String s_grossProfit;
    /**
     * h5Url
     */
    private String s_h5Url;
    /**
     * 活动内容
     */
    private String s_content;
    /**
     * 附件ID
     */
    private Long attachmentId;

    /**
     * 所选品牌ID 包含所有
     */
    private String s_allPpArray;
    /**
     * 所选品牌ID
     */
    private String s_ppIdArray;

    public String getS_theme() {
        return s_theme;
    }

    public void setS_theme(String s_theme) {
        this.s_theme = s_theme;
    }

    public String getS_startTime() {
        return s_startTime;
    }

    public void setS_startTime(String s_startTime) {
        this.s_startTime = s_startTime;
    }

    public String getS_endTime() {
        return s_endTime;
    }

    public void setS_endTime(String s_endTime) {
        this.s_endTime = s_endTime;
    }

    public String getS_country() {
        return s_country;
    }

    public void setS_country(String s_country) {
        this.s_country = s_country;
    }

    public String getS_sellAmount() {
        return s_sellAmount;
    }

    public void setS_sellAmount(String s_sellAmount) {
        this.s_sellAmount = s_sellAmount;
    }

    public String getS_passengerFlow() {
        return s_passengerFlow;
    }

    public void setS_passengerFlow(String s_passengerFlow) {
        this.s_passengerFlow = s_passengerFlow;
    }

    public String getS_grossProfit() {
        return s_grossProfit;
    }

    public void setS_grossProfit(String s_grossProfit) {
        this.s_grossProfit = s_grossProfit;
    }

    public String getS_h5Url() {
        return s_h5Url;
    }

    public void setS_h5Url(String s_h5Url) {
        this.s_h5Url = s_h5Url;
    }

    public String getS_content() {
        return s_content;
    }

    public void setS_content(String s_content) {
        this.s_content = s_content;
    }

    public Long getS_actId() {
        return s_actId;
    }

    public void setS_actId(Long s_actId) {
        this.s_actId = s_actId;
    }

    public Long getAttachmentId() {
        return attachmentId;
    }

    public void setAttachmentId(Long attachmentId) {
        this.attachmentId = attachmentId;
    }

    public String getS_allPpArray() {
        return s_allPpArray;
    }

    public void setS_allPpArray(String s_allPpArray) {
        this.s_allPpArray = s_allPpArray;
    }

    public String getS_ppIdArray() {
        return s_ppIdArray;
    }

    public void setS_ppIdArray(String s_ppIdArray) {
        this.s_ppIdArray = s_ppIdArray;
    }
}
