package com.qq.bean;

public class Collect {
	int cid;
	int uid;
	int likecusss;
	int	mid;
	int mvid;
	
	
	
	
	@Override
	public String toString() {
		return "Collect [cid=" + cid + ", uid=" + uid + ", likecusss=" + likecusss + ", mid=" + mid + ", mvid=" + mvid
				+ "]";
	}
	public Collect() {
		super();
	}
	public Collect(int cid, int uid, int likecusss, int mid, int mvid) {
		super();
		this.cid = cid;
		this.uid = uid;
		this.likecusss = likecusss;
		this.mid = mid;
		this.mvid = mvid;
	}
	public int getCid() {
		return cid;
	}
	public void setCid(int cid) {
		this.cid = cid;
	}
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public int getLikecusss() {
		return likecusss;
	}
	public void setLikecusss(int likecusss) {
		this.likecusss = likecusss;
	}
	public int getMid() {
		return mid;
	}
	public void setMid(int mid) {
		this.mid = mid;
	}
	public int getMvid() {
		return mvid;
	}
	public void setMvid(int mvid) {
		this.mvid = mvid;
	}
	
	
}
