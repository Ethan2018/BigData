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
		
		//�ж�����
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		if (!isMultipart) {
			throw new RuntimeException("�������Ͳ���multipart/form-data");
		}
		
		//������������
		DiskFileItemFactory factory = new DiskFileItemFactory();
		
		//�����ϴ��ļ��Ķ���
		ServletFileUpload upload = new ServletFileUpload(factory);
		
		//����
		try {
			List<FileItem> list = upload.parseRequest(request);
			
			//�ֱ����ͨ����������ļ�������������
			for (FileItem fileItem : list) {
				if (fileItem.isFormField()) {
					String name = fileItem.getFieldName();
					String value = fileItem.getString("utf-8");
					
					map.put(name, new String[]{value});
				}else {
					//��ȡ�ļ�������
					String fileName = fileItem.getName();
					fileName = FilenameUtils.getName(fileName);
					
					//��д·��
					String realDir = "C:\\Users\\Administrator\\Desktop\\img\\upload";
					
					File file = new File(realDir);
					
					if (!file.exists()) {
						file.mkdirs();
					}
					
					//��ȡ�ļ���ȫ·��
					String subdir = getChildDirectoryByHashCode(file, fileName);
					
					String saveDir = subdir+File.separator+fileName;
					
					//���ļ��ĵ�ǰĿ¼�洢���ݿ�
					map.put(fileItem.getFieldName(), new String[]{saveDir});
					
					File currentDir = new File(file,saveDir);
					
					//�ļ�д��
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
		
		
		
		//���������Ϣ
		
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
		
		//������ݵ����ݿ�
		book.setId(UUIDUtil.getUUID());
		BookService bService =new BookServiceImpl();
		int num = bService.AddBook(book);
		
		//ת��
		if (num>0) {
			request.getRequestDispatcher("/FindAllBooks").forward(request, response);
		}else {
			//
		}
		
		//out.close();
	}
	
	//�������ļ���Ŀ¼---ͨ����ϣֵ
		private String getChildDirectoryByHashCode(File realDirectory,String filename){
			int hash = filename.hashCode();
			String hexhash = Integer.toHexString(hash);
			
			String subdir = hexhash.charAt(0)+File.separator+hexhash.charAt(1); //   1/e    1/1
			
			//�������ɵ�ʱ���ַ�������������·��
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
