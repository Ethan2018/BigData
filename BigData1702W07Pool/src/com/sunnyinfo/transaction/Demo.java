package com.sunnyinfo.transaction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.sunnyinfo.util.DBUtil;

public class Demo {
	public static void main(String[] args) throws SQLException {
		
		Connection connection = null;
		PreparedStatement pStatement = null;
		try {
			connection = DBUtil.getConnection();
			//开启手动提交事务
			connection.setAutoCommit(false);
			//隔离级别
			connection.setTransactionIsolation(connection.TRANSACTION_SERIALIZABLE);
			//张三借出100
			String sql = "update count set money=money-100 where name=?";
			pStatement = connection.prepareStatement(sql);
			pStatement.setString(1, "张三");
			pStatement.executeUpdate();
			pStatement.close();
			//王五收到100
			String sql2 = "update count set money=money+100 where name=?";
			pStatement = connection.prepareStatement(sql2);
			pStatement.setString(1, "王五");
			pStatement.executeUpdate();
			pStatement.close();
			//提交事务
			connection.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			//事务回滚
			connection.rollback();
		} finally {
			DBUtil.closeAll(connection, pStatement, null);
		}
	}
}
