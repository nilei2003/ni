package com.qq.bean;

public class playlist {

	String pid;
	String uid;
	String	pname;
	String	pphoto;
	
	
	
	@Override
	public String toString() {
		return "playlist [pid=" + pid + ", uid=" + uid + ", pname=" + pname + ", pphoto=" + pphoto + "]";
	}
	public playlist() {
		super();
	}
	public playlist(String pid, String uid, String pname, String pphoto) {
		super();
		this.pid = pid;
		this.uid = uid;
		this.pname = pname;
		this.pphoto = pphoto;
	}
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public String getPphoto() {
		return pphoto;
	}
	public void setPphoto(String pphoto) {
		this.pphoto = pphoto;
	}
	
	
	
}
