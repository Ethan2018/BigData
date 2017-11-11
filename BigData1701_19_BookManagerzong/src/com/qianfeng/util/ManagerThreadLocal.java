package com.qianfeng.util;

import java.sql.Connection;
import java.sql.SQLException;

public class ManagerThreadLocal {
	private static ThreadLocal<Connection> local = new ThreadLocal<Connection>();
	private static Connection connection = null;
	//��ȡ���Ӷ���
	public static Connection getConnection() {
		//��local�л�ȡ���Ӷ���
		connection = local.get();
		
		//��һ��ʹ�����������ʱ��,local���滹û�����Ӷ���,����Ҫ�ȴ�����һ��
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
	
	//�ֶ���������
	//������
	public static void startTransaction() {
		try {
			connection = getConnection();
			connection.setAutoCommit(false);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//�ع�����
	public  static void  rollbackTransaction() {
		try {
			connection.rollback();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//�ر�����
	public static void closeTransaction() {
		//�����ӷŻ����ӳ�
		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//�����Ӵ�local��ɾ��
		local.remove();
	}
	
	//�ύ����
	public static void commitTransaction() {
		try {
			connection.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
