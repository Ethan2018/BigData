package com.qianfeng.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.qianfeng.domain.Book;
import com.qianfeng.domain.Order;
import com.qianfeng.domain.OrderItem;
import com.qianfeng.domain.User;
import com.qianfeng.service.OrderService;
import com.qianfeng.service.impl.OrderServiceImpl;
import com.qianfeng.service.impl.UserException;

public class OrderServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		request.setCharacterEncoding("utf-8");
		
		Order order = new Order();
		try {
			BeanUtils.populate(order, request.getParameterMap());
			//自己设置id
			order.setId(UUID.randomUUID().toString());
			//添加用户
			order.setUser((User)request.getSession().getAttribute("user"));
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//添加订单项
		
		//获取购物车数据
		Map<Book, String> map = (Map<Book, String>)request.getSession().getAttribute("cart");
		
		//创建一个临时的集合对象
		List<OrderItem> templist = new ArrayList<OrderItem>();
		
		for(Map.Entry<Book, String> en:map.entrySet()){
			Book book = en.getKey();
			int num = Integer.parseInt(en.getValue());
			
			//创建订单项对象
			OrderItem item = new OrderItem();
			item.setOrder(order);
			item.setBook(book);
			item.setBuynum(num);
			
			templist.add(item);
		}
		
		
		//将订单项放入order
		order.setList(templist);
		
		//将订单放入数据库
		OrderService oService = new OrderServiceImpl();
		try {
			oService.addOrder(order);
			
			request.setAttribute("order", order);
			//跳转到支付界面
			request.getRequestDispatcher("/pay.jsp").forward(request, response);
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
