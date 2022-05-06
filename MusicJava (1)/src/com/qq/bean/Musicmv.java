package com.qq.bean;

public class Musicmv {
//	mvid int primary key auto_increment, -- mvid 
//	  mvs varchar(500), -- mv存放路径
//	  mvname varchar(100), -- mv名
//	  mvphoto varchar(500), -- mv封面
//		mid int unique, -- 歌曲id
//		mvbj varchar(50) -- mv标签
	public int mvid;
	public String mvname;
	public String mvphoto;
	public String mvs;
	public int mid;
	public String mvbj;
	public int playvolume;
	
	
	
	@Override
	public String toString() {
		return "Musicmv [mvid=" + mvid + ", mvname=" + mvname + ", mvphoto=" + mvphoto + ", mvs=" + mvs + ", mid=" + mid
				+ ", mvbj=" + mvbj + ", playvolume=" + playvolume + "]";
	}
	public Musicmv() {
		super();
	}
	public Musicmv(int mvid, String mvname, String mvphoto, String mvs, int mid, String mvbj, int playvolume) {
		super();
		this.mvid = mvid;
		this.mvname = mvname;
		this.mvphoto = mvphoto;
		this.mvs = mvs;
		this.mid = mid;
		this.mvbj = mvbj;
		this.playvolume = playvolume;
	}
	public int getMvid() {
		return mvid;
	}
	public void setMvid(int mvid) {
		this.mvid = mvid;
	}
	public String getMvname() {
		return mvname;
	}
	public void setMvname(String mvname) {
		this.mvname = mvname;
	}
	public String getMvphoto() {
		return mvphoto;
	}
	public void setMvphoto(String mvphoto) {
		this.mvphoto = mvphoto;
	}
	public String getMvs() {
		return mvs;
	}
	public void setMvs(String mvs) {
		this.mvs = mvs;
	}
	public int getMid() {
		return mid;
	}
	public void setMid(int mid) {
		this.mid = mid;
	}
	public String getMvbj() {
		return mvbj;
	}
	public void setMvbj(String mvbj) {
		this.mvbj = mvbj;
	}
	public int getPlayvolume() {
		return playvolume;
	}
	public void setPlayvolume(int playvolume) {
		this.playvolume = playvolume;
	}
	
	

}
