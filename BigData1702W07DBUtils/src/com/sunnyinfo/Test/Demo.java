package com.sunnyinfo.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.junit.Test;

import com.sunnyinfo.util.C3P0Util;

public class Demo {
	//@Test
	//�����������ֱ��ִ��,����Ҫдmain����(1.�����в��� 2.��Ҫд����ֵ)
	public void test() {
		Connection connection = null;
		PreparedStatement pStatement = null;
		try {
			connection = C3P0Util.getConnection();
			pStatement = connection.prepareStatement("insert into users(name,password,birthday) values(?,?,?)");
			pStatement.setString(1, "������");
			pStatement.setString(2, "123");
			pStatement.setString(3, "1990-10-10");
			int num = pStatement.executeUpdate();
			System.out.println(num);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			C3P0Util.release(connection, pStatement, null);
		}
	}
	
	//ʹ��DBUtils�ķ���ʵ��
	//@Test
	public void test2() throws SQLException {
		//1.������ɾ�Ĳ�������� QueryRunner
		/*
		 * >���ÿղεĹ���
		 * >�����вε�
		 *
		 */
		Connection conn = null;
		try {
			conn = C3P0Util.getConnection();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
//		QueryRunner qRunner = new QueryRunner();
//		
//		try {
//			String sql = "insert into users(name,password,birthday) values(?,?,?)";
//			int num = qRunner.update(conn, sql, "����", "234", "1990-1-10");
//			System.out.println(num);
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		
		
		QueryRunner qRunner2 = new QueryRunner(C3P0Util.getComboPooledDataSource());//���Ӷ������ڵ�����Դ
		String sql2 = "insert into users(name,password,birthday) values(?,?,?)";
		int num2 = qRunner2.update(sql2, "������", "234", "1990-1-10");
		System.out.println(num2);
		
		//2.����Connection����
		//3.����sql���
		//4.ͨ��update ��ɾ��
	}
	
	
	//@Test
	public void test3() throws SQLException {
		QueryRunner qRunner = new QueryRunner(C3P0Util.getComboPooledDataSource());//���Ӷ������ڵ�����Դ
		String sql = "delete from users where id=?";
		int num2 = qRunner.update(sql, 6);
		System.out.println(num2);
	}
	//@Test
	public void test4() throws SQLException {
		QueryRunner qRunner = new QueryRunner(C3P0Util.getComboPooledDataSource());//���Ӷ������ڵ�����Դ
		String sql = "update users set name=? where id=?";
		int num = qRunner.update(sql, "aniu", 3);
		System.out.println(num);
	}
	//һ�ֹ���
	@Test
	public void test5() throws SQLException {
		QueryRunner qRunner = new QueryRunner(C3P0Util.getComboPooledDataSource());
		
		String sql = "insert into users(name,password,birthday) values(?,?,?)";
		Object[][] params = new Object[5][];
		for (int i = 0; i < params.length; i++) {
			params[i] = new Object[]{"xiom"+i,"123","1999-10-10"};
		}
		int[] nums = qRunner.batch(sql, params);
		
	}
	
	
}
