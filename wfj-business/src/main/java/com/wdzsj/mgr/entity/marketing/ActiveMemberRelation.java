package com.wdzsj.mgr.entity.marketing;

/**
 * 活动会员关联表
 */
public class ActiveMemberRelation {
    /**
     * 活动表id
     * 活动表 与 活动会员关联表  是一对多关系
     * 反之多对一
     */
    private Long actId;

    /**
     * 会员表cid
     */
    private String cid;

    public Long getActId() {
        return actId;
    }

    public void setActId(Long actId) {
        this.actId = actId;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid == null ? null : cid.trim();
    }
}