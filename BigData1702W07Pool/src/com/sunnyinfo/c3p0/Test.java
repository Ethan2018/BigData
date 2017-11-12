package com.sunnyinfo.c3p0;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Connection connection = null;
		PreparedStatement pStatement = null;
		try {
			connection = C3P0Util.getConnection();
			String sql = "update count set money=money-100 where name=?";
			pStatement = connection.prepareStatement(sql);
			pStatement.setString(1, "ÕÅÈý");
			pStatement.executeUpdate();
			pStatement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			C3P0Util.release(connection, pStatement, null);
		}
	}

}
