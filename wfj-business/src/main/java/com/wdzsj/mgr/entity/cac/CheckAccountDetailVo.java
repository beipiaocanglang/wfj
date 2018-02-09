package com.wdzsj.mgr.entity.cac;

import com.wdzsj.cmn.annotation.ExcelVOAttr;

import java.util.Date;

public class CheckAccountDetailVo extends CheckAccount{
    //卡号
    @ExcelVOAttr(name="卡号",column="A")
    private String code;
    //交易日期
    @ExcelVOAttr(name="交易日期",column="B")
    private String transactionD;
    private Date transactionDate;
    //交易流水号
    @ExcelVOAttr(name="交易流水号",column="C")
    private String transactionNum;
    //交易金额
    @ExcelVOAttr(name="交易金额",column="D")
    private String transactionMoney;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    public String getTransactionD() {
        return transactionD;
    }

    public void setTransactionD(String transactionD) {
        this.transactionD = transactionD;
    }

    public String getTransactionNum() {
        return transactionNum;
    }

    public void setTransactionNum(String transactionNum) {
        this.transactionNum = transactionNum;
    }

    public String getTransactionMoney() {
        return transactionMoney;
    }

    public void setTransactionMoney(String transactionMoney) {
        this.transactionMoney = transactionMoney;
    }
}
