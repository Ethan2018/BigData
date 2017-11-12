package com.sunnyinfo.c3p0;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class C3P0Util {
	//1.创建数据源,系统会自动添加连接
	private static ComboPooledDataSource dataSource = new ComboPooledDataSource();
	//向池中添加连接(系统会自动读取c3p0-config.xml文件:a.文件的名字要固定 b.文件的位置也是固定的)
	
	//2.获取连接
	public static Connection getConnection() throws SQLException {
		return dataSource.getConnection();
	}
	//3.释放连接
	public static void release(Connection connection, PreparedStatement pStatement, ResultSet rSet) {
		if (rSet != null) {
			try {
				rSet.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (pStatement != null) {
			try {
				pStatement.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
