package com.qianfeng.service;

import com.qianfeng.domain.User;

public interface UserService {
	
	//��ѯ��ǰ���û�
	User findUserByNameAndPwd(String name, String password);

}
