package com.qq.bean;

public class playlistdetals {

	int pdid;
	String pid;
	int	mid;
	
	
	
	@Override
	public String toString() {
		return "playlistdetals [pdid=" + pdid + ", pid=" + pid + ", mid=" + mid + "]";
	}
	public playlistdetals() {
		super();
	}
	public playlistdetals(int pdid, String pid, int mid) {
		super();
		this.pdid = pdid;
		this.pid = pid;
		this.mid = mid;
	}
	public int getPdid() {
		return pdid;
	}
	public void setPdid(int pdid) {
		this.pdid = pdid;
	}
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public int getMid() {
		return mid;
	}
	public void setMid(int mid) {
		this.mid = mid;
	}
	
	
	
	
}
