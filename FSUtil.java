package com.sunnyinfo.utils;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.LocalFileSystem;

public class FSUtil {
	
	
	public static FileSystem getFS() {
		
		try {
			Configuration conf = new Configuration();
			FileSystem fs = FileSystem.get(conf);
			return fs;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		throw new RuntimeException();
	}
	
	
	public static LocalFileSystem getLFS() {
		LocalFileSystem lfs = null;
		try {
			lfs = FileSystem.newInstanceLocal(new Configuration());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lfs;
		
	}
	
	
	
	public static void closeFS(FileSystem fs) {
		if (fs != null) {
			try {
				fs.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	
	public static void closeLFS(LocalFileSystem lfs) {
		if (lfs != null) {
			try {
				lfs.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
}
