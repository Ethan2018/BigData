package com.qianfeng.util;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mchange.v2.c3p0.ComboPooledDataSource;
/**
 * 
 * ʹ��c3p0��ע������:
 * 1.c3p0-config.xml������ֲ��ܸ�,c3p0Ĭ�ϻ�ȥ���������,��ȡ�����ݽ�������
 * 2.�Լ�Ҫ����һ��c3p0-config.xml�ļ�,���ҽ����ݿ����ӵ���Ҫ�ط�������ļ�
 * 3.��Ϊ����c3p0-config.xml�ļ�,ϵͳ���Զ���ȡ,�����DBCP����,���Ծ���ʹ��c3p0
 *
 */
public class C3P0Util {
	
	private static ComboPooledDataSource dataSource = new ComboPooledDataSource();
	
	
	
//	static{
//		
//		try {
//			dataSource.setDriverClass( "com.mysql.jdbc.Driver" );
//		} catch (PropertyVetoException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} //loads the jdbc driver            
//		dataSource.setJdbcUrl( "jdbc:mysql://localhost:3306/db5" );
//		dataSource.setUser("root");                                  
//		dataSource.setPassword("123");     
//	}
	
	public static ComboPooledDataSource getDataSource() {
		return dataSource;
	}

		//��ȡ���Ӷ���
		public static Connection getConnection() throws SQLException {
			return dataSource.getConnection();
		}
		//�ͷ����Ӷ���-----close�Ĺ��ܾ��ǹر�����
		
		public static void release(ResultSet rSet,Connection connection,PreparedStatement ps) {
			if (rSet !=null) {
				try {
					rSet.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			if (connection !=null) {
				try {
					connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			if (ps !=null) {
				try {
					ps.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
}
