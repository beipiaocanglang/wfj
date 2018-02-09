package com.wdzsj.cmn.db;

import java.io.Serializable;
@SuppressWarnings("all")
public class DimMemberSale  implements Serializable{
	//rowkey字段
	private String rowkey;
	//年龄段
	private  String age_group;
	
	//开卡时间
	private  String cardtime;
	//会员状态
	private  String cid_status;
	//市
	private  String city;
	//生命周期
	private  String clifestage;
	//学历
	private  String educ;
	//总累计活动积分
	private  String hdsum;
	//总累计积分
	private  String jfsum;
	//婚姻状况
	private  String marry;
	//门店号
	private  String mdh;
	//openid
	private  String openid;
	//电话
	private  String phone;
	//会员卡号
	private  String cid;
	//性别
	private  String sex;
	//省
	private  String province;
	//市
	private  String region;
	//价值度（3M）
	private  String rfvcluster_3m;
	//价值度（6M）
	private  String rfvcluster_6m;
	//价值度（12M）
	private  String rfvcluster_12m;
	//品牌号
	private String pp;
	//总累计消费
	private  String xfsum;
	//职业
	private  String work;
	//流失预警
	private  String prewarning;
	//折扣敏感度
	private  String sen_discount;
	//
	private String standard_poname;
	private String cpp;
	private String csj;
	private String cmoney;
	
	public String getAge_group() {
		return age_group;
	}
	public void setAge_group(String age_group) {
		this.age_group = age_group;
	}
	public String getCardtime() {
		return cardtime;
	}
	public void setCardtime(String cardtime) {
		this.cardtime = cardtime;
	}
	public String getCid_status() {
		return cid_status;
	}
	public void setCid_status(String cid_status) {
		this.cid_status = cid_status;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getClifestage() {
		return clifestage;
	}
	public void setClifestage(String clifestage) {
		this.clifestage = clifestage;
	}
	public String getEduc() {
		return educ;
	}
	public void setEduc(String educ) {
		this.educ = educ;
	}
	public String getHdsum() {
		return hdsum;
	}
	public void setHdsum(String hdsum) {
		this.hdsum = hdsum;
	}
	public String getJfsum() {
		return jfsum;
	}
	public void setJfsum(String jfsum) {
		this.jfsum = jfsum;
	}
	public String getMarry() {
		return marry;
	}
	public void setMarry(String marry) {
		this.marry = marry;
	}
	public String getMdh() {
		return mdh;
	}
	public void setMdh(String mdh) {
		this.mdh = mdh;
	}
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getCid() {
		return cid;
	}
	public void setCid(String cid) {
		this.cid = cid;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public String getRfvcluster_3m() {
		return rfvcluster_3m;
	}
	public void setRfvcluster_3m(String rfvcluster_3m) {
		this.rfvcluster_3m = rfvcluster_3m;
	}
	public String getRfvcluster_6m() {
		return rfvcluster_6m;
	}
	public void setRfvcluster_6m(String rfvcluster_6m) {
		this.rfvcluster_6m = rfvcluster_6m;
	}
	public String getRfvcluster_12m() {
		return rfvcluster_12m;
	}
	public void setRfvcluster_12m(String rfvcluster_12m) {
		this.rfvcluster_12m = rfvcluster_12m;
	}
	public String getStandard_poname() {
		return standard_poname;
	}
	public void setStandard_poname(String standard_poname) {
		this.standard_poname = standard_poname;
	}
	public String getPp() {
		return pp;
	}
	public void setPp(String pp) {
		this.pp = pp;
	}
	public String getXfsum() {
		return xfsum;
	}
	public void setXfsum(String xfsum) {
		this.xfsum = xfsum;
	}
	public String getWork() {
		return work;
	}
	public void setWork(String work) {
		this.work = work;
	}
	public String getPrewarning() {
		return prewarning;
	}
	public void setPrewarning(String prewarning) {
		this.prewarning = prewarning;
	}
	public String getSen_discount() {
		return sen_discount;
	}
	public void setSen_discount(String sen_discount) {
		this.sen_discount = sen_discount;
	}
	public String getCpp() {
		return cpp;
	}
	public void setCpp(String cpp) {
		this.cpp = cpp;
	}
	public String getCsj() {
		return csj;
	}
	public void setCsj(String csj) {
		this.csj = csj;
	}
	public String getCmoney() {
		return cmoney;
	}
	public void setCmoney(String cmoney) {
		this.cmoney = cmoney;
	}
	public String getRowkey() {
		return rowkey;
	}
	public void setRowkey(String rowkey) {
		this.rowkey = rowkey;
	}
}
