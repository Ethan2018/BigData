package com.qianfeng.util;

import java.sql.Connection;
import java.sql.SQLException;

public class ManagerThreadLocal {
	private static ThreadLocal<Connection> local = new ThreadLocal<Connection>();
	private static Connection connection = null;
	//获取连接对象
	public static Connection getConnection() {
		//从local中获取连接对象
		connection = local.get();
		
		//第一次使用这个方法的时候,local里面还没有连接对象,所以要先创创建一个
		if (connection == null) {
			try {
				connection = C3P0Util.getConnection();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			local.set(connection);
		}
		
		return connection;
	}
	
	//手动管理事务
	//打开事务
	public static void startTransaction() {
		try {
			connection = getConnection();
			connection.setAutoCommit(false);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//回滚操作
	public  static void  rollbackTransaction() {
		try {
			connection.rollback();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//关闭连接
	public static void closeTransaction() {
		//将连接放回连接池
		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//将连接从local中删除
		local.remove();
	}
	
	//提交事务
	public static void commitTransaction() {
		try {
			connection.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
