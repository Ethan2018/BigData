package com.sunnyinfo.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Date;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.BlockLocation;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.FsStatus;
import org.apache.hadoop.fs.LocalFileSystem;
import org.apache.hadoop.fs.LocatedFileStatus;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.fs.RemoteIterator;
import org.apache.hadoop.hdfs.DistributedFileSystem;
import org.apache.hadoop.hdfs.protocol.DatanodeInfo;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.util.Progressable;

public class HDFS2Handle {
	/**
	 * HDFS中的文件在控制台显示
	 * @param filepath    HDFS中文件的路径
	 * @throws IOException
	 */
	public static void catFileToConsole(String filepath) throws IOException {
		Configuration conf = new Configuration();
		conf.set("fs.defaultFS", "hdfs://192.168.140.11:9000");
		FileSystem fs = FileSystem.get(conf);
		FSDataInputStream fis = fs.open(new Path(filepath));
		IOUtils.copyBytes(fis, System.out, conf, true);
	}
	
	/**
	 * HDFS文件下载到本地windows
	 * @param filepath
	 * @throws IOException
	 */
	public static void catFileToLocal(String filepath) throws IOException {
		Configuration conf = new Configuration();
		conf.set("fs.defaultFS", "hdfs://192.168.140.11:9000");
		FileSystem fs = FileSystem.get(conf);
		FSDataInputStream fis = fs.open(new Path(filepath));
		OutputStream os = new FileOutputStream(new File("D:\\hadoopdata\\readme.txt"));
		IOUtils.copyBytes(fis, os, conf, true);
		fs.close();
		//IOUtils.copyBytes(fis, System.out, conf, true);
	}
	
	/**
	 * HDFS中创建目录
	 * @param filepath    HDFS中需要创建的目录
	 * @throws IOException
	 */
	public static void mkdir(String filepath) throws IOException {
		Configuration conf = new Configuration();
		conf.set("fs.defaultFS", "hdfs://192.168.140.11:9000");
		FileSystem fs = FileSystem.newInstance(conf);
		boolean isok = fs.mkdirs(new Path(filepath));
		fs.close();
		if (isok) {
			System.out.println("success!");
		} else {
			System.out.println("fail");
		}
	}
	
	
	/**
	 * HDFS中创建文件
	 * @param filepath   HDFS中需要创建文件的路径
	 * @throws IOException
	 * @throws URISyntaxException
	 */
	public static void touchz(String filepath) throws IOException, URISyntaxException {
		Configuration conf = new Configuration();
		//conf.set("fs.defaultFS", "hdfs://smallbell:9000");
		FileSystem fs = FileSystem.get(new URI("hdfs://192.168.140.11:9000"), conf);
		
		InputStream is = new FileInputStream(new File("D:\\hadoopdata\\readme.txt"));
		FSDataOutputStream fos = fs.create(new Path(filepath));
		
		IOUtils.copyBytes(is, fos, 4096, false);
		is.close();
		fos.close();
		System.out.println("finished.....");
		fs.close();
	}
	
	/**
	 * listStatus 可以列出文件和文件夹的信息，但是不提供自带的递归遍历
	 * @param filepath  HDFS中所选文件的路径
	 * @throws FileNotFoundException
	 * @throws IllegalArgumentException
	 * @throws IOException
	 */
	 public static void listContain(String filepath) throws FileNotFoundException, IllegalArgumentException, IOException {
		FileSystem fs = FSUtil.getFS();
		FileStatus[] fss = fs.listStatus(new Path(filepath));
		
		for (FileStatus fileStatus : fss) {
			System.out.println("文件路径:" + fileStatus.getPath());
			System.out.println("文件名字:" + fileStatus.getPath().getName());
			System.out.println("文件大小(字节):" + fileStatus.getLen());
			System.out.println("文件的副本数:" + fileStatus.getReplication());
			System.out.println("文件操作时间:" + new Date(fileStatus.getAccessTime()));
			System.out.println("文件所有者:" + fileStatus.getOwner());
			
		}
		FSUtil.closeFS(fs);
	}
	
	 public static void listFiles(String filepath) throws FileNotFoundException, IllegalArgumentException, IOException {
		FileSystem fs = FSUtil.getFS();
		RemoteIterator<LocatedFileStatus> files = fs.listFiles(new Path(filepath), true); 
		while (files.hasNext()) {
			LocatedFileStatus file = files.next();
//			Path filePath = file.getPath();
//			String fileName = filePath.getName();
//			System.out.println(fileName);
			System.out.println("文件路径:" + file.getPath());
			System.out.println("文件名字:" + file.getPath().getName());
			System.out.println("文件大小(字节):" + file.getLen());
			System.out.println("文件的副本数:" + file.getReplication());
			System.out.println("文件操作时间:" + new Date(file.getAccessTime()));
			System.out.println("文件所有者:" + file.getOwner());
		}
	 }
	 
	 
	 
	 /**
	  * 列出本地文件列表
	  * @param path
	  * @throws FileNotFoundException
	  * @throws IllegalArgumentException
	  * @throws IOException
	  */
	 
	public static void listLocals(String path) throws FileNotFoundException, IllegalArgumentException, IOException {
		FileSystem lfs = FSUtil.getLFS();
		FileStatus[] fss = lfs.listStatus(new Path(path));
		
		for (FileStatus fileStatus : fss) {
			System.out.println("文件路径:" + fileStatus.getPath());
			System.out.println("文件名字:" + fileStatus.getPath().getName());
			System.out.println("文件大小:" + fileStatus.getLen());
			System.out.println("文件的副本数:" + fileStatus.getReplication());
			//System.out.println("文件操作时间:" + fileStatus.getAccessTime());
			//System.out.println("文件所有者:" + fileStatus.getOwner());
			
		}
		FSUtil.closeFS(lfs);
	}
	
	
	/**
	 * 获取集群资源使用情况
	 * @throws IOException
	 */
	public static void getSource() throws IOException {
		FileSystem fs = FSUtil.getFS();
		FsStatus  fsStatus = fs.getStatus();
		System.out.println(fsStatus.getCapacity()/1024/1024/1024.0 + "GB");
		System.out.println(fsStatus.getUsed()/1024/1024.0 + "MB");
		System.out.println(fsStatus.getRemaining()/1024/1024/1024.0 + "GB");
		FSUtil.closeFS(fs);
	}
	
	/**
	 * 获取集群节点信息
	 * @throws IOException
	 */
	public static void getNodeInfo() throws IOException {
		
		FileSystem fs = FSUtil.getFS();
		DistributedFileSystem dfs = (DistributedFileSystem) fs;
		DatanodeInfo[] dif = dfs.getDataNodeStats();
		for (int i = 0; i < dif.length; i++) {
			System.out.println("结点主机名:" + dif[i].getName() + ":" + dif[i].getXferPort());
		}
	}
	
	/**
	 * 获取块位置
	 * @param filepath
	 * @throws IllegalArgumentException
	 * @throws IOException
	 */
	public static void getDataBlockLocation(String filepath) throws IllegalArgumentException, IOException {
		FileSystem fs = FSUtil.getFS();
		FileStatus fss = fs.getFileStatus(new Path(filepath));
		BlockLocation[] bLocations = fs.getFileBlockLocations(fss, 0, fss.getLen());
		for (BlockLocation bl : bLocations) {
			for (int i = 0; i < bl.getHosts().length; i++) {
				System.out.println("主机名:" + bl.getHosts()[i]);
				//System.out.println(bl.getCachedHosts()[i]);
			}
		}
	}
	
	
	/**
	 * 创建文件
	 * @param filepath
	 * @throws IOException
	 * @throws URISyntaxException
	 */
	 public static void uploadWithProgressable(String filepath) throws IOException, URISyntaxException {
			Configuration conf = new Configuration();
			//conf.set("fs.defaultFS", "hdfs://smallbell:9000");
			FileSystem fs = FileSystem.get(new URI("hdfs://192.168.140.11:9000"), conf);
			
			InputStream is = new FileInputStream(new File("D:\\hadoopdata\\readme.txt"));
			FSDataOutputStream fos = fs.create(new Path(filepath), new Progressable() {
				
				@Override
				public void progress() {
					// TODO Auto-generated method stub
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					System.out.print("*");
				}
			});
			
			IOUtils.copyBytes(is, fos, 4096, false);
			is.close();
			fos.close();
			System.out.println("finished.....");
			fs.close();
		}
	
	 /**
	  * 上传(文件或者目录都可以)
	  * @param localpath
	  * @param filepath
	  * @throws IOException
	  * @throws URISyntaxException
	  */
	 public static void upload(String localpath, String filepath) throws IOException, URISyntaxException {
			FileSystem fs = FSUtil.getFS();
			fs.copyFromLocalFile(false, true, new Path(localpath), new Path(filepath));
			FSUtil.closeFS(fs);
	}
	 
	 /**
	  * 批量上传
	  * @param localpath
	  * @param filepath
	  * @throws IOException
	  * @throws URISyntaxException
	  */
	 public static void uploadBatch(Path[] localpath, String filepath) throws IOException, URISyntaxException {
			FileSystem fs = FSUtil.getFS();
			fs.copyFromLocalFile(false, true, localpath, new Path(filepath));
			//fs.moveFromLocalFile(localpath, new Path(filepath));
			FSUtil.closeFS(fs);	
	}
	
	 
	 /**
	  * 下载
	  * @param filepath
	  * @param localpath
	  * @throws IOException
	  * @throws URISyntaxException
	  */
	 public static void copy2local(String filepath, String localpath) throws IOException, URISyntaxException {
			FileSystem fs = FSUtil.getFS();
			fs.copyToLocalFile(false, new Path(filepath), new Path(localpath), true);
			FSUtil.closeFS(fs);	
	}
	 /**
	  * 删除文件或者目录
	  * @param filepath
	  * @throws IOException
	  */
	 public static void del(String filepath) throws IOException {
			
		 FileSystem fs = FSUtil.getFS();
		 
		 Path fp = new Path(filepath);
		 if (fs.exists(fp)) {
			if (fs.isDirectory(fp)) {
				fs.delete(fp, true);
			} else {
				fs.delete(fp, false);
			}
		} else {
			System.out.println(filepath + "文件不存在");
		}
	}
	 
	 
	 /**
	  * 重命名或者剪切
	  * @param filepath
	  * @param newfilepath
	  * @throws IllegalArgumentException
	  * @throws IOException
	  */
	 public static void rename(String filepath, String newfilepath) throws IllegalArgumentException, IOException {
			
		 FileSystem fs = FSUtil.getFS();
		 fs.rename(new Path(filepath), new Path(newfilepath));
		 FSUtil.closeFS(fs);
	 } 
	 
	 /**
	  * 将小文件合并上传
	  * @param localpath
	  * @param desPath
	  * @throws IllegalArgumentException
	  * @throws IOException
	  */
	 public static void uploadWithMerge(String localpath, String desPath) throws IllegalArgumentException, IOException {
			
		 FileSystem fs = FSUtil.getFS();
		 LocalFileSystem lfs = FSUtil.getLFS();
		 Path dp = new Path(desPath);
		 FileStatus[] fStatus = lfs.listStatus(new Path(localpath));
		 FSDataOutputStream fos = null;
		 InputStream is = null;
		 for (FileStatus fileStatus : fStatus) {
			System.out.println("文件路径:" + fileStatus.getPath().toString().replace("file:/", ""));
			String filePath = fileStatus.getPath().toString().replace("file:/", "");
			is = new FileInputStream(new File(filePath));
			
			if (!fs.exists(dp)) {
				fos = fs.create(dp);
			} 
			byte[] data = new byte[1024];
			int len = 0;
			while ((len = is.read(data)) != -1) {
				fos.write(data, 0, len);
			}
		 }
		 fos.close();
		 is.close();
		 FSUtil.closeLFS(lfs);
		 FSUtil.closeFS(fs);
	} 
	 
	 
	 
	public static void main(String[] args) throws IOException {
		//catFileToConsole("/test/my.cnf");
		//catFileToLocal("/test/my.cnf");
		//mkdir("/hadoopdata/1120");
//		try {
//			touchz("/test/readme.txt");
//		} catch (URISyntaxException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		//listContain("/test/readme.txt");
		//listFiles("/test");
		//listLocals("D:\\hadoopdata");
		//getSource();
		//getNodeInfo();
		//getDataBlockLocation("/test/shell/1.doc");
//		try {
//			uploadWithProgressable("/upload/");
//		} catch (URISyntaxException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
//		try {
//			upload("D:\\hadoopdata\\readme.txt", "/test/readme2.txt");
//		} catch (URISyntaxException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
//		Path [] paths = {new Path("D:\\hadoopdata\\122.txt"),new Path("D:\\hadoopdata\\123.txt"),new Path("D:\\hadoopdata\\readme.txt")};
//		try {
//			uploadBatch(paths, "/test");
//		} catch (URISyntaxException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
//		try {
//			copy2local("/test/readme.txt", "D:\\hadoopdata\\re.txt");
//		} catch (URISyntaxException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		//del("/upload");
		//rename("/hadoopdata/1120", "/hadoopdata/test");
		
		uploadWithMerge("D:\\hadoopdata\\", "/upload/hadoopdatamerge");
	}
	
}
