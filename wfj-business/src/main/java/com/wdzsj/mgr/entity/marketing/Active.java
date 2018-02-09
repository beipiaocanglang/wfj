package com.wdzsj.mgr.entity.marketing;

import java.util.Date;

public class Active {
    /**
     * 活动ID
     */
    private Long actId;
    /**
     * 活动主题
     */
    private String theme;
    /**
     * 活动内容
     */
    private String content;
    /**
     * 活动范围ID(即关联门店表的ID,多个用逗号分隔)
     */
    private String storeId;
    /**
     * 活动开始时间
     */
    private Date startTime;
    /**
     * 活动结束时间
     */
    private Date endTime;
    /**
     * 活动状态
     * 取值：
     *      0：未开始
     *      1：进行中
     *      2：已完成
     */
    private String status;
    /**
     * 渠道ID
     * 取值：
     *      0：短信
     *      1：朋友圈
     */
    private String channelId;

    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 更新时间
     */
    private Date updateTime;

    private String h5Url;

    /**
     * 附件ID(种子导入文件)  以二进制的形式存储用户导入的种子文件
     */
    private Long attachmentIdImportFile;

    /**
     * 附件ID(推送文件) 以二进制的形式存储 供用户下载的种子模板
     */
    private Long attachmentIdExportFile;

    /**
     * 附件ID(二维码) 以二进制的形式存储二维码图片
     */
    private Long attachmentIdQrCode;

    /**
     * 智算后的种子数量
     * @return
     */
    private String seedNum;

    public Long getActId() {
        return actId;
    }
    public void setActId(Long actId) {
        this.actId = actId;
    }
    public String getTheme() {
        return theme;
    }
    public void setTheme(String theme) {
        this.theme = theme == null ? null : theme.trim();
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
    public String getStoreId() {
        return storeId;
    }
    public void setStoreId(String storeId) {
        this.storeId = storeId == null ? null : storeId.trim();
    }
    public Date getStartTime() {
        return startTime;
    }
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }
    public Date getEndTime() {
        return endTime;
    }
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }
    public String getChannelId() {
        return channelId;
    }
    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }
    public Date getCreateTime() {
        return createTime;
    }
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    public Date getUpdateTime() {
        return updateTime;
    }
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
    public String getH5Url() {
        return h5Url;
    }
    public void setH5Url(String h5Url) {
        this.h5Url = h5Url == null ? null : h5Url.trim();
    }
    public Long getAttachmentIdImportFile() {
        return attachmentIdImportFile;
    }
    public void setAttachmentIdImportFile(Long attachmentIdImportFile) {
        this.attachmentIdImportFile = attachmentIdImportFile;
    }
    public Long getAttachmentIdExportFile() {
        return attachmentIdExportFile;
    }
    public void setAttachmentIdExportFile(Long attachmentIdExportFile) {
        this.attachmentIdExportFile = attachmentIdExportFile;
    }
    public Long getAttachmentIdQrCode() {
        return attachmentIdQrCode;
    }
    public void setAttachmentIdQrCode(Long attachmentIdQrCode) {
        this.attachmentIdQrCode = attachmentIdQrCode;
    }
    public String getSeedNum() {
        return seedNum;
    }
    public void setSeedNum(String seedNum) {
        this.seedNum = seedNum;
    }
}