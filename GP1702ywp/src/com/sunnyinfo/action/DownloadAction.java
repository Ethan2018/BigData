package com.sunnyinfo.action;

import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;

import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

import com.sunnyinfo.util.FSUtil;

public class DownloadAction extends BaseAction {
	public String fpath;
	public String fname;
	
	
	public String download() {
		return "success";
	}
	
	public InputStream getInputStream() {
		InputStream is = null;
		
		try {
			FileSystem fs = FSUtil.getFS();
			String fp = "/" + (String)session.get("username") + "/" + URLEncoder.encode(fname, "utf-8");
			is = fs.open(new Path(fp));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return is;
	}
}
