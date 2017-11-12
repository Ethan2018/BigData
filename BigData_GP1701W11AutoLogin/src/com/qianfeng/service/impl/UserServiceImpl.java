package com.qianfeng.service.impl;

import java.sql.SQLException;

import com.qianfeng.dao.UserDao;
import com.qianfeng.dao.impl.UserDaoImpl;
import com.qianfeng.domain.User;
import com.qianfeng.service.UserService;

public class UserServiceImpl implements UserService {
	UserDao uDao = new UserDaoImpl();
	@Override
	public User findUserByNameAndPwd(String name, String password) {
		User user = null;
		try {
			user = uDao.findUserByNameAndPwd(name,password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user;
	}
}
