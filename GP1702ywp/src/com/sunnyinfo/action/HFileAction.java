package com.sunnyinfo.action;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Hdfs;
import org.apache.hadoop.fs.Path;

import com.mysql.jdbc.interceptors.SessionAssociationInterceptor;
import com.sun.org.apache.bcel.internal.generic.NEW;
import com.sunnyinfo.dao.HFileDao;
import com.sunnyinfo.dao.impl.HFileDaoImpl;
import com.sunnyinfo.entity.HFile;
import com.sunnyinfo.util.FSUtil;

public class HFileAction extends BaseAction {
	public File upload;
	public String uploadFileName;
	
	public List<HFile> fileList;
	
	public String fname;
	public int fid;
	
	public String msg;
	
	HFileDao hd = new HFileDaoImpl();

	
	public String toUploadPage() {
		return "success";
	}
	
	public String upload() {
		FileSystem fs = FSUtil.getFS();
		try {
			String localpath = upload.getAbsolutePath();
			String owner = (String)session.get("username");
			String destpath = "/" + owner + "/" + uploadFileName;
			
			HFile file = new HFile();
			file.setFname(uploadFileName);
			file.setFpath(destpath);
			file.setFowner(owner);
			file.setFsize(upload.length());
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			file.setDt(sdf.format(new Date()));
			
			if (hd.saveFile(file)) {
				fs.copyFromLocalFile(new Path(localpath), new Path(destpath));
				msg = "上传成功";
			} else {
				msg = "上传失败";
			}
		} catch (Exception e) {
			// TODO: handle exception
			
		}
		
		return "success";
	}
	
	public String list() {
		fileList = hd.listFile((String)session.get("username"));
		if (fileList.size() <= 0) {
			msg = "暂无文件";
			return "error";
		} else {
			return "success";
		}
	}
	
	public String del() {
		FileSystem fs = FSUtil.getFS();
		try {
			if (hd.delFile(fid)) {
				boolean isok;
					isok = fs.delete(new Path("/" + (String)session.get("username") + "/" + fname), true);
					if (isok) {
						msg = "删除成功";
					} else {
						msg = "删除失败";
					}
			} else {
				msg = "删除失败";
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return "success";
	}
	
}
