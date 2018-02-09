package com.wdzsj.mgr.entity.marketing.vo;

/**
 * 种子人群页面回显
 */
public class ActiveSeedVo extends EditActiveVo {
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
}
