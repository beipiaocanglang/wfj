package com.wdzsj.mgr.entity.scb;

import com.wdzsj.cmn.annotation.ExcelVOAttr;

import java.util.Date;

/**
 * 售卡余额查询表封装
 */
public class SellCardBalanceVo {
    //初期余额
    @ExcelVOAttr(name="初期余额",column="A")
    private Double initialBalance;

    //本期售卡
    @ExcelVOAttr(name="本期售卡",column="B")
    private Double currentSalesCardAmount;

    //本期退卡
    @ExcelVOAttr(name="本期退卡",column="C")
    private Double currentBackCardAmount;

    //期末余额
    @ExcelVOAttr(name="期末余额",column="D")
    private Double finalBalance;

    private Date transtime;

    //初期余额的总金额
    private Double totalPrice;

    //从数据库中查出的 根据时间排序后  sum 后 price
    private Double groupByTransTimePrice;

    public Double getInitialBalance() {
        return initialBalance;
    }

    public void setInitialBalance(Double initialBalance) {
        this.initialBalance = initialBalance;
    }

    public Double getCurrentSalesCardAmount() {
        return currentSalesCardAmount;
    }

    public void setCurrentSalesCardAmount(Double currentSalesCardAmount) {
        this.currentSalesCardAmount = currentSalesCardAmount;
    }

    public Double getCurrentBackCardAmount() {
        return currentBackCardAmount;
    }

    public void setCurrentBackCardAmount(Double currentBackCardAmount) {
        this.currentBackCardAmount = currentBackCardAmount;
    }

    public Double getFinalBalance() {
        return finalBalance;
    }

    public void setFinalBalance(Double finalBalance) {
        this.finalBalance = finalBalance;
    }

    public Date getTranstime() {
        return transtime;
    }

    public void setTranstime(Date transtime) {
        this.transtime = transtime;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Double getGroupByTransTimePrice() {
        return groupByTransTimePrice;
    }

    public void setGroupByTransTimePrice(Double groupByTransTimePrice) {
        this.groupByTransTimePrice = groupByTransTimePrice;
    }
}
