package com.qianfeng.service;

import com.qianfeng.domain.User;
import com.qianfeng.service.impl.UserException;

public interface UserService {
	public int registerUser(User user)throws UserException;

	public void activeUser(String activecode) throws UserException;

	public User userLogin(String name, String password) throws UserException;

	public void modifyUser(User user) throws UserException;
}
