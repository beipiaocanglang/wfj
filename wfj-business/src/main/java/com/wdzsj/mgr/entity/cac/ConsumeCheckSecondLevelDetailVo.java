package com.wdzsj.mgr.entity.cac;

import com.wdzsj.cmn.annotation.ExcelVOAttr;

/**
 * 二级消费核对表
 */
public class ConsumeCheckSecondLevelDetailVo extends CheckAccount {

    //门店名称
    @ExcelVOAttr(name="门店名称",column="A")
    private String storename;

    //微众核销数
    @ExcelVOAttr(name="微众核销数",column="B")
    private Double amount = 0.0;

    //门店消费数
    @ExcelVOAttr(name="门店消费数",column="C")
    private Double consumeNum = 0.0;

    //差额
    @ExcelVOAttr(name="差额",column="D")
    private Double diffAmount = 0.0;

    private int totalNum;

    @Override
    public String getStorename() {
        return storename;
    }
    @Override
    public void setStorename(String storename) {
        this.storename = storename;
    }
    public Double getAmount() {
        return amount;
    }
    public void setAmount(Double amount) {
        this.amount = amount;
    }
    public Double getConsumeNum() {
        return consumeNum;
    }
    public void setConsumeNum(Double consumeNum) {
        this.consumeNum = consumeNum;
    }
    public Double getDiffAmount() {
        return diffAmount;
    }
    public void setDiffAmount(Double diffAmount) {
        this.diffAmount = diffAmount;
    }

    public int getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(int totalNum) {
        this.totalNum = totalNum;
    }
}
