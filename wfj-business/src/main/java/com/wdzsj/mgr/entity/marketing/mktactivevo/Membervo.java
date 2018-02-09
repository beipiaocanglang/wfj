package com.wdzsj.mgr.entity.marketing.mktactivevo;

/**
 * 种子页面的种子会员列表数据
 * author : sunpanhu
 * create time : 2018/2/8 下午5:53
 */
public class Membervo {

    /**
     * 会员id
     */
    private String memberId;
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

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

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
}
