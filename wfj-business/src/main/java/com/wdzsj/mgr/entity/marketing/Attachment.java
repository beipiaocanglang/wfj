package com.wdzsj.mgr.entity.marketing;

import java.util.Date;

/**
 * 附件表，对应活动表中对应的字段存储的附件id
 */
public class Attachment {
    /**
     * 附件ID
     */
    private Long attachmentId;
    /**
     * 附件名称
     */
    private String attachmentName;
    /**
     * 附件类型
     */
    private String attachmentType;
    /**
     * 创建时间
     */
    private Date attachmentCreatetime;
    /**
     * 更新时间
     */
    private Date attachmentUpdatetime;
    /**
     * 服务器文件路径(该值不为空时表示文件存盘不存库)
     */
    private String fileUrl;
    /**
     * 附件内容
     */
    private byte[] attachmentContent;

    public Long getAttachmentId() {
        return attachmentId;
    }

    public void setAttachmentId(Long attachmentId) {
        this.attachmentId = attachmentId;
    }

    public String getAttachmentName() {
        return attachmentName;
    }

    public void setAttachmentName(String attachmentName) {
        this.attachmentName = attachmentName == null ? null : attachmentName.trim();
    }

    public String getAttachmentType() {
        return attachmentType;
    }

    public void setAttachmentType(String attachmentType) {
        this.attachmentType = attachmentType == null ? null : attachmentType.trim();
    }

    public Date getAttachmentCreatetime() {
        return attachmentCreatetime;
    }

    public void setAttachmentCreatetime(Date attachmentCreatetime) {
        this.attachmentCreatetime = attachmentCreatetime;
    }

    public Date getAttachmentUpdatetime() {
        return attachmentUpdatetime;
    }

    public void setAttachmentUpdatetime(Date attachmentUpdatetime) {
        this.attachmentUpdatetime = attachmentUpdatetime;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl == null ? null : fileUrl.trim();
    }

    public byte[] getAttachmentContent() {
        return attachmentContent;
    }

    public void setAttachmentContent(byte[] attachmentContent) {
        this.attachmentContent = attachmentContent;
    }
}