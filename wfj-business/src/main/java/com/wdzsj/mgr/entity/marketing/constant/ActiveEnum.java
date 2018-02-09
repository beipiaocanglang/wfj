package com.wdzsj.mgr.entity.marketing.constant;

/**
 * 活动状态
 */
public enum ActiveEnum {
    NOTBEGIN("0", "未开始"),
    UNDERWAY("1", "进行中"),
    FINISH("2", "已完成");

    private String code;
    private String value;

    ActiveEnum(String code, String value){
        this.code = code;
        this.value = value;
    }

    public static ActiveEnum getByCode(String code) {
        for (ActiveEnum type : ActiveEnum.values()) {
            if (type.code.equals(code)) {
                return type;
            }
        }
        return null;
    }

    public static ActiveEnum getByValue(String value) {
        for (ActiveEnum type : ActiveEnum.values()) {
            if (type.value.equals(value)) {
                return type;
            }
        }
        return null;
    }

    public String getCode() { return code; }

    public void setCode(String code) { this.code = code; }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
