package com.sunnyinfo.DBCP;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSourceFactory;

public class DBCPUtil {
	//1.创建数据源
	private static DataSource dataSource;//系统提供的数据源
	//2.连接配置文件
	static {
		Properties properties = new Properties();
		try {//字节码文件的类加载器(反射之上)
			properties.load(DBCPUtil.class.getClassLoader().getResourceAsStream("dbcpconfig.properties"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//得到连接池对象,将配置信息指定给连接池对象
		try {
			dataSource = BasicDataSourceFactory.createDataSource(properties);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//3.获取连接
	public static Connection getConnection() throws SQLException {
		return dataSource.getConnection();
	}
	//4.释放连接
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
