package com.qianfeng.dao;

import java.sql.SQLException;

import com.qianfeng.domain.User;

public interface UserDao {
	public int registerUser(User user) throws SQLException;

	public User findUserWithActiveCode(String activecode)throws SQLException;

	public void modifystate(String activecode)throws SQLException;

	public User findUserWithNameAndPwd(String name, String password) throws SQLException;

	public void modifyUser(User user)throws SQLException;
}
