package com.qq.bean;

public class temporarys {
	
	public String tid;
	public int uid;
	
	
	
	@Override
	public String toString() {
		return "temporarys [tid=" + tid + ", uid=" + uid + "]";
	}
	public temporarys() {
		super();
	}
	public temporarys(String tid, int uid) {
		super();
		this.tid = tid;
		this.uid = uid;
	}
	public String getTid() {
		return tid;
	}
	public void setTid(String tid) {
		this.tid = tid;
	}
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	
	

}
