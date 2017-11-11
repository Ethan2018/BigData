package com.qianfeng.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qianfeng.domain.PageBook;
import com.qianfeng.service.BookService;
import com.qianfeng.service.impl.BookServiceImpl;

public class PageServlet extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		request.setCharacterEncoding("utf-8");
		
		//获取标签参数
		String currrentPage = request.getParameter("currentPage");
		int cPage = Integer.parseInt(currrentPage);
		System.out.println("cpage="+cPage);
		//每页的书籍的本数:
		int pageSize = 4;
		
		//到数据库查询当页的信息
		BookService bService = new BookServiceImpl();
		PageBook pbook = bService.findBooksByPage(cPage,pageSize);
		
		//返回product_list.jsp界面
		System.out.println("pageBook="+pbook);
		request.setAttribute("pageBook", pbook);
		request.getRequestDispatcher("/product_list.jsp").forward(request, response);
		
		out.close();
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
