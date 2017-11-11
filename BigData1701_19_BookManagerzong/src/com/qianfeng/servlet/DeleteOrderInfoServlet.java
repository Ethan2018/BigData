package com.qianfeng.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qianfeng.service.OrderService;
import com.qianfeng.service.impl.OrderServiceImpl;
import com.qianfeng.service.impl.UserException;

public class DeleteOrderInfoServlet extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		request.setCharacterEncoding("utf-8");
		
		String orderid = request.getParameter("order_id");
		
		//到数据库删除订单信息
		OrderService oService = new OrderServiceImpl();
		try {
			oService.deleteOrderInfo(orderid);
			
			
			
			request.getRequestDispatcher("/servlet/FindOrderList").forward(request, response);
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
