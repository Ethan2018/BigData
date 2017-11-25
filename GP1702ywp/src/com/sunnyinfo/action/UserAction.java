package com.sunnyinfo.action;


import org.apache.commons.lang.StringUtils;
import org.apache.hadoop.io.MD5Hash;

import com.sunnyinfo.dao.UserDao;
import com.sunnyinfo.dao.impl.UserDaoImpl;
import com.sunnyinfo.entity.User;

public class UserAction extends BaseAction {
	public String name;
	public String pwd;
	public String msg;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	UserDao ud = new UserDaoImpl();
	
	/**
	 * 注册
	 * @return
	 */
	public String register() {
		
		if (StringUtils.isEmpty(name) || StringUtils.isEmpty(pwd)) {
			msg = "用户名或者密码不能为空";
			return "success";
		}
		
		User user = ud.getUser(name);
		if (user == null) {
			User newuser = new User();
			newuser.setName(name);
			newuser.setPassword(MD5Hash.digest(pwd.getBytes()).toString());
			newuser.setSize("10");
			boolean isok = ud.addUser(newuser);
			if (isok) {
				msg = "注册成功";
			} else {
				msg = "注册失败";
			}
		} else {
			msg = "用户已经存在,请直接登录";
		}
		return "success";
		
	}
	
	public String login() {
		if (StringUtils.isEmpty(name) || StringUtils.isEmpty(pwd)) {
			msg = "用户名或者密码不能为空";
			return "success";
		}
		User user = ud.getUser(name, MD5Hash.digest(pwd.getBytes()).toString());
		if (user != null) {
			msg = "登录成功";
			session.put("username", name);
		} else {
			msg = "用户名或者密码错误";
		}
		return "success";
	}
	
	public String logout() {
		session.remove("username");
		msg = "退出成功";
		return "success";
	}
	
}
