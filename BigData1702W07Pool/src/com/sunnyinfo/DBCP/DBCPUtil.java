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
	//1.��������Դ
	private static DataSource dataSource;//ϵͳ�ṩ������Դ
	//2.���������ļ�
	static {
		Properties properties = new Properties();
		try {//�ֽ����ļ����������(����֮��)
			properties.load(DBCPUtil.class.getClassLoader().getResourceAsStream("dbcpconfig.properties"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//�õ����ӳض���,��������Ϣָ�������ӳض���
		try {
			dataSource = BasicDataSourceFactory.createDataSource(properties);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//3.��ȡ����
	public static Connection getConnection() throws SQLException {
		return dataSource.getConnection();
	}
	//4.�ͷ�����
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
