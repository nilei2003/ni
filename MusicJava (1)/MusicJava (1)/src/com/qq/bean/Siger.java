package com.qq.bean;

public class Siger {
	public Integer sid;
	public String sname;
	public String sphoto;
	public String sbrief;
	
	
	
	public Siger() {
		super();
	}
	public Siger(Integer sid, String sname, String sphoto, String sbrief) {
		super();
		this.sid = sid;
		this.sname = sname;
		this.sphoto = sphoto;
		this.sbrief = sbrief;
	}
	@Override
	public String toString() {
		return "Siger [sid=" + sid + ", sname=" + sname + ", sphoto=" + sphoto + ", sbrief=" + sbrief + "]";
	}
	public Integer getSid() {
		return sid;
	}
	public void setSid(Integer sid) {
		this.sid = sid;
	}
	public String getSname() {
		return sname;
	}
	public void setSname(String sname) {
		this.sname = sname;
	}
	public String getSphoto() {
		return sphoto;
	}
	public void setSphoto(String sphoto) {
		this.sphoto = sphoto;
	}
	public String getSbrief() {
		return sbrief;
	}
	public void setSbrief(String sbrief) {
		this.sbrief = sbrief;
	}
	
	

}
