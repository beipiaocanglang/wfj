package com.wdzsj.mgr.entity.card;

import com.wdzsj.cmn.base.BaseEntity;

/**
 * 订单实体类
 * @author coco
 *
 */
public class CardOrder extends BaseEntity{
	
	private static final long serialVersionUID = 1L;
	
	private String code = "500";//返回码
	private String msg;//返回消息
	private String responseTime;//响应时间
	private String openid;//交易人 openid
	private String balance;////余额
	private String cardName;//卡名称
	private String cardId;//卡类型 Id
	private String cardCode;//卡 code
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getResponseTime() {
		return responseTime;
	}
	public void setResponseTime(String responseTime) {
		this.responseTime = responseTime;
	}
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	public String getBalance() {
		return balance;
	}
	public void setBalance(String balance) {
		this.balance = balance;
	}
	public String getCardName() {
		return cardName;
	}
	public void setCardName(String cardName) {
		this.cardName = cardName;
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
}

