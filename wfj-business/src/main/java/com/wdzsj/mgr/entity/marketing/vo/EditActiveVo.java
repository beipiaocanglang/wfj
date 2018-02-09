package com.wdzsj.mgr.entity.marketing.vo;

import com.wdzsj.mgr.entity.marketing.Active;

import java.util.Date;

/**
 * 编辑活动 三张表关联
 * active 活动表
 * kpi 对应kpi表
 * attachment 对应附件表(二维码)
 */
public class EditActiveVo extends Active{
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
        this.attachmentName = attachmentName;
    }

    public String getAttachmentType() {
        return attachmentType;
    }

    public void setAttachmentType(String attachmentType) {
        this.attachmentType = attachmentType;
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
        this.fileUrl = fileUrl;
    }

    public byte[] getAttachmentContent() {
        return attachmentContent;
    }

    public void setAttachmentContent(byte[] attachmentContent) {
        this.attachmentContent = attachmentContent;
    }
}
