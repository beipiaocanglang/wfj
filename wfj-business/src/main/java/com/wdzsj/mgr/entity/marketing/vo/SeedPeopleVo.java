package com.wdzsj.mgr.entity.marketing.vo;

/**
 * 保存种子数据
 */
public class SeedPeopleVo {
    /**
     * 活动ID
     */
    private String s_actId;
    /**
     * 开始时间
     */
    private String s_startTime;
    /**
     * 结束时间
     */
    private String s_endTime;
    /**
     * 门店ID
     */
    private String[] s_storesId;
    /**
     * 所选品牌ID 包含所有
     */
    private String s_allPpArray;
    /**
     * 所选品牌ID
     */
    private String s_ppIdArray;
    /**
     * 附件表ID
     */
    private String s_attachmentId;

    public String getS_actId() {
        return s_actId;
    }

    public void setS_actId(String s_actId) {
        this.s_actId = s_actId;
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

    public String[] getS_storesId() {
        return s_storesId;
    }

    public void setS_storesId(String[] s_storesId) {
        this.s_storesId = s_storesId;
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

    public String getS_attachmentId() {
        return s_attachmentId;
    }

    public void setS_attachmentId(String s_attachmentId) {
        this.s_attachmentId = s_attachmentId;
    }
}
