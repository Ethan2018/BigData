package com.sunnyinfo.web.servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;

/**
 * Servlet implementation class UploadServlet2
 */
@WebServlet("/UploadServlet2")
public class UploadServlet2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UploadServlet2() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		request.setCharacterEncoding("utf-8");
		
		boolean ismu = ServletFileUpload.isMultipartContent(request);
		if (!ismu) {
			throw new RuntimeException("enctype类型不是multipart/form-data");
		}
		
		DiskFileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload sfu = new ServletFileUpload(factory);
		try {
			List<FileItem> list = sfu.parseRequest(request);
			for (FileItem fileItem : list) {
				if (fileItem.isFormField()) {
					dealFormField(fileItem);
				} else {
					dealFileUpload(fileItem);
				}
			}
		} catch (FileUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		out.close();
	}
	
	
	public static void dealFormField(FileItem fileItem) {
		String name = fileItem.getFieldName();
		String value = null;
		try {
			value = fileItem.getString("utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("name=" + name + " value=" + value);
	}
	public void dealFileUpload(FileItem fileItem) {
		String storedirectory = this.getServletContext().getRealPath("/WEB-INF/upload");
		File readfile = new File(storedirectory);
		if (!readfile.exists()) {
			readfile.mkdirs();
		}
		String name = fileItem.getName();
		name = FilenameUtils.getName(name);
		
		name = UUID.randomUUID().toString() + "_" + name;
		
		
		//String subdir = getSubDirectoryByTime(readfile);
		String subdir = getSubDirectoryByHashCode(readfile, name);
		File path = new File(readfile, subdir + File.separator + name);
		try {
			fileItem.write(path);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public String getSubDirectoryByTime(File readfile) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String dateStr = sdf.format(date);
		File file = new File(readfile, dateStr);
		if (!file.exists()) {
			file.mkdirs();
		}
		return dateStr;
	}
	public String getSubDirectoryByHashCode(File readfile, String filename) {
		int hash = filename.hashCode();
		String hashStr = Integer.toHexString(hash);
		String subStr = hashStr.charAt(0) + File.separator + hashStr.charAt(1);
		File file = new File(readfile, subStr);
		if (!file.exists()) {
			file.mkdirs();
		}
		return subStr;
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
