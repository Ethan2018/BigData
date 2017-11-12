package com.sunnyinfo.c3p0;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class C3P0Util {
	//1.��������Դ,ϵͳ���Զ��������
	private static ComboPooledDataSource dataSource = new ComboPooledDataSource();
	//������������(ϵͳ���Զ���ȡc3p0-config.xml�ļ�:a.�ļ�������Ҫ�̶� b.�ļ���λ��Ҳ�ǹ̶���)
	
	//2.��ȡ����
	public static Connection getConnection() throws SQLException {
		return dataSource.getConnection();
	}
	//3.�ͷ�����
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
