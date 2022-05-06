package com.qq.bean;

public class Sepcial {
	public String eid;
	public String ephoto; 
	public String ename; 
	public String ebrief; 
	public int emoney;
	public Sepcial(String eid, String ephoto, String ename, String ebrief, int emoney) {
		super();
		this.eid = eid;
		this.ephoto = ephoto;
		this.ename = ename;
		this.ebrief = ebrief;
		this.emoney = emoney;
	}
	public Sepcial() {
		super();
	}
	public String getEid() {
		return eid;
	}
	public void setEid(String eid) {
		this.eid = eid;
	}
	public String getEphoto() {
		return ephoto;
	}
	public void setEphoto(String ephoto) {
		this.ephoto = ephoto;
	}
	public String getEname() {
		return ename;
	}
	public void setEname(String ename) {
		this.ename = ename;
	}
	public String getEbrief() {
		return ebrief;
	}
	public void setEbrief(String ebrief) {
		this.ebrief = ebrief;
	}
	public int getEmoney() {
		return emoney;
	}
	public void setEmoney(int emoney) {
		this.emoney = emoney;
	}
	@Override
	public String toString() {
		return "Sepcial [eid=" + eid + ", ephoto=" + ephoto + ", ename=" + ename + ", ebrief=" + ebrief + ", emoney="
				+ emoney + "]";
	} 
	
	
	

}
