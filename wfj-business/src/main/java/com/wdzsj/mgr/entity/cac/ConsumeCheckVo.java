package com.wdzsj.mgr.entity.cac;

import com.wdzsj.cmn.annotation.ExcelVOAttr;

import java.util.Date;

/**
 * 消费核对表的
 */
public class ConsumeCheckVo extends CheckAccount {

    //日期
    @ExcelVOAttr(name="日期",column="A")
    private String time;

    private Date transtime;

    //微众银行核销数
    @ExcelVOAttr(name="微众银行核销数",column="B")
    private Double money;

    //富基系统消费数
    @ExcelVOAttr(name="富基系统消费数",column="C")
    private Double consumeNum = 0.0;

    //是否平衡(微众银行核销数 == 富基系统消费数 平衡 否则 不平衡)
    @ExcelVOAttr(name="是否平衡",column="D")
    private Boolean isBalance = false;

    private int totalNum;

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
    public Double getMoney() {
        return money;
    }
    public void setMoney(Double money) {
        this.money = money;
    }
    public Double getConsumeNum() {
        return consumeNum;
    }
    public void setConsumeNum(Double consumeNum) {
        this.consumeNum = consumeNum;
    }
    public Boolean getBalance() {
        return isBalance;
    }
    public void setBalance(Boolean balance) {
        isBalance = balance;
    }

    public int getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(int totalNum) {
        this.totalNum = totalNum;
    }
}
