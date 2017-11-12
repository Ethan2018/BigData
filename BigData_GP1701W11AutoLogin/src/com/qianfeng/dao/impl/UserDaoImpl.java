package com.qianfeng.dao.impl;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import com.qianfeng.dao.UserDao;
import com.qianfeng.domain.User;
import com.qianfeng.util.C3P0Util;

public class UserDaoImpl implements UserDao {
	@Override
	public User findUserByNameAndPwd(String name, String password) throws SQLException {
		QueryRunner qRunner = new QueryRunner(C3P0Util.getDataSource());
		
		
		return qRunner.query("select * from user where name=? and pwd=?", new BeanHandler<User>(User.class),name,password);
	}
}
