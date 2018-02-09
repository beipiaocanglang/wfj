package com.wdzsj.mgr.entity.ttr;

import com.wdzsj.cmn.annotation.ExcelVOAttr;

public class CardActiveVo extends CardActivation {

    @ExcelVOAttr(name="卡号",column="A")
    private String cardCode;

    @ExcelVOAttr(name="面值",column="B")
    private String price;

    @ExcelVOAttr(name="顾客手机号",column="C")
    private String phoneNo;

    private int totalNum;

    @Override
    public String getCardCode() {
        return cardCode;
    }
    @Override
    public void setCardCode(String cardCode) {
        this.cardCode = cardCode;
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

    public int getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(int totalNum) {
        this.totalNum = totalNum;
    }
}
