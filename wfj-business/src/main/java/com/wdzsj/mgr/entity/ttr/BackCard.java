package com.wdzsj.mgr.entity.ttr;

import com.wdzsj.cmn.base.BaseEntity;

public class BackCard extends BaseEntity {

    private String openid;
    private String contactName;
    private String contactPhone;
    private String orderId;
    private String transId;
    private String cardList;
    private String cardId;
    private String cardCode;
    private String payFinishTime;
    private String price;

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getTransId() {
        return transId;
    }

    public void setTransId(String transId) {
        this.transId = transId;
    }

    public String getCardList() {
        return cardList;
    }

    public void setCardList(String cardList) {
        this.cardList = cardList;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public String getCardCode() {
        return cardCode;
    }

    public void setCardCode(String cardCode) {
        this.cardCode = cardCode;
    }

    public String getPayFinishTime() {
        return payFinishTime;
    }

    public void setPayFinishTime(String payFinishTime) {
        this.payFinishTime = payFinishTime;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
