package com.sunnyinfo.service.impl;

import com.sunnyinfo.dao.UserDao;
import com.sunnyinfo.dao.impl.UserDaoImpl;
import com.sunnyinfo.javaBean.Users;
import com.sunnyinfo.service.UserService;

public class UserServiceImpl implements UserService {
	UserDao ud = new UserDaoImpl();
	@Override
	public int registerUser(Users user) {
		// TODO Auto-generated method stub
		int num = ud.register(user);
		return num;
	}
	@Override
	public Users loginUser(String name, String pwd) {
		// TODO Auto-generated method stub
		Users user = ud.loginUser(name, pwd);
		return user;
	}
	
}
