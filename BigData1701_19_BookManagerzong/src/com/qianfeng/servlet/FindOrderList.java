package com.qianfeng.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qianfeng.domain.Order;
import com.qianfeng.domain.User;
import com.qianfeng.service.OrderService;
import com.qianfeng.service.impl.OrderServiceImpl;
import com.qianfeng.service.impl.UserException;

public class FindOrderList extends HttpServlet {


	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		request.setCharacterEncoding("utf-8");
		//获取用户的id
		User user = (User)request.getSession().getAttribute("user");
		
		//到数据库查找当前用户的所有订单信息
		OrderService oService = new OrderServiceImpl();
		List<Order> orders;
		try {
			orders = oService.findOrders(user.getId());
			
			
			
			request.setAttribute("orders", orders);
			request.getRequestDispatcher("/orderlist.jsp").forward(request, response);
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
