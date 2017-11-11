package com.qianfeng.servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;

import com.qianfeng.domain.Book;
import com.qianfeng.service.BookService;
import com.qianfeng.service.impl.BookServiceImpl;
import com.qianfeng.util.UUIDUtil;

public class AddBookServlet extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Map<String, String[]> map = new HashMap<String, String[]>();
		
		//判断类型
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		if (!isMultipart) {
			throw new RuntimeException("表单的类型不是multipart/form-data");
		}
		
		//创建工厂方法
		DiskFileItemFactory factory = new DiskFileItemFactory();
		
		//创建上传文件的对象
		ServletFileUpload upload = new ServletFileUpload(factory);
		
		//解析
		try {
			List<FileItem> list = upload.parseRequest(request);
			
			//分别对普通的数据项和文件数据项做处理
			for (FileItem fileItem : list) {
				if (fileItem.isFormField()) {
					String name = fileItem.getFieldName();
					String value = fileItem.getString("utf-8");
					
					map.put(name, new String[]{value});
				}else {
					//获取文件的名字
					String fileName = fileItem.getName();
					fileName = FilenameUtils.getName(fileName);
					
					//编写路径
					String realDir = "C:\\Users\\Administrator\\Desktop\\img\\upload";
					
					File file = new File(realDir);
					
					if (!file.exists()) {
						file.mkdirs();
					}
					
					//获取文件的全路径
					String subdir = getChildDirectoryByHashCode(file, fileName);
					
					String saveDir = subdir+File.separator+fileName;
					
					//将文件的当前目录存储数据库
					map.put(fileItem.getFieldName(), new String[]{saveDir});
					
					File currentDir = new File(file,saveDir);
					
					//文件写入
					fileItem.write(currentDir);
				}
			}
		} catch (FileUploadException e1)
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		
		
		//接收书的信息
		
		Book book = new Book();
		try {
			BeanUtils.populate(book, map);
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//添加数据到数据库
		book.setId(UUIDUtil.getUUID());
		BookService bService =new BookServiceImpl();
		int num = bService.AddBook(book);
		
		//转向
		if (num>0) {
			request.getRequestDispatcher("/FindAllBooks").forward(request, response);
		}else {
			//
		}
		
		//out.close();
	}
	
	//创建子文件夹目录---通过哈希值
		private String getChildDirectoryByHashCode(File realDirectory,String filename){
			int hash = filename.hashCode();
			String hexhash = Integer.toHexString(hash);
			
			String subdir = hexhash.charAt(0)+File.separator+hexhash.charAt(1); //   1/e    1/1
			
			//根据生成的时间字符串生成真正的路径
			File newdir = new File(realDirectory,subdir);
			
			if (!newdir.exists()) {
				newdir.mkdirs();
			}
			
			return subdir;		
			
		}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
