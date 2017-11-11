package com.qianfeng.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qianfeng.domain.Book;
import com.qianfeng.service.BookService;
import com.qianfeng.service.impl.BookServiceImpl;

public class findBooksByNameServlet extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		request.setCharacterEncoding("utf-8");
		
		//接收名字
		String name= request.getParameter("name");
		
		//遇到中文时需要再编码,再解码
		String newname = new String(name.getBytes("ISO8859-1"),"utf-8");
		
		//去数据库查询信息
		BookService bService = new  BookServiceImpl();
		List<Object>list = bService.findBooksByName(newname);
		
		//将数据传回页面
		//将list里面的数据取出,拼接成一个字符串
		String str = "";
		for(int i=0;i<list.size();i++){
			if (i>0) {
				str+=",";
			}
			str+=list.get(i);
		}
		
		//将拼接好的字符串发挥界面
		out.print(str);
		
		out.close();
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
		
	}

}
