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
			//�����ֶ��ύ����
			connection.setAutoCommit(false);
			//���뼶��
			connection.setTransactionIsolation(connection.TRANSACTION_SERIALIZABLE);
			//�������100
			String sql = "update count set money=money-100 where name=?";
			pStatement = connection.prepareStatement(sql);
			pStatement.setString(1, "����");
			pStatement.executeUpdate();
			pStatement.close();
			//�����յ�100
			String sql2 = "update count set money=money+100 where name=?";
			pStatement = connection.prepareStatement(sql2);
			pStatement.setString(1, "����");
			pStatement.executeUpdate();
			pStatement.close();
			//�ύ����
			connection.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			//����ع�
			connection.rollback();
		} finally {
			DBUtil.closeAll(connection, pStatement, null);
		}
	}
}
