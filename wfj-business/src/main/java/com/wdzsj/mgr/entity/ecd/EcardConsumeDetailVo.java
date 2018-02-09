package com.wdzsj.mgr.entity.ecd;

import com.wdzsj.cmn.annotation.ExcelVOAttr;
import com.wdzsj.mgr.entity.cac.CheckAccount;

import java.util.Date;

/**
 * 电子卡消费明细报表查询的封装pojo
 */
public class EcardConsumeDetailVo extends CheckAccount {
    //卡号
    @ExcelVOAttr(name="卡号",column="A")
    private String code;

    //交易时间
    @ExcelVOAttr(name="交易时间",column="B")
    private String time;

    private Date transtime;

    //交易流水号
    @ExcelVOAttr(name="交易流水号",column="C")
    private String transid;

    //交易金额
    @ExcelVOAttr(name="交易金额",column="D")
    private Double transPrice;

    //余额
    @ExcelVOAttr(name="余额",column="E")
    private Double balance = 0.0;

    //消费门店
    @ExcelVOAttr(name="消费门店",column="F")
    private String storename;

    @Override
    public String getCode() {
        return code;
    }
    @Override
    public void setCode(String code) {
        this.code = code;
    }
    public String getTime() {
        return time;
    }
    public void setTime(String time) {
        this.time = time;
    }
    @Override
    public Date getTranstime() {
        return transtime;
    }
    @Override
    public void setTranstime(Date transtime) {
        this.transtime = transtime;
    }
    @Override
    public String getTransid() {
        return transid;
    }
    @Override
    public void setTransid(String transid) {
        this.transid = transid;
    }
    public Double getTransPrice() {
        return transPrice;
    }
    public void setTransPrice(Double transPrice) {
        this.transPrice = transPrice;
    }
    public Double getBalance() {
        return balance;
    }
    public void setBalance(Double balance) {
        this.balance = balance;
    }
    @Override
    public String getStorename() {
        return storename;
    }
    @Override
    public void setStorename(String storename) {
        this.storename = storename;
    }
}
