package com.qianfeng.dao;

import java.sql.SQLException;

import com.qianfeng.domain.User;

public interface UserDao {

	User findUserByNameAndPwd(String name, String password) throws SQLException;

}
