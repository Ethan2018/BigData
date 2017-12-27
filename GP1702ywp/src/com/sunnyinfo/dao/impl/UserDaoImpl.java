package com.sunnyinfo.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.sun.org.apache.regexp.internal.recompile;
import com.sunnyinfo.dao.UserDao;
import com.sunnyinfo.entity.User;
import com.sunnyinfo.util.JDBCUtil;

public class UserDaoImpl implements UserDao {

	@Override
	public boolean addUser(User user) {
		// TODO Auto-generated method stub
		Connection conn = JDBCUtil.getConn();
		PreparedStatement ps = null;
		boolean flag = false;
		
		try {
			String sql = "insert into `user` (uname,password,size) values(?,?,?)";
			ps = conn.prepareStatement(sql);
			ps.setString(1, user.getName());
			ps.setString(2, user.getPassword());
			ps.setString(3, user.getSize());
			flag = ps.executeUpdate() > 0 ? true : false;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCUtil.closeConn(conn, ps, null);
		}
		
		return flag;
	}

	@Override
	public User getUser(String uname) {
		// TODO Auto-generated method stub
		Connection conn = JDBCUtil.getConn();
		PreparedStatement ps = null;
		ResultSet rs = null;
		User user = null;
		
		try {
			String sql = "select uid,uname,size from user where uname=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, uname);
			rs = ps.executeQuery();
			while (rs.next()) {
				user = new User();
				user.setUid(rs.getInt("uid"));
				user.setName(rs.getString("uname"));
				user.setSize(rs.getString("size"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCUtil.closeConn(conn, ps, rs);
		}
		
		return user;
	
	}

	@Override
	public User getUser(String uname, String pwd) {
		// TODO Auto-generated method stub
		Connection conn = JDBCUtil.getConn();
		PreparedStatement ps = null;
		ResultSet rs = null;
		User user = null;
		try {
			String sql = "select uid,uname,size from user where uname=? and password=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, uname);
			ps.setString(2, pwd);
			rs = ps.executeQuery();
			while (rs.next()) {
				user = new User();
				user.setUid(rs.getInt("uid"));
				user.setName(rs.getString("uname"));
				user.setSize(rs.getString("size"));
			}
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			JDBCUtil.closeConn(conn, ps, rs);
		}
		return user;
	}



}
