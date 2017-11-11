package com.qianfeng.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qianfeng.util.PaymentUtil;
import com.qianfeng.service.impl.UserException;
import com.qianfeng.service.OrderService;
import com.qianfeng.service.impl.OrderServiceImpl;

public class CallBackServlet extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		//接收支付完成后第三方返回的数据
				String p1_MerId = request.getParameter("p1_MerId");
				String r0_Cmd = request.getParameter("r0_Cmd");
				String r1_Code= request.getParameter("r1_Code");//支付结果码，1表示支付成功
				String r2_TrxId = request.getParameter("r2_TrxId");
				String r3_Amt =request.getParameter("r3_Amt");
				String r4_Cur =request.getParameter("r4_Cur");
				String r5_Pid =request.getParameter("r5_Pid");
				String r6_Order =request.getParameter("r6_Order");//订单编号
				String r7_Uid =request.getParameter("r7_Uid");
				String r8_MP =request.getParameter("r8_MP");
				String r9_BType =request.getParameter("r9_BType");//1:重定向，2：点对点
				String hmac =request.getParameter("hmac");
				String keyValue = "69cl522AV6q613Ii4W6u8K6XuW8vM1N6bFgyv769220IuYe9u37N4y7rI4Pl";//密钥
		//验证
				boolean issuc = PaymentUtil.verifyCallback(hmac, p1_MerId, r0_Cmd, r1_Code, r2_TrxId, r3_Amt, r4_Cur, r5_Pid, r6_Order, r7_Uid, r8_MP, r9_BType, keyValue);
		
				if (!issuc) {
					out.println("信息被修改,注意安全");
				}else {
					if ("1".equals(r1_Code)) {
						//说明支付成功
						
						if ("2".equals(r9_BType)) {
							out.print("success");//给第三方返回success
						}
						
						
						//修改订单的支付状态
						OrderService orderService = new OrderServiceImpl();
						try {
							orderService.modifyOrderState(r6_Order);
						} catch (UserException e) {
							out.println(e.getMessage());
						}
					}
				}
				out.close();
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
