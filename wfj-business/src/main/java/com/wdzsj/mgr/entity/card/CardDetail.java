package com.wdzsj.mgr.entity.card;

import com.wdzsj.cmn.base.BaseEntity;

/**
 * 订单明细实体类
 * @author coco
 *
 */
public class CardDetail extends BaseEntity{
	
	private static final long serialVersionUID = 1L;
	
	private String code = "500";//返回码
	private String msg;//返回消息
	private String responseTime;//响应时间
	private String balance;//余额
	private String saleTime;//购买时间
	private String codeStatus;//卡 code 状态
	private String cardCode;//卡 code
	private String expire;//过期时间
	private String result;//TODO 返回对象？？？
	private String storeName;//使用门店
	private String useTime;//消费日期
	private String useSum;//消费金额
	private String refundType;//交易类型
	private String useGlideNo;//商户交易订单号
	
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
	public String getBalance() {
		return balance;
	}
	public void setBalance(String balance) {
		this.balance = balance;
	}
	public String getSaleTime() {
		return saleTime;
	}
	public void setSaleTime(String saleTime) {
		this.saleTime = saleTime;
	}
	public String getCodeStatus() {
		return codeStatus;
	}
	public void setCodeStatus(String codeStatus) {
		this.codeStatus = codeStatus;
	}
	public String getCardCode() {
		return cardCode;
	}
	public void setCardCode(String cardCode) {
		this.cardCode = cardCode;
	}
	public String getExpire() {
		return expire;
	}
	public void setExpire(String expire) {
		this.expire = expire;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public String getStoreName() {
		return storeName;
	}
	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}
	public String getUseTime() {
		return useTime;
	}
	public void setUseTime(String useTime) {
		this.useTime = useTime;
	}
	public String getUseSum() {
		return useSum;
	}
	public void setUseSum(String useSum) {
		this.useSum = useSum;
	}
	public String getRefundType() {
		return refundType;
	}
	public void setRefundType(String refundType) {
		this.refundType = refundType;
	}
	public String getUseGlideNo() {
		return useGlideNo;
	}
	public void setUseGlideNo(String useGlideNo) {
		this.useGlideNo = useGlideNo;
	}
}

