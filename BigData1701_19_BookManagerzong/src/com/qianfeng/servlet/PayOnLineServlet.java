package com.qianfeng.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qianfeng.util.PaymentUtil;

public class PayOnLineServlet extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		String orderid = request.getParameter("orderid");
		String money = request.getParameter("money");
		String yh = request.getParameter("yh");
		
		//在支付前把数据组织成第三方需要的格式
		String p0_Cmd = "Buy";
		String p1_MerId="10001126856";  //如果是真实的支付,需要到易宝支付的后台完成注册
		String p2_Order=orderid;
		String p3_Amt=money;
		String p4_Cur="CNY";
		String p5_Pid="bugaosuni";
		String p6_Pcat="eat";
		String p7_Pdesc="good";
		//支付完成后第三方请求的资源路径
		String p8_Url="http://10.0.181.19:8081/BigData1701_19_BookManager/CallBackServlet";
		String p9_SAF="1";
		String pa_MP="idon'tknow";
		String pd_FrpId=yh;
		String pr_NeedResponse="1";
		String keyValue = "69cl522AV6q613Ii4W6u8K6XuW8vM1N6bFgyv769220IuYe9u37N4y7rI4Pl";//密钥
		
		//完成数字签名
		String hmac = PaymentUtil.buildHmac(p0_Cmd, p1_MerId, p2_Order, p3_Amt, p4_Cur, p5_Pid, p6_Pcat, p7_Pdesc, p8_Url, p9_SAF, pa_MP, pd_FrpId, pr_NeedResponse, keyValue);
		
		//通过get方法发送
		//String url = "https://www.yeepay.com/app-merchant-proxy/node?p0_Cmd=";
		
		
		request.setAttribute("p0_Cmd",p0_Cmd);
		request.setAttribute("p1_MerId",p1_MerId);
		request.setAttribute("p2_Order",p2_Order);
		request.setAttribute("p3_Amt",p3_Amt);
		request.setAttribute("p4_Cur",p4_Cur);
		request.setAttribute("p5_Pid",p5_Pid);
		request.setAttribute("p6_Pcat",p6_Pcat);
		request.setAttribute("p7_Pdesc",p7_Pdesc);
		request.setAttribute("p8_Url",p8_Url);
		request.setAttribute("p9_SAF",p9_SAF);
		request.setAttribute("pa_MP",pa_MP);
		request.setAttribute("pd_FrpId",pd_FrpId);
		request.setAttribute("hmac",hmac);
		request.setAttribute("pr_NeedResponse",pr_NeedResponse);
		
		request.getRequestDispatcher("/confirm.jsp").forward(request, response);
		
		out.close();
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
