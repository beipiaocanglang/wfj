package com.wdzsj.mgr.entity.marketing;

public class TagModel {
    private String cid;

    private String clifestage;

    private String prewarning;

    private String rfvcluster3m;

    private String rfvcluster6m;

    private String rfvcluster12m;

    private String senDiscount;

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid == null ? null : cid.trim();
    }

    public String getClifestage() {
        return clifestage;
    }

    public void setClifestage(String clifestage) {
        this.clifestage = clifestage == null ? null : clifestage.trim();
    }

    public String getPrewarning() {
        return prewarning;
    }

    public void setPrewarning(String prewarning) {
        this.prewarning = prewarning == null ? null : prewarning.trim();
    }

    public String getRfvcluster3m() {
        return rfvcluster3m;
    }

    public void setRfvcluster3m(String rfvcluster3m) {
        this.rfvcluster3m = rfvcluster3m == null ? null : rfvcluster3m.trim();
    }

    public String getRfvcluster6m() {
        return rfvcluster6m;
    }

    public void setRfvcluster6m(String rfvcluster6m) {
        this.rfvcluster6m = rfvcluster6m == null ? null : rfvcluster6m.trim();
    }

    public String getRfvcluster12m() {
        return rfvcluster12m;
    }

    public void setRfvcluster12m(String rfvcluster12m) {
        this.rfvcluster12m = rfvcluster12m == null ? null : rfvcluster12m.trim();
    }

    public String getSenDiscount() {
        return senDiscount;
    }

    public void setSenDiscount(String senDiscount) {
        this.senDiscount = senDiscount == null ? null : senDiscount.trim();
    }
}