package com.wdzsj.mgr.entity.ttr;

import com.wdzsj.cmn.annotation.ExcelVOAttr;

public class SecondLevelTotalTableVo extends CardActivation {

    @ExcelVOAttr(name="销售日期",column="A")
    private String payFinishTime;

    @ExcelVOAttr(name="订单号",column="B")
    private String transId;

    @ExcelVOAttr(name="订单金额",column="C")
    private String price;

    @ExcelVOAttr(name="顾客手机号",column="D")
    private String phoneNo;

    @ExcelVOAttr(name="是否生成发票",column="E")
    private String isMakeInvoice = "待定中";

    private int totalNum;

    @Override
    public String getPayFinishTime() {
        return payFinishTime;
    }
    @Override
    public void setPayFinishTime(String payFinishTime) {
        this.payFinishTime = payFinishTime;
    }
    @Override
    public String getTransId() {
        return transId;
    }
    @Override
    public void setTransId(String transId) {
        this.transId = transId;
    }
    @Override
    public String getPrice() {
        return price;
    }
    @Override
    public void setPrice(String price) {
        this.price = price;
    }
    @Override
    public String getPhoneNo() {
        return phoneNo;
    }
    @Override
    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }
    public String getIsMakeInvoice() {
        return isMakeInvoice;
    }
    public void setIsMakeInvoice(String isMakeInvoice) {
        this.isMakeInvoice = isMakeInvoice;
    }

    public int getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(int totalNum) {
        this.totalNum = totalNum;
    }
}
