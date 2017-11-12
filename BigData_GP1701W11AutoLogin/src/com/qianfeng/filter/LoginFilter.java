package com.qianfeng.filter;

import java.io.IOException;
import java.net.URLDecoder;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import com.qianfeng.domain.User;
import com.qianfeng.service.UserService;
import com.qianfeng.service.impl.UserServiceImpl;

public class LoginFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		HttpServletRequest hsr = (HttpServletRequest)request;
		Cookie[] cookies = hsr.getCookies();
		
		String name = null;
		String pwd = null;
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("user")) {
				
					String value = cookie.getValue();
					//在cookie中保存中文的时候----解码
					String newValue = URLDecoder.decode(value, "utf-8");
					String[] ss = newValue.split("&");
					System.out.println("newValue="+newValue);
					name = ss[0];
					pwd = ss[1];
					
					//先查询数据库进行验证
					UserService uService = new UserServiceImpl();
					User user = uService.findUserByNameAndPwd(name,pwd);
					
					if (user!= null) {
						hsr.getSession().setAttribute("user", user);
					}
				}else {
					System.out.println("other=");
					//没有保存的值,说明没有自动登录设置,让用户重新登录
					hsr.getSession().setAttribute("user", null);
				}
			}
			
		}
		
		chain.doFilter(hsr, response);
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

}
