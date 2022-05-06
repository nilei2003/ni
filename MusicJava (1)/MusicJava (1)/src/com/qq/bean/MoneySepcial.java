package com.qq.bean;

public class MoneySepcial {
	
	public int msid;
	public String eid;
	public int uid;
	public int getMsid() {
		return msid;
	}
	public void setMsid(int msid) {
		this.msid = msid;
	}
	public String getEid() {
		return eid;
	}
	public void setEid(String eid) {
		this.eid = eid;
	}
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public MoneySepcial(int msid, String eid, int uid) {
		super();
		this.msid = msid;
		this.eid = eid;
		this.uid = uid;
	}
	public MoneySepcial() {
		super();
	}
	@Override
	public String toString() {
		return "MoneySepcial [msid=" + msid + ", eid=" + eid + ", uid=" + uid + "]";
	}
	
	
	

}
