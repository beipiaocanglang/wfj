package com.wdzsj.mgr.entity.srm;

import com.wdzsj.cmn.base.BaseEntity;

//-- id, name, ip, sftwe, cpu, stor, mem, ntwk_crd, fram, envir, rmrk, creat_tm, updt_tm, enab_flag
public class Srvr extends BaseEntity{
	//       
	private String name;
	 //       
    private String ip;
	//
	private String sftwe;
	private String cpu;
	 //       
   private String stor;
	//
	private String mem;
	private String ntwkCrd;
	 //       
   private String fram;
	//
	private String envir;
	private String rmrk;
	
	public Srvr(){
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getSftwe() {
		return sftwe;
	}

	public void setSftwe(String sftwe) {
		this.sftwe = sftwe;
	}

	public String getCpu() {
		return cpu;
	}

	public void setCpu(String cpu) {
		this.cpu = cpu;
	}

	public String getStor() {
		return stor;
	}

	public void setStor(String stor) {
		this.stor = stor;
	}

	public String getMem() {
		return mem;
	}

	public void setMem(String mem) {
		this.mem = mem;
	}

	public String getNtwkCrd() {
		return ntwkCrd;
	}

	public void setNtwkCrd(String ntwkCrd) {
		this.ntwkCrd = ntwkCrd;
	}

	public String getFram() {
		return fram;
	}

	public void setFram(String fram) {
		this.fram = fram;
	}

	public String getEnvir() {
		return envir;
	}

	public void setEnvir(String envir) {
		this.envir = envir;
	}

	public String getRmrk() {
		return rmrk;
	}

	public void setRmrk(String rmrk) {
		this.rmrk = rmrk;
	}
}
