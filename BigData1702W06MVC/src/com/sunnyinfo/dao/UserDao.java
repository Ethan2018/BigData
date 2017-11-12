package com.sunnyinfo.dao;

import com.sunnyinfo.javaBean.Users;

public interface UserDao {

	int register(Users user);

	Users loginUser(String name, String pwd);

}
