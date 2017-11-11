package com.qianfeng.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.qianfeng.domain.User;
import com.qianfeng.service.UserService;
import com.qianfeng.service.impl.UserException;
import com.qianfeng.service.impl.UserServiceImpl;

public class RegisterServlet extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		request.setCharacterEncoding("utf-8");
		
		//�ȶ���֤�������֤
		String code = request.getParameter("ckcode");
		String code1 = (String)request.getSession().getAttribute("checkcode_session");
		
		if (!code.equals(code1)) {
			request.setAttribute("errmsg", "��֤���������");
			request.getRequestDispatcher("/register.jsp").forward(request, response);
		}
		
		//��֤������Ϣ
		User user = new User();
		try {
			BeanUtils.populate(user,request.getParameterMap());
			user.setActiveCode(UUID.randomUUID().toString());
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		UserService uService = new UserServiceImpl();
		int num = 0;
		
		//ע����������ַ�ʽ֮ͨ���Զ����쳣
		try {
			num = uService.registerUser(user);
			out.println("ע��ɹ�");
			//���ﲻ��Ҫ�洢�û�������,���û���ɵ�¼��,�ٴ�
//			request.getSession().setAttribute("user", user);
			request.getRequestDispatcher("/registersuccess.jsp").forward(request, response);
		} catch (UserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			request.setAttribute("msg", "ע��ʧ��");
			request.getRequestDispatcher("/register.jsp").forward(request, response);
		}
		
		
		//ͨ������ֵ
//		if (num>0) {
//			out.println("ע��ɹ�");
//			request.getSession().setAttribute("user", user);
//			request.getRequestDispatcher("/registersuccess.jsp").forward(request, response);
//			
//		}else {
//			request.setAttribute("msg", "ע��ʧ��");
//			request.getRequestDispatcher("/register.jsp").forward(request, response);
//		}
		
		out.close();
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
