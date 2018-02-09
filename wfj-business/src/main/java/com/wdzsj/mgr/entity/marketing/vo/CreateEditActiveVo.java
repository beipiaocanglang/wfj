package com.wdzsj.mgr.entity.marketing.vo;

import java.util.Date;

/**
 * 创建活动时 也可以直接编辑的vo
 * author : sunpanhu
 * create time : 2018/2/5 上午11:04
 */
public class CreateEditActiveVo extends EditActiveAndKpiVo{

    /**
     * 对应活动表ID
     */
    private Long seedActId;

    /**
     * 对应表亲啊数据表ID
     */
    private Long tagDataId;

    /**
     * 扩散标签中的json数据
     */
    private String tagData;

    /**
     * 种子人群中已选的品牌/品类维度
     */
    private String seedCategoryData;


    /**
     * 种子文件附件ID
     */
    private Long importAttachmentId;
    /**
     * 种子文件附件名称
     */
    private String importAttachmentName;
    /**
     * 种子文件附件类型
     */
    private String importAttachmentType;
    /**
     * 种子文件创建时间
     */
    private Date importAttachmentCreatetime;
    /**
     * 种子文件更新时间
     */
    private Date importAttachmentUpdatetime;
    /**
     * 种子文件服务器文件路径(该值不为空时表示文件存盘不存库)
     */
    private String importFileUrl;
    /**
     * 种子文件附件内容
     */
    private byte[] importAttachmentContent;


    public Long getSeedActId() {
        return seedActId;
    }

    public void setSeedActId(Long seedActId) {
        this.seedActId = seedActId;
    }

    public Long getTagDataId() {
        return tagDataId;
    }

    public void setTagDataId(Long tagDataId) {
        this.tagDataId = tagDataId;
    }

    public String getTagData() {
        return tagData;
    }

    public void setTagData(String tagData) {
        this.tagData = tagData;
    }

    public String getSeedCategoryData() {
        return seedCategoryData;
    }

    public void setSeedCategoryData(String seedCategoryData) {
        this.seedCategoryData = seedCategoryData;
    }

    public Long getImportAttachmentId() {
        return importAttachmentId;
    }

    public void setImportAttachmentId(Long importAttachmentId) {
        this.importAttachmentId = importAttachmentId;
    }

    public String getImportAttachmentName() {
        return importAttachmentName;
    }

    public void setImportAttachmentName(String importAttachmentName) {
        this.importAttachmentName = importAttachmentName;
    }

    public String getImportAttachmentType() {
        return importAttachmentType;
    }

    public void setImportAttachmentType(String importAttachmentType) {
        this.importAttachmentType = importAttachmentType;
    }

    public Date getImportAttachmentCreatetime() {
        return importAttachmentCreatetime;
    }

    public void setImportAttachmentCreatetime(Date importAttachmentCreatetime) {
        this.importAttachmentCreatetime = importAttachmentCreatetime;
    }

    public Date getImportAttachmentUpdatetime() {
        return importAttachmentUpdatetime;
    }

    public void setImportAttachmentUpdatetime(Date importAttachmentUpdatetime) {
        this.importAttachmentUpdatetime = importAttachmentUpdatetime;
    }

    public String getImportFileUrl() {
        return importFileUrl;
    }

    public void setImportFileUrl(String importFileUrl) {
        this.importFileUrl = importFileUrl;
    }

    public byte[] getImportAttachmentContent() {
        return importAttachmentContent;
    }

    public void setImportAttachmentContent(byte[] importAttachmentContent) {
        this.importAttachmentContent = importAttachmentContent;
    }
}
