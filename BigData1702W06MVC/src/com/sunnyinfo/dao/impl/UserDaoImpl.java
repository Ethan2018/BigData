package com.sunnyinfo.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import com.sunnyinfo.dao.UserDao;
import com.sunnyinfo.javaBean.Users;
import com.sunnyinfo.util.DBUtil;

public class UserDaoImpl implements UserDao {

	@Override
	public int register(Users user) {
		// TODO Auto-generated method stub
		Connection connection = null;
		PreparedStatement pStatement = null;
		int num = 0;
		try {
			connection = DBUtil.getConnection();
			String sql = "insert into user(name,password,email,birthday) values(?,?,?,?)";
			pStatement = connection.prepareStatement(sql);
			pStatement.setString(1, user.getName());
			pStatement.setString(2, user.getPassword());
			pStatement.setString(3, user.getEmail());
			pStatement.setDate(4, new Date(user.getBirthday().getTime()));
			num = pStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.closeAll(connection, pStatement, null);
		}
		return num;
	}

	@Override
	public Users loginUser(String name, String pwd) {
		// TODO Auto-generated method stub
		Connection connection = null;
		PreparedStatement pStatement = null;
		ResultSet rSet = null;
		Users user = null;
		int num = 0;
		
		try {
			connection = DBUtil.getConnection();
			String sql = "select * from users where name=? and pwd=?";
			pStatement = connection.prepareStatement(sql);
			rSet = pStatement.executeQuery();
			if (rSet.next()) {
				user = new Users();
				user.setId(rSet.getInt("id"));
				user.setName(rSet.getNString("name"));
				user.setPassword(rSet.getString("password"));
				user.setEmail(rSet.getString("email"));
				user.setBirthday(rSet.getDate("birthday"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.closeAll(connection, pStatement, null);
		}
		
		return user;
	}

}
