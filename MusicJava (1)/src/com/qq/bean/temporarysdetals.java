package com.qq.bean;

public class temporarysdetals {

	public String ttdid;
	public int tid;
	public int mid;
	
	
	@Override
	public String toString() {
		return "temporarysdetals [ttdid=" + ttdid + ", tid=" + tid + ", mid=" + mid + "]";
	}
	public temporarysdetals() {
		super();
	}
	public temporarysdetals(String ttdid, int tid, int mid) {
		super();
		this.ttdid = ttdid;
		this.tid = tid;
		this.mid = mid;
	}
	public String getTtdid() {
		return ttdid;
	}
	public void setTtdid(String ttdid) {
		this.ttdid = ttdid;
	}
	public int getTid() {
		return tid;
	}
	public void setTid(int tid) {
		this.tid = tid;
	}
	public int getMid() {
		return mid;
	}
	public void setMid(int mid) {
		this.mid = mid;
	}
	
	
	
}
