package com.qianfeng.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.qianfeng.domain.Book;
import com.qianfeng.service.BookService;
import com.qianfeng.service.impl.BookServiceImpl;

public class AddToCartServlet extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		request.setCharacterEncoding("utf-8");
		
		//接收参数
		String id = request.getParameter("id");
		
		//获取对应的数据
		BookService bService =  new BookServiceImpl();
		Book book = bService.findBookById(id);
		
		HttpSession hSession = request.getSession();
		Map<Book, String> map = (Map<Book, String>)hSession.getAttribute("cart");
		
		int num = 1;
		
		if (map == null) {
			map = new HashMap<Book, String>();
		}
		
		//处理同时买多本同样的书的情况
		//判断车中是否包含当前的书
		if (map.containsKey(book)) {
			num = Integer.parseInt(map.get(book))+1;
		}
		
		map.put(book, num+"");
		
		//将购物车放入session
		hSession.setAttribute("cart", map);
		
		
		out.println("<a href='"+request.getContextPath()+"/PageServlet?currentPage=1'>继续购物</a>");
		out.println("<a href='"+request.getContextPath()+"/cart.jsp'>到购物车</a>");
		
		out.close();
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
