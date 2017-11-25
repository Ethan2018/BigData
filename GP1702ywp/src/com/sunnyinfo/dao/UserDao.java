package com.sunnyinfo.dao;

import com.sunnyinfo.entity.User;

public interface UserDao {
	boolean addUser(User user);
	User getUser(String uname);
	User getUser(String uname, String pwd);
}
