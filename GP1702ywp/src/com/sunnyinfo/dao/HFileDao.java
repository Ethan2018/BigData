package com.sunnyinfo.dao;

import java.util.List;

import com.sunnyinfo.entity.HFile;

public interface HFileDao {
	boolean saveFile(HFile file);
	
	List<HFile> listFile(String owner);
	
	boolean delFile(int fid);
}
