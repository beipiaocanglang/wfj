package com.wdzsj.mgr.entity.marketing.vo;

public class UserVo{
    /**
     * 年龄段
     */
    private String age_group;
    /**
     * openid
     */
    private String openid;
    /**
     * 手机号
     */
    private String phone;
    /**
     * 所属城市
     */
    private String province;
    /**
     * 用户ID
     */
    private String rowkey;
    /**
     * 性别
     */
    private String sex;

    public String getAge_group() {
        return age_group;
    }

    public void setAge_group(String age_group) {
        this.age_group = age_group;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
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
}
