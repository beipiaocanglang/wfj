package com.wdzsj.mgr.entity.marketing.vo;

/**
 * 保存标签扩散
 */
public class LabelVo {

    private String labelValue;

    private String isChecked = "";

    private Integer checkedNum = 0;

    public String getLabelValue() {
        return labelValue;
    }

    public void setLabelValue(String labelValue) {
        this.labelValue = labelValue;
    }

    public String getIsChecked() {
        return isChecked;
    }

    public void setIsChecked(String isChecked) {
        this.isChecked = isChecked;
    }

    public Integer getCheckedNum() {
        return checkedNum;
    }

    public void setCheckedNum(Integer checkedNum) {
        this.checkedNum = checkedNum;
    }
}
