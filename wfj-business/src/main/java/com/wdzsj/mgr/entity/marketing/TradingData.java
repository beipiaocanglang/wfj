package com.wdzsj.mgr.entity.marketing;

import java.math.BigDecimal;

/**
 * 交易记录
 */
public class TradingData {
    /**
     * 会员id
     * 活动会员关联表 与 交易记录表  一对多
     */
    private String cid;
    /**
     * 品类id
     */
    private String pp;
    /**
     * 门店id
     */
    private String storeId;
    /**
     * 周消费金额
     */
    private BigDecimal weekMoney;
    /**
     * 周消费频率
     */
    private Integer weekRate;
    /**
     * 周数
     */
    private Integer weekNo;
    /**
     * 年
     */
    private Integer year;

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getPp() {
        return pp;
    }

    public void setPp(String pp) {
        this.pp = pp;
    }

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    public BigDecimal getWeekMoney() {
        return weekMoney;
    }

    public void setWeekMoney(BigDecimal weekMoney) {
        this.weekMoney = weekMoney;
    }

    public Integer getWeekRate() {
        return weekRate;
    }

    public void setWeekRate(Integer weekRate) {
        this.weekRate = weekRate;
    }

    public Integer getWeekNo() {
        return weekNo;
    }

    public void setWeekNo(Integer weekNo) {
        this.weekNo = weekNo;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }
}