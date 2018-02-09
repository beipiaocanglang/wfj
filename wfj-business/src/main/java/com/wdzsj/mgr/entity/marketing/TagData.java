package com.wdzsj.mgr.entity.marketing;

public class TagData {
    private Long tagDataId;

    private Long tagId;

    private String dictionaryName;

    private String dictionaryValue;

    private Integer seqNo;

    public Long getTagDataId() {
        return tagDataId;
    }

    public void setTagDataId(Long tagDataId) {
        this.tagDataId = tagDataId;
    }

    public Long getTagId() {
        return tagId;
    }

    public void setTagId(Long tagId) {
        this.tagId = tagId;
    }

    public String getDictionaryName() {
        return dictionaryName;
    }

    public void setDictionaryName(String dictionaryName) {
        this.dictionaryName = dictionaryName == null ? null : dictionaryName.trim();
    }

    public String getDictionaryValue() {
        return dictionaryValue;
    }

    public void setDictionaryValue(String dictionaryValue) {
        this.dictionaryValue = dictionaryValue == null ? null : dictionaryValue.trim();
    }

    public Integer getSeqNo() {
        return seqNo;
    }

    public void setSeqNo(Integer seqNo) {
        this.seqNo = seqNo;
    }
}