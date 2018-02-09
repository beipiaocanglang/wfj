package com.wdzsj.mgr.entity.marketing;

/**
 * 扩散标签中存储
 */
public class ActiveTagRelation {
    /**
     * 对应活动表ID
     */
    private Long actId;

    /**
     * 对应标签数据表ID
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

    public Long getActId() {
        return actId;
    }

    public void setActId(Long actId) {
        this.actId = actId;
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
}