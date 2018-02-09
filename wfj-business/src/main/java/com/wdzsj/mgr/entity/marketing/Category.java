package com.wdzsj.mgr.entity.marketing;

public class Category {
    /**
     * 品牌编码
     */
    private String pp;

    /**
     * 品牌名称
     */
    private String standardPpname;

    /**
     * 上级品牌编码
     */
    private String pId;

    /**
     * 当前品牌的等级
     */
    private Integer classHierarchy;

    /**
     * 对应门店ID
     */
    private String storeId;

    public String getPp() {
        return pp;
    }

    public void setPp(String pp) {
        this.pp = pp == null ? null : pp.trim();
    }

    public String getStandardPpname() {
        return standardPpname;
    }

    public void setStandardPpname(String standardPpname) {
        this.standardPpname = standardPpname == null ? null : standardPpname.trim();
    }

    public String getpId() {
        return pId;
    }

    public void setpId(String pId) {
        this.pId = pId == null ? null : pId.trim();
    }

    public Integer getClassHierarchy() {
        return classHierarchy;
    }

    public void setClassHierarchy(Integer classHierarchy) {
        this.classHierarchy = classHierarchy;
    }

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }
}