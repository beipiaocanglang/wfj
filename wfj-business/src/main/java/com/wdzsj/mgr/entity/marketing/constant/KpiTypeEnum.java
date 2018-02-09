package com.wdzsj.mgr.entity.marketing.constant;

/**
 * kpi类型
 */
public enum  KpiTypeEnum {
    SELLA_MOUNT("1",  "销售额"),
    PASSENGER_FLOW("2",  "客流"),
    GROSS_PROFIT("3", "毛利");

    private String code;
    private String value;

    KpiTypeEnum(String code, String value){
        this.code = code;
        this.value = value;
    }

    public static KpiTypeEnum getByCode(String code) {
        for (KpiTypeEnum type : KpiTypeEnum.values()) {
            if (type.code.equals(code)) {
                return type;
            }
        }
        return null;
    }

    public static KpiTypeEnum getByValue(String value) {
        for (KpiTypeEnum type : KpiTypeEnum.values()) {
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
