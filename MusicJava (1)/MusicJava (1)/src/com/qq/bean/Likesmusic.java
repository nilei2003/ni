package com.qq.bean;

public class Likesmusic {
	int lid;
	int uid;
	int mid;
	

	public Likesmusic() {
		super();
	}
	public Likesmusic(int lid, int uid, int mid) {
		super();
		this.lid = lid;
		this.uid = uid;
		this.mid = mid;
	}
	@Override
	public String toString() {
		return "Likesmusic [lid=" + lid + ", uid=" + uid + ", mid=" + mid + "]";
	}
	public int getLid() {
		return lid;
	}
	public void setLid(int lid) {
		this.lid = lid;
	}
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public int getMid() {
		return mid;
	}
	public void setMid(int mid) {
		this.mid = mid;
	}
	
	
}
