package com.wdzsj.mgr.entity.ttr;

import com.wdzsj.cmn.annotation.ExcelVOAttr;

import java.util.Date;

public class TotalTableVo extends CardActivation{

    @ExcelVOAttr(name="日期",column="A")
    private String payTime;

    @ExcelVOAttr(name="售卡合计",column="B")
    private Double activeCardPrice = 0.0;

    @ExcelVOAttr(name="退款合计",column="C")
    private Double backCardPrice = 0.0;

    @ExcelVOAttr(name="存退款合计",column="D")
    private Double diffAmount = 0.0;

    public String getPayTime() {
        return payTime;
    }
    public void setPayTime(String payTime) {
        this.payTime = payTime;
    }
    public Double getActiveCardPrice() {
        return activeCardPrice;
    }
    public void setActiveCardPrice(Double activeCardPrice) {
        this.activeCardPrice = activeCardPrice;
    }
    public Double getBackCardPrice() {
        return backCardPrice;
    }
    public void setBackCardPrice(Double backCardPrice) {
        this.backCardPrice = backCardPrice;
    }
    public Double getDiffAmount() {
        return diffAmount;
    }
    public void setDiffAmount(Double diffAmount) {
        this.diffAmount = diffAmount;
    }
}
