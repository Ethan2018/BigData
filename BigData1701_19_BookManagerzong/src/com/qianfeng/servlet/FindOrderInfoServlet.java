package com.qianfeng.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qianfeng.domain.Order;
import com.qianfeng.service.OrderService;
import com.qianfeng.service.impl.OrderServiceImpl;
import com.qianfeng.service.impl.UserException;

public class FindOrderInfoServlet extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		request.setCharacterEncoding("utf-8");
		
		//ªÒ»°∂©µ•∫≈
		String orderid = request.getParameter("order_id");
		
		OrderService oService = new OrderServiceImpl();
		
		try {
			Order order = oService.findOrderInfo(orderid);
			
			request.setAttribute("order", order);
			System.out.println("FindOrderInfoServlet----order="+order);
			request.getRequestDispatcher("/orderInfo.jsp").forward(request, response);
			System.out.println("haha");
		} catch (UserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		out.close();
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
