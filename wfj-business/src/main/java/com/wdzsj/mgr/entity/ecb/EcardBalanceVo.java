package com.wdzsj.mgr.entity.ecb;

import com.wdzsj.cmn.annotation.ExcelVOAttr;
import com.wdzsj.mgr.entity.cac.CheckAccount;

/**
 * 电子卡余额表查询封装
 */
public class EcardBalanceVo{
    //初期余额
    @ExcelVOAttr(name="初期余额",column="A")
    private Double initialBalance;

    //本期售卡
    @ExcelVOAttr(name="本期售卡",column="B")
    private Double currentSalesCardAmount;

    //本期退卡
    @ExcelVOAttr(name="本期退卡",column="C")
    private Double currentBackCardAmount;

    //本期消费
    @ExcelVOAttr(name="本期消费",column="D")
    private Double currentConsumeAmount;

    //本期退货
    @ExcelVOAttr(name="本期退货",column="E")
    private Double currentReturnAmount;

    //期末余额
    @ExcelVOAttr(name="期末余额",column="F")
    private Double finalBalance;

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

    public Double getCurrentConsumeAmount() {
        return currentConsumeAmount;
    }

    public void setCurrentConsumeAmount(Double currentConsumeAmount) {
        this.currentConsumeAmount = currentConsumeAmount;
    }

    public Double getCurrentReturnAmount() {
        return currentReturnAmount;
    }

    public void setCurrentReturnAmount(Double currentReturnAmount) {
        this.currentReturnAmount = currentReturnAmount;
    }

    public Double getFinalBalance() {
        return finalBalance;
    }

    public void setFinalBalance(Double finalBalance) {
        this.finalBalance = finalBalance;
    }
}
