package com.sunnyinfo.service;

import com.sunnyinfo.javaBean.Users;

public interface UserService {

	int registerUser(Users user);

	Users loginUser(String name, String pwd);
	
}
