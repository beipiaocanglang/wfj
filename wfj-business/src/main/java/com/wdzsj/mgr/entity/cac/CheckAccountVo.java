package com.wdzsj.mgr.entity.cac;

import com.wdzsj.cmn.annotation.ExcelVOAttr;

/**
 * 门店消费报表
 */
public class CheckAccountVo extends CheckAccount{
	//postid
	@ExcelVOAttr(name="收款机号",column="A")
	private java.lang.String posid;
	//交易笔数
	@ExcelVOAttr(name="交易笔数",column="B")
	private String jiaoyibishu;
	//消费金额
	@ExcelVOAttr(name="消费金额",column="C")
	private String xiaofiejinge;
	//退货金额
	@ExcelVOAttr(name="退货金额",column="D")
	private String tuihuojine;
	//消费合计
	@ExcelVOAttr(name="消费合计",column="E")
	private String xiaofeiheji;

	private int totalNum;

	public String getJiaoyibishu() {
		return jiaoyibishu;
	}
	public void setJiaoyibishu(String jiaoyibishu) {
		this.jiaoyibishu = jiaoyibishu;
	}
	public String getXiaofiejinge() {
		return xiaofiejinge;
	}
	public void setXiaofiejinge(String xiaofiejinge) {
		this.xiaofiejinge = xiaofiejinge;
	}
	public String getTuihuojine() {
		return tuihuojine;
	}
	public void setTuihuojine(String tuihuojine) {
		this.tuihuojine = tuihuojine;
	}
	public String getXiaofeiheji() {
		return xiaofeiheji;
	}
	public void setXiaofeiheji(String xiaofeiheji) {
		this.xiaofeiheji = xiaofeiheji;
	}
	public java.lang.String getPosid() {
		return posid;
	}
	public void setPosid(java.lang.String posid) {
		this.posid = posid;
	}

	public int getTotalNum() {
		return totalNum;
	}

	public void setTotalNum(int totalNum) {
		this.totalNum = totalNum;
	}
}





























