package com.wdzsj.mgr.entity.marketing.mktactivevo;

import java.util.List;

/**
 * 重构后的种子会员vo
 * author : sunpanhu
 * create time : 2018/2/8 下午5:37
 */
public class SeedMemberVo {
    /**
     * 购买频次
     */
    private String buyRate;
    /**
     * 购买客单价
     */
    private String buyPerTicketSales;
    /**
     * 累计购买金额
     */
    private String buyTotalAmount;
    /**
     * 购买时间
     * 取值：
     *      0：近3个月
     *      1：近6个月
     *      2：近一年
     */
    private Integer buyTime;
    /**
     * 开始时间
     */
    private String startTime;
    /**
     * 结束时间
     */
    private String endTime;
    /**
     * 包含会员数据的集合
     */
    private List<Membervo> memberList;

    public String getBuyRate() {
        return buyRate;
    }

    public void setBuyRate(String buyRate) {
        this.buyRate = buyRate;
    }

    public String getBuyPerTicketSales() {
        return buyPerTicketSales;
    }

    public void setBuyPerTicketSales(String buyPerTicketSales) {
        this.buyPerTicketSales = buyPerTicketSales;
    }

    public String getBuyTotalAmount() {
        return buyTotalAmount;
    }

    public void setBuyTotalAmount(String buyTotalAmount) {
        this.buyTotalAmount = buyTotalAmount;
    }

    public Integer getBuyTime() {
        return buyTime;
    }

    public void setBuyTime(Integer buyTime) {
        this.buyTime = buyTime;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public List<Membervo> getMemberList() {
        return memberList;
    }

    public void setMemberList(List<Membervo> memberList) {
        this.memberList = memberList;
    }
}
