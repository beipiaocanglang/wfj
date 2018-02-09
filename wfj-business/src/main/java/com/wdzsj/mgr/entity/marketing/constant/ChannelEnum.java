package com.wdzsj.mgr.entity.marketing.constant;

/**
 * 投放渠道
 */
public enum ChannelEnum {
    MESSAGE("0", "短信"),
    FRIEND("1", "朋友圈");

    private String code;
    private String value;

    ChannelEnum(String code, String value){
        this.code = code;
        this.value = value;
    }

    public static ChannelEnum getByCode(String code) {
        for (ChannelEnum type : ChannelEnum.values()) {
            if (type.code.equals(code)) {
                return type;
            }
        }
        return null;
    }

    public static ChannelEnum getByValue(String value) {
        for (ChannelEnum type : ChannelEnum.values()) {
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
