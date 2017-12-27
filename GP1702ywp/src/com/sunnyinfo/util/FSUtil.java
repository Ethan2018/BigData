package com.sunnyinfo.util;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.LocalFileSystem;

public class FSUtil {
		public static FileSystem getFS() {
				FileSystem fs = null;
				try {
					Configuration conf = new Configuration();
//					conf.set("fs.defaultFS", "hdfs://sunnyinfo");
//					conf.set("dfs.nameservices", "sunnyinfo");
//					conf.set("dfs.ha.namenodes.sunnyinfo", "nn1, nn2");
//					conf.set("dfs.namenode.rpc-address.sunnyinfo.nn1", "hadoop01:9000");
//					conf.set("dfs.namenode.rpc-address.sunnyinfo.nn2", "smallbell:9000");
//					conf.set("dfs.client.failover.proxy.provider.sunnyinfo", "org.apache.hadoop.hdfs.server.namenode.ha.ConfiguredFailoverProxyProvider");
					fs = FileSystem.get(conf);
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return fs;
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
}