package com.wdzsj.mgr.entity.marketing;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 活动表中的渠道ID对应的渠道表
 */
public class Channel {
    /**
     * 渠道ID
     */
    private Long channelId;

    /**
     * 渠道名称
     * 取值：
     *      短信
     *      朋友圈
     */
    private String channelName;

    /**
     * 转化率
     */
    private BigDecimal conversionRate;

    /**
     * 是否包含种子用户0：是、1：否
     */
    private Integer seedUser;

    /**
     * 投放时间
     */
    private Date releaseTime;

    /**
     * 投放结束时间
     */
    private Date releaseEndTime;

    /**
     * 投放人群数量
     */
    private Long peopleNo;

    public Long getChannelId() {
        return channelId;
    }

    public void setChannelId(Long channelId) {
        this.channelId = channelId;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName == null ? null : channelName.trim();
    }

    public BigDecimal getConversionRate() {
        return conversionRate;
    }

    public void setConversionRate(BigDecimal conversionRate) {
        this.conversionRate = conversionRate;
    }

    public Integer getSeedUser() {
        return seedUser;
    }

    public void setSeedUser(Integer seedUser) {
        this.seedUser = seedUser;
    }

    public Date getReleaseTime() {
        return releaseTime;
    }

    public void setReleaseTime(Date releaseTime) {
        this.releaseTime = releaseTime;
    }

    public Date getReleaseEndTime() {
        return releaseEndTime;
    }

    public void setReleaseEndTime(Date releaseEndTime) {
        this.releaseEndTime = releaseEndTime;
    }

    public Long getPeopleNo() {
        return peopleNo;
    }

    public void setPeopleNo(Long peopleNo) {
        this.peopleNo = peopleNo;
    }
}