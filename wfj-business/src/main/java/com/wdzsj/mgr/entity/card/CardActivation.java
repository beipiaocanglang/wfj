package com.wdzsj.mgr.entity.card;

import org.apache.commons.lang.StringUtils;
import com.wdzsj.cmn.base.BaseEntity;

/**
 * 购卡激活 实体类
 * @author coco
 *
 */
public class CardActivation extends BaseEntity{
	
	private static final long serialVersionUID = 1L;
	
	private String orderId;//商户订单号
	private String transId;//交易订单号
	private String payFinishTime;//付款成功时间
	private String totalPrice;//总价
	private String openid;//付款人openid
	private String unionid;//付款人unionid
	private String corpUserId;//合作方用户id（如有）
	private String phoneNo;//手机号
	private String isChatRoom;//是否群发
	private String cardId;//卡类型
	private String price;//单价
	private String cardCode;//code
	private String jsonData;//JSON数据

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
	public String getPayFinishTime() {
		return payFinishTime;
	}
	public void setPayFinishTime(String payFinishTime) {
		if(StringUtils.isNotBlank(payFinishTime) && payFinishTime.endsWith(".0"))
			payFinishTime = payFinishTime.substring(0, payFinishTime.length() -2);
		this.payFinishTime = payFinishTime;
	}
	public String getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(String totalPrice) {
		this.totalPrice = totalPrice;
	}
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	public String getUnionid() {
		return unionid;
	}
	public void setUnionid(String unionid) {
		this.unionid = unionid;
	}
	public String getCorpUserId() {
		return corpUserId;
	}
	public void setCorpUserId(String corpUserId) {
		this.corpUserId = corpUserId;
	}
	public String getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	public String getIsChatRoom() {
		return isChatRoom;
	}
	public void setIsChatRoom(String isChatRoom) {
		this.isChatRoom = isChatRoom;
	}
	public String getCardId() {
		return cardId;
	}
	public void setCardId(String cardId) {
		this.cardId = cardId;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getCardCode() {
		return cardCode;
	}
	public void setCardCode(String cardCode) {
		this.cardCode = cardCode;
	}
	public String getJsonData() {
		return jsonData;
	}
	public void setJsonData(String jsonData) {
		this.jsonData = jsonData;
	}
}

