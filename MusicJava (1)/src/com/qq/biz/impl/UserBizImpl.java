package com.qq.biz.impl;

import com.qq.bean.User;
import com.qq.biz.UserBiz;
import com.qq.dao.UserDao;
import com.qq.dao.impl.UserDaoImpl;
import com.qq.util.StringUtils;

public class UserBizImpl implements UserBiz{
	UserDao dao = new UserDaoImpl();
	//登录
	@Override
	public User Login(String uname, String upwd) {
		if(StringUtils.pach(uname,upwd)) {
			return null;
		}
		return dao.login(uname, upwd);
	}
	/**
	 * 注册
	 */
	@Override
	public int add(String uname, String pwd, String email) {
		if(StringUtils.pach(uname,pwd,email)) {
			return 0;
		}
		
		return dao.add(uname, pwd, email);
	}
	
	//添加评论
	@Override
	public int addComment(String uid, String date, String text, int discuss, int mid, int mvid, String eid, String pid,
			int sid) {
		if(StringUtils.pach(uid,date)) {
			return 0;
		}
		return dao.addComment(uid, date, text, discuss, mid, mvid, eid, pid, sid);
	}

	@Override
	public int updateuphoto(String uphoto, String uid) {
		UserDao dao = new UserDaoImpl();
		return dao.updateuphoto(uphoto, uid);
	}
	@Override
	public User getUser(String uid) {
		if(StringUtils.pach(uid)) {
			return null;
		}
		return dao.getUser(uid);
	}
}	
