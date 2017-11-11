package com.qianfeng.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qianfeng.service.BookService;
import com.qianfeng.service.impl.BookServiceImpl;

public class DelBooksServlet extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		request.setCharacterEncoding("utf-8");
		
		//��ȡ���е�idֵ
		String[] ids = request.getParameterValues("ids");
		
		//�����ݿ�ɾ����ѡ��id��Ӧ�ļ�¼
		BookService bService = new BookServiceImpl();
		int[] num = bService.delBooks(ids);
		//��ת��FinAllBoosServlet
		request.getRequestDispatcher("/FindAllBooks").forward(request, response);
		
		out.close();
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
