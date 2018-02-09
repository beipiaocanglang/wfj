package com.wdzsj.mgr.entity.marketing.vo;

import com.wdzsj.cmn.annotation.ExcelVOAttr;

/**
 * 智算时下载用的vo
 */
public class ZhiSuanVo {
    /**
     * 用户ID
     */
    @ExcelVOAttr(name="会员ID",column="A")
    private String rowkey;
    /**
     * 性别
     */
    @ExcelVOAttr(name="性别",column="B",combo = { "男", "女", "未知" })
    private String sex;
    /**
     * 年龄范围
     */
    @ExcelVOAttr(name="年龄范围",column="C")
    private String age_group;
    /**
     * 手机号
     */
    @ExcelVOAttr(name="手机号",column="D")
    private String phone;
    /**
     *所在城市
     */
    @ExcelVOAttr(name="籍贯城市",column="E")
    private String province;
    /**
     * 城市ID
     */
    @ExcelVOAttr(name="所在城市",column="F")
    private String cityName;
    private String cityId;
    /**
     *品类ID
     */
    @ExcelVOAttr(name="品类名称",column="G")
    private String ppName;

    private String ppId;
    /**
     *openid
     */
    @ExcelVOAttr(name="openid",column="H")
    private String openid;
    /**
     *年份
     */
    @ExcelVOAttr(name="年份",column="I")
    private String year;
    /**
     *周消费费频率
     */
    @ExcelVOAttr(name="周消费费频率",column="J")
    private String weekendfrequency;
    /**
     *周数
     */
    @ExcelVOAttr(name="周数",column="K")
    private String weekenddays;
    /**
     *周消费金额
     */
    @ExcelVOAttr(name="周消费金额",column="L")
    private String weekendsalemoney;


    public String getRowkey() {
        return rowkey;
    }

    public void setRowkey(String rowkey) {
        this.rowkey = rowkey;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAge_group() {
        return age_group;
    }

    public void setAge_group(String age_group) {
        this.age_group = age_group;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getPpName() {
        return ppName;
    }

    public void setPpName(String ppName) {
        this.ppName = ppName;
    }

    public String getPpId() {
        return ppId;
    }

    public void setPpId(String ppId) {
        this.ppId = ppId;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getWeekendfrequency() {
        return weekendfrequency;
    }

    public void setWeekendfrequency(String weekendfrequency) {
        this.weekendfrequency = weekendfrequency;
    }

    public String getWeekenddays() {
        return weekenddays;
    }

    public void setWeekenddays(String weekenddays) {
        this.weekenddays = weekenddays;
    }

    public String getWeekendsalemoney() {
        return weekendsalemoney;
    }

    public void setWeekendsalemoney(String weekendsalemoney) {
        this.weekendsalemoney = weekendsalemoney;
    }

    @Override
    public String toString() {
        return "ZhiSuanVo{" +
                "rowkey='" + rowkey + '\'' +
                ", sex='" + sex + '\'' +
                ", age_group='" + age_group + '\'' +
                ", phone='" + phone + '\'' +
                ", province='" + province + '\'' +
                ", cityName='" + cityName + '\'' +
                ", cityId='" + cityId + '\'' +
                ", ppName='" + ppName + '\'' +
                ", ppId='" + ppId + '\'' +
                ", openid='" + openid + '\'' +
                ", year='" + year + '\'' +
                ", weekendfrequency='" + weekendfrequency + '\'' +
                ", weekenddays='" + weekenddays + '\'' +
                ", weekendsalemoney='" + weekendsalemoney + '\'' +
                '}';
    }
}
