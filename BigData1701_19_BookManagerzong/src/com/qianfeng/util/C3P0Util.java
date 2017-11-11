package com.qianfeng.util;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mchange.v2.c3p0.ComboPooledDataSource;
/**
 * 
 * 使用c3p0的注意事项:
 * 1.c3p0-config.xml这个名字不能改,c3p0默认会去找这个名字,读取出数据进行配置
 * 2.自己要创建一个c3p0-config.xml文件,并且将数据库连接的四要素放入这个文件
 * 3.因为对于c3p0-config.xml文件,系统是自动读取,相对于DBCP更简单,所以尽量使用c3p0
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

		//获取连接对象
		public static Connection getConnection() throws SQLException {
			return dataSource.getConnection();
		}
		//释放连接对象-----close的功能就是关闭连接
		
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
