package com.wdzsj.mgr.entity.marketing.vo;

/**
 * 扩散标签封装
 */
public class LabelSpreadVo {
    /**
     * 活动ID
     */
    private Long s_actId;
    /**
     * 活动针对的门店
     */
    private String[] s_storesId;
    /**
     * 年龄
     */
    private String[] age;
    /**
     * 性别
     */
    private String[] gender;
    /**
     * 是否已婚
     */
    private String[] isMarriage;
    /**
     * 消费定位
     */
    private String[] consumeLocation;
    /**
     * 购物频次
     */
    private String[] shoppingFrequency;
    /**
     * 客单价
     */
    private String[] perTicketSalesPrice;
    /**
     * RFV标签
     */
    private String[] rfvLabel;
    /**
     * 省
     * @return
     */
    private String province;
    /**
     * 市
     * @return
     */
    private String city;
    /**
     * 县/区
     * @return
     */
    private String county;
    /**
     * 异类品牌会员数
     * @return
     */
    private String alienBrandMemberNum;
    /**
     * 目标kpi人数
     * @return
     */
    private String targetKpiNum;
    /**
     * 异类品牌种子会员数
     * @return
     */
    private String alienBrandSeedMemberNum;

    public Long getS_actId() {
        return s_actId;
    }

    public void setS_actId(Long s_actId) {
        this.s_actId = s_actId;
    }

    public String[] getS_storesId() {
        return s_storesId;
    }

    public void setS_storesId(String[] s_storesId) {
        this.s_storesId = s_storesId;
    }

    public String[] getAge() {
        return age;
    }
    public void setAge(String[] age) {
        this.age = age;
    }
    public String[] getGender() {
        return gender;
    }
    public void setGender(String[] gender) {
        this.gender = gender;
    }
    public String[] getIsMarriage() {
        return isMarriage;
    }
    public void setIsMarriage(String[] isMarriage) {
        this.isMarriage = isMarriage;
    }
    public String[] getConsumeLocation() {
        return consumeLocation;
    }
    public void setConsumeLocation(String[] consumeLocation) {
        this.consumeLocation = consumeLocation;
    }
    public String[] getShoppingFrequency() {
        return shoppingFrequency;
    }
    public void setShoppingFrequency(String[] shoppingFrequency) {
        this.shoppingFrequency = shoppingFrequency;
    }
    public String[] getPerTicketSalesPrice() {
        return perTicketSalesPrice;
    }
    public void setPerTicketSalesPrice(String[] perTicketSalesPrice) {
        this.perTicketSalesPrice = perTicketSalesPrice;
    }
    public String[] getRfvLabel() {
        return rfvLabel;
    }
    public void setRfvLabel(String[] rfvLabel) {
        this.rfvLabel = rfvLabel;
    }
    public String getProvince() {
        return province;
    }
    public void setProvince(String province) {
        this.province = province;
    }
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public String getCounty() {
        return county;
    }
    public void setCounty(String county) {
        this.county = county;
    }
    public String getAlienBrandMemberNum() {
        return alienBrandMemberNum;
    }
    public void setAlienBrandMemberNum(String alienBrandMemberNum) {
        this.alienBrandMemberNum = alienBrandMemberNum;
    }
    public String getTargetKpiNum() {
        return targetKpiNum;
    }
    public void setTargetKpiNum(String targetKpiNum) {
        this.targetKpiNum = targetKpiNum;
    }
    public String getAlienBrandSeedMemberNum() {
        return alienBrandSeedMemberNum;
    }
    public void setAlienBrandSeedMemberNum(String alienBrandSeedMemberNum) {
        this.alienBrandSeedMemberNum = alienBrandSeedMemberNum;
    }
}
