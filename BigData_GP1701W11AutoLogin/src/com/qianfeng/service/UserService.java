package com.qianfeng.service;

import com.qianfeng.domain.User;

public interface UserService {
	
	//查询当前的用户
	User findUserByNameAndPwd(String name, String password);

}
