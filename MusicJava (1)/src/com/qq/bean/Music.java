package com.qq.bean;

public class Music {
	
	public int mid;
	public String musics;
	public String mphoto;
	public String lyrlce;
	public String mname;
	public int sid;
	public int playvolume;
	public String  eid;
	public String  mdate;
	public String  mbj;
	public int  statusmv;
	public int yesnopay;
	public int moneypay;
	public int num;
	
	
	
	
	public Music() {
		super();
	}
	public Music(int mid, String musics, String mphoto, String lyrlce, String mname, int sid, int playvolume,
			String eid, String mdate, String mbj, int statusmv, int yesnopay, int moneypay, int num) {
		super();
		this.mid = mid;
		this.musics = musics;
		this.mphoto = mphoto;
		this.lyrlce = lyrlce;
		this.mname = mname;
		this.sid = sid;
		this.playvolume = playvolume;
		this.eid = eid;
		this.mdate = mdate;
		this.mbj = mbj;
		this.statusmv = statusmv;
		this.yesnopay = yesnopay;
		this.moneypay = moneypay;
		this.num = num;
	}
	@Override
	public String toString() {
		return "Music [mid=" + mid + ", musics=" + musics + ", mphoto=" + mphoto + ", lyrlce=" + lyrlce + ", mname="
				+ mname + ", sid=" + sid + ", playvolume=" + playvolume + ", eid=" + eid + ", mdate=" + mdate + ", mbj="
				+ mbj + ", statusmv=" + statusmv + ", yesnopay=" + yesnopay + ", moneypay=" + moneypay + ", num=" + num
				+ "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((eid == null) ? 0 : eid.hashCode());
		result = prime * result + ((lyrlce == null) ? 0 : lyrlce.hashCode());
		result = prime * result + ((mbj == null) ? 0 : mbj.hashCode());
		result = prime * result + ((mdate == null) ? 0 : mdate.hashCode());
		result = prime * result + mid;
		result = prime * result + ((mname == null) ? 0 : mname.hashCode());
		result = prime * result + moneypay;
		result = prime * result + ((mphoto == null) ? 0 : mphoto.hashCode());
		result = prime * result + ((musics == null) ? 0 : musics.hashCode());
		result = prime * result + num;
		result = prime * result + playvolume;
		result = prime * result + sid;
		result = prime * result + statusmv;
		result = prime * result + yesnopay;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Music other = (Music) obj;
		if (eid == null) {
			if (other.eid != null)
				return false;
		} else if (!eid.equals(other.eid))
			return false;
		if (lyrlce == null) {
			if (other.lyrlce != null)
				return false;
		} else if (!lyrlce.equals(other.lyrlce))
			return false;
		if (mbj == null) {
			if (other.mbj != null)
				return false;
		} else if (!mbj.equals(other.mbj))
			return false;
		if (mdate == null) {
			if (other.mdate != null)
				return false;
		} else if (!mdate.equals(other.mdate))
			return false;
		if (mid != other.mid)
			return false;
		if (mname == null) {
			if (other.mname != null)
				return false;
		} else if (!mname.equals(other.mname))
			return false;
		if (moneypay != other.moneypay)
			return false;
		if (mphoto == null) {
			if (other.mphoto != null)
				return false;
		} else if (!mphoto.equals(other.mphoto))
			return false;
		if (musics == null) {
			if (other.musics != null)
				return false;
		} else if (!musics.equals(other.musics))
			return false;
		if (num != other.num)
			return false;
		if (playvolume != other.playvolume)
			return false;
		if (sid != other.sid)
			return false;
		if (statusmv != other.statusmv)
			return false;
		if (yesnopay != other.yesnopay)
			return false;
		return true;
	}
	public int getMid() {
		return mid;
	}
	public void setMid(int mid) {
		this.mid = mid;
	}
	public String getMusics() {
		return musics;
	}
	public void setMusics(String musics) {
		this.musics = musics;
	}
	public String getMphoto() {
		return mphoto;
	}
	public void setMphoto(String mphoto) {
		this.mphoto = mphoto;
	}
	public String getLyrlce() {
		return lyrlce;
	}
	public void setLyrlce(String lyrlce) {
		this.lyrlce = lyrlce;
	}
	public String getMname() {
		return mname;
	}
	public void setMname(String mname) {
		this.mname = mname;
	}
	public int getSid() {
		return sid;
	}
	public void setSid(int sid) {
		this.sid = sid;
	}
	public int getPlayvolume() {
		return playvolume;
	}
	public void setPlayvolume(int playvolume) {
		this.playvolume = playvolume;
	}
	public String getEid() {
		return eid;
	}
	public void setEid(String eid) {
		this.eid = eid;
	}
	public String getMdate() {
		return mdate;
	}
	public void setMdate(String mdate) {
		this.mdate = mdate;
	}
	public String getMbj() {
		return mbj;
	}
	public void setMbj(String mbj) {
		this.mbj = mbj;
	}
	public int getStatusmv() {
		return statusmv;
	}
	public void setStatusmv(int statusmv) {
		this.statusmv = statusmv;
	}
	public int getYesnopay() {
		return yesnopay;
	}
	public void setYesnopay(int yesnopay) {
		this.yesnopay = yesnopay;
	}
	public int getMoneypay() {
		return moneypay;
	}
	public void setMoneypay(int moneypay) {
		this.moneypay = moneypay;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	
	
	
	

}
