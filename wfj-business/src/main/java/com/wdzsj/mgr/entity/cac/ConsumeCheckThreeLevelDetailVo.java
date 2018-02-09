package com.wdzsj.mgr.entity.cac;

import com.wdzsj.cmn.annotation.ExcelVOAttr;

import java.util.Date;

/**
 * 消费核对表三级
 */
public class ConsumeCheckThreeLevelDetailVo extends CheckAccount {

    //交易时间
    @ExcelVOAttr(name="交易时间",column="A")
    private String time;
    private Date transtime;

    //posid
    @ExcelVOAttr(name="pos机",column="B")
    private String posid;

    //交易流水号
    @ExcelVOAttr(name="交易流水号",column="C")
    private String transid;

    //门店电子卡消费金额
    @ExcelVOAttr(name="门店电子卡消费金额",column="D")
    private Double consumeNum = 0.0;

    //微众核销金额
    @ExcelVOAttr(name="微众核销金额",column="E")
    private Double amount = 0.0;

    //是否平衡(微众银行核销数 == 富基系统消费数 平衡 否则 不平衡)
    @ExcelVOAttr(name="是否平衡",column="F")
    private Boolean isBalance = false;

    //符合条件的总记录数
    private Integer totaleCount;

    @Override
    public Date getTranstime() {
        return transtime;
    }
    @Override
    public void setTranstime(Date transtime) {
        this.transtime = transtime;
    }
    @Override
    public String getPosid() {
        return posid;
    }
    @Override
    public void setPosid(String posid) {
        this.posid = posid;
    }
    @Override
    public String getTransid() {
        return transid;
    }
    @Override
    public void setTransid(String transid) {
        this.transid = transid;
    }
    public String getTime() {
        return time;
    }
    public void setTime(String time) {
        this.time = time;
    }
    public Double getConsumeNum() {
        return consumeNum;
    }
    public void setConsumeNum(Double consumeNum) {
        this.consumeNum = consumeNum;
    }
    public Double getAmount() {
        return amount;
    }
    public void setAmount(Double amount) {
        this.amount = amount;
    }
    public Boolean getBalance() {
        return isBalance;
    }
    public void setBalance(Boolean balance) {
        isBalance = balance;
    }
    public Integer getTotaleCount() {
        return totaleCount;
    }
    public void setTotaleCount(Integer totaleCount) {
        this.totaleCount = totaleCount;
    }
}
