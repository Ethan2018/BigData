package com.qianfeng.dao.impl;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import com.qianfeng.dao.UserDao;
import com.qianfeng.domain.User;
import com.qianfeng.util.C3P0Util;

public class UserDaoImpl implements UserDao {

	@Override
	public int registerUser(User user) throws SQLException {
		QueryRunner qRunner = new QueryRunner(C3P0Util.getDataSource());
		
		return qRunner.update("insert into users (username,password,gender,email,telephone,introduce,activeCode,state,registTime) "
				+ "values(?,?,?,?,?,?,?,?,?)",user.getUsername(),user.getPassword(),user.getGender(),user.getEmail(),user.getTelephone(),
				user.getIntroduce(),user.getActiveCode(),user.getState(),user.getRegistTime()
				);
		
	}
	
	@Override
	//根据激活码查询用户
	public User findUserWithActiveCode(String activecode) throws SQLException {
		QueryRunner qRunner = new QueryRunner(C3P0Util.getDataSource());
		
		return  qRunner.query("select * from users where activeCode=?", new BeanHandler<User>(User.class),activecode);
	}
	
	
	//根据激活码修改用户的状态值
	@Override
	public void modifystate(String activecode) throws SQLException {
		QueryRunner qRunner = new QueryRunner(C3P0Util.getDataSource());
		
		qRunner.update("update users set state=? where activeCode=?",1,activecode);
	}
	
	//根据用户名和密码查询用户
	@Override
	public User findUserWithNameAndPwd(String name, String password) throws SQLException {
		QueryRunner qRunner = new QueryRunner(C3P0Util.getDataSource());
		
		return qRunner.query("select * from users where username=? and password=?", new BeanHandler<User>(User.class),name,password);
	}
	
	@Override
	public void modifyUser(User user) throws SQLException {
		QueryRunner qRunner = new QueryRunner(C3P0Util.getDataSource());
		qRunner.update("update users set password=?, gender=?, telephone=? where id=?",user.getPassword(),user.getGender(),user.getTelephone(),user.getId());
		
	}
}
