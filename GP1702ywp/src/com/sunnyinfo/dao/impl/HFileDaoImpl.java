package com.sunnyinfo.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.sunnyinfo.dao.HFileDao;
import com.sunnyinfo.entity.HFile;
import com.sunnyinfo.util.JDBCUtil;

public class HFileDaoImpl implements HFileDao {

	@Override
	public boolean saveFile(HFile file) {
		// TODO Auto-generated method stub
		Connection conn = JDBCUtil.getConn();
		PreparedStatement ps = null;
		boolean flag = false;
		try {
			String sql = "insert into hfile(fname,fpath,fowner,fsize,dt) values(?,?,?,?,?)";
			ps = conn.prepareStatement(sql);
			ps.setString(1, file.getFname());
			ps.setString(2, file.getFpath());
			ps.setString(3, file.getFowner());
			ps.setLong(4, file.getFsize());
			ps.setString(5, file.getDt());
			flag = ps.executeUpdate() > 0 ? true : false;
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			JDBCUtil.closeConn(conn, ps, null);
		}
		
		
		return flag;
	}

	@Override
	public List<HFile> listFile(String owner) {
		// TODO Auto-generated method stub
		Connection conn = JDBCUtil.getConn();
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<HFile> list = new ArrayList<>();
		try {
			String sql = "select fid,fname,fpath,fowner,fsize,dt from hfile where fowner=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, owner);
			rs = ps.executeQuery();
			while (rs.next()) {
				HFile hf = new HFile();
				hf.setFid(rs.getInt("fid"));
				hf.setFname(rs.getString("fname"));
				hf.setFpath(rs.getString("fpath"));
				hf.setFowner(rs.getString("fowner"));
				hf.setFsize(rs.getLong("fsize"));
				hf.setDt(rs.getString("dt"));
				list.add(hf);
			}
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			JDBCUtil.closeConn(conn, ps, rs);
		}
		return list;
	}

	@Override
	public boolean delFile(int fid) {
		// TODO Auto-generated method stub
		Connection conn = JDBCUtil.getConn();
		PreparedStatement ps = null;
		boolean flag = false;
		try {
			String sql = "delete from hfile where fid=?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, fid);
			flag = ps.executeUpdate() > 0 ? true : false;
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			JDBCUtil.closeConn(conn, ps, null);
		}
		return flag;
	}



}
