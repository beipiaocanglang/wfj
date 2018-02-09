package com.wdzsj.mgr.entity.cac;

import java.util.Date;

import com.wdzsj.cmn.base.BaseEntity;
import com.wdzsj.cmn.util.DateUtil;
/**
 * 核销对账
 */
public class CheckAccount extends BaseEntity{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//主键
	private Long id;
	//合作商APP_ID   
	private java.lang.String appid;
	//合作商交易流水   
	
	/*@ExcelVOAttr(name="交易流水号",column="C")*/
	private java.lang.String transid;
	//券码   
	/*@ExcelVOAttr(name="卡号",column="A")*/
	private java.lang.String code;
	//ProgramId   
	private java.lang.String programid;
	//兑换数量   
	private java.lang.String num;
	//产品名   
	private java.lang.String product;
	//兑换时间   
	/*@ExcelVOAttr(name="交易日期",column="B")*/
	private Date transtime;
	//门店号
	private java.lang.String storeid;
	//门店名   
	private java.lang.String storename;
	//区域   
	private java.lang.String area;
	//POS机ID   
	private java.lang.String posid;
	//原价   
	private java.lang.String origprice;
	//活动价   
	private java.lang.String saleprice;
	//核销总价   
	
	/*@ExcelVOAttr(name="交易金额",column="D")*/
	private java.lang.String price;
	//码最大可取数量   
	private java.lang.String cardnum;
	//码最大可取金额   
	private java.lang.String cardamount;
	//码有效期   
	private Date expiretime;
	//码是否过期   
	private java.lang.String expired;
	//码核销状态   
	private java.lang.String cardstatus;
	//券码种类   
	private java.lang.String cardtype;
	//微信openid   
	private java.lang.String openid;
	//卡类型   
	private java.lang.String cardid;
	//卡code   
	private java.lang.String cardcode;

	public void setAppid(java.lang.String value) {
		this.appid = value;
	}
	
	public java.lang.String getAppid() {
		return this.appid;
	}
	
	public void setTransid(java.lang.String value) {
		this.transid = value;
	}
	
	public java.lang.String getTransid() {
		return this.transid;
	}
	
	public void setCode(java.lang.String value) {
		this.code = value;
	}
	
	public java.lang.String getCode() {
		return this.code;
	}
	
	public void setProgramid(java.lang.String value) {
		this.programid = value;
	}
	
	public java.lang.String getProgramid() {
		return this.programid;
	}
	
	public void setNum(java.lang.String value) {
		this.num = value;
	}
	
	public java.lang.String getNum() {
		return this.num;
	}
	
	public void setProduct(java.lang.String value) {
		this.product = value;
	}
	
	public java.lang.String getProduct() {
		return this.product;
	}
	public String getTranstimeString() {
		return DateUtil.formatDatetime(getTranstime());
	}
	public void setTranstimeString(String value) {
		setTranstime(DateUtil.parseDatetime(value));
	}
	
	public void setTranstime(Date value) {
		this.transtime = value;
	}
	
	public Date getTranstime() {
		return this.transtime;
	}
	
	public void setStoreid(java.lang.String value) {
		this.storeid = value;
	}
	
	public java.lang.String getStoreid() {
		return this.storeid;
	}
	
	public void setStorename(java.lang.String value) {
		this.storename = value;
	}
	
	public java.lang.String getStorename() {
		return this.storename;
	}
	
	public void setArea(java.lang.String value) {
		this.area = value;
	}
	
	public java.lang.String getArea() {
		return this.area;
	}
	
	public void setPosid(java.lang.String value) {
		this.posid = value;
	}
	
	public java.lang.String getPosid() {
		return this.posid;
	}
	
	public void setOrigprice(java.lang.String value) {
		this.origprice = value;
	}
	
	public java.lang.String getOrigprice() {
		return this.origprice;
	}
	
	public void setSaleprice(java.lang.String value) {
		this.saleprice = value;
	}
	
	public java.lang.String getSaleprice() {
		return this.saleprice;
	}
	
	public void setPrice(java.lang.String value) {
		this.price = value;
	}
	
	public java.lang.String getPrice() {
		return this.price;
	}
	
	public void setCardnum(java.lang.String value) {
		this.cardnum = value;
	}
	
	public java.lang.String getCardnum() {
		return this.cardnum;
	}
	
	public void setCardamount(java.lang.String value) {
		this.cardamount = value;
	}
	
	public java.lang.String getCardamount() {
		return this.cardamount;
	}
	public String getExpiretimeString() {
		return DateUtil.formatDatetime(getExpiretime());
	}
	public void setExpiretimeString(String value) {
		setExpiretime(DateUtil.parseDatetime(value));
	}
	
	public void setExpiretime(Date value) {
		this.expiretime = value;
	}
	
	public Date getExpiretime() {
		return this.expiretime;
	}
	
	public void setExpired(java.lang.String value) {
		this.expired = value;
	}
	
	public java.lang.String getExpired() {
		return this.expired;
	}
	
	public void setCardstatus(java.lang.String value) {
		this.cardstatus = value;
	}
	
	public java.lang.String getCardstatus() {
		return this.cardstatus;
	}
	
	public void setCardtype(java.lang.String value) {
		this.cardtype = value;
	}
	
	public java.lang.String getCardtype() {
		return this.cardtype;
	}
	
	public void setOpenid(java.lang.String value) {
		this.openid = value;
	}
	
	public java.lang.String getOpenid() {
		return this.openid;
	}
	
	public void setCardid(java.lang.String value) {
		this.cardid = value;
	}
	
	public java.lang.String getCardid() {
		return this.cardid;
	}
	
	public void setCardcode(java.lang.String value) {
		this.cardcode = value;
	}
	
	public java.lang.String getCardcode() {
		return this.cardcode;
	}
}
