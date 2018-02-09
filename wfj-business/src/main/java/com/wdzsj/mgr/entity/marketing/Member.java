package com.wdzsj.mgr.entity.marketing;

import java.util.Date;

/**
 * 会员基本数据表
 */
public class Member {
    /**
     * 用户ID ID是保存用户数据之前生成的，调用智算接口后返回的
     */
    private String cid;
    /**
     * 用户手机号
     */
    private String phone;
    /**
     * 用户openid 例如：o6wt5vy75ajkfmirrqbjghfgj
     */
    private String openid;
    /**
     * 用户性别
     * 取值：
     *      男/女/未知
     */
    private String sex;
    /**
     * 年龄范围 例如：20~30
     */
    private String ageGroup;
    /**
     * 所属城市 省份 例如：北京
     */
    private String province;
    /**
     * 所属城市 市 例如：北京
     */
    private String city;
    /**
     * 所属城市 区/县 例如：朝阳区
     */
    private String district;
    /**
     * 门店编号
     */
    private String mdh;
    /**
     * 会员状态
     * 取值：
     *      Y：是
     *      N：否
     */
    private String cidStatus;
    /**
     * 开卡时间
     */
    private Date cardtime;
    /**
     * 总累计活动积分
     */
    private Long hdsum;
    /**
     * 总累计积分
     */
    private Long jfsum;
    /**
     * 总累计消费
     */
    private Long xfsum;
    /**
     * 婚姻状况
     * 取值：
     *      已婚
     *      未婚
     *      未知
     */
    private String marry;
    /**
     * 学历
     */
    private String educ;
    /**
     * 职业
     */
    private String work;


    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid == null ? null : cid.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid == null ? null : openid.trim();
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }

    public String getAgeGroup() {
        return ageGroup;
    }

    public void setAgeGroup(String ageGroup) {
        this.ageGroup = ageGroup == null ? null : ageGroup.trim();
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province == null ? null : province.trim();
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district == null ? null : district.trim();
    }

    public String getMdh() {
        return mdh;
    }

    public void setMdh(String mdh) {
        this.mdh = mdh == null ? null : mdh.trim();
    }

    public String getCidStatus() {
        return cidStatus;
    }

    public void setCidStatus(String cidStatus) {
        this.cidStatus = cidStatus == null ? null : cidStatus.trim();
    }

    public Date getCardtime() {
        return cardtime;
    }

    public void setCardtime(Date cardtime) {
        this.cardtime = cardtime;
    }

    public Long getHdsum() {
        return hdsum;
    }

    public void setHdsum(Long hdsum) {
        this.hdsum = hdsum;
    }

    public Long getJfsum() {
        return jfsum;
    }

    public void setJfsum(Long jfsum) {
        this.jfsum = jfsum;
    }

    public Long getXfsum() {
        return xfsum;
    }

    public void setXfsum(Long xfsum) {
        this.xfsum = xfsum;
    }

    public String getMarry() {
        return marry;
    }

    public void setMarry(String marry) {
        this.marry = marry == null ? null : marry.trim();
    }

    public String getEduc() {
        return educ;
    }

    public void setEduc(String educ) {
        this.educ = educ == null ? null : educ.trim();
    }

    public String getWork() {
        return work;
    }

    public void setWork(String work) {
        this.work = work == null ? null : work.trim();
    }
}