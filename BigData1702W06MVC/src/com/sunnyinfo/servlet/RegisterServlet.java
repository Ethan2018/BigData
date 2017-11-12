package com.sunnyinfo.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.Converter;

import com.sunnyinfo.javaBean.Users;
import com.sunnyinfo.service.UserService;
import com.sunnyinfo.service.impl.UserServiceImpl;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		request.setCharacterEncoding("utf-8");
		Users user = new Users();
		
		try {
			ConvertUtils.register(new Converter() {
				
				@Override
				public Object convert(Class arg0, Object arg1) {
					// TODO Auto-generated method stub
					String temp = (String)arg1;
					Date date = null;
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					if (temp instanceof String) {
						try {
							date = sdf.parse(temp);
						} catch (ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					return date;
				}
			}, Date.class);
			BeanUtils.populate(user, request.getParameterMap());
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		UserService us = new UserServiceImpl();
		int num = us.registerUser(user);
		if (num > 0) {
			out.println("¹§Ï²Äú×¢²á³É¹¦,3Ãëºó·µ»ØÖ÷Ò³");
			response.setHeader("refresh", "3;url='"+request.getContextPath()+"/main.jsp'");
		} else {
			out.println("ºÜ±§Ç¸×¢²áÊ§°Ü,3Ãëºó·µ»Ø");
			response.setHeader("refresh", "3;url='"+request.getContextPath()+"/register.jsp'");
		}
		out.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
