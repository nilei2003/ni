package com.qq.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.GoodsDetail;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.qq.util.SessionKeys;
import com.qq.util.StringUtils;
import com.qq.util.bean;
import com.yc.snacknet_vue.alipay.config.AlipayConfig;





/**
 * 沙箱支付  付款控制层
 * company 源辰信息
 * @author Administrator
 * @date 2021年8月13日
 * @version 1.0
 * Email 1198865589@qq.com
 */
@WebServlet("/alipay")
public class AlipayController1 extends HttpServlet{

	private static final long serialVersionUID = -1637054167091780128L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setHeader("Access-Control-Allow-Origin", "*");//跨域
		// 如果报错支付宝报错: invalid-signature 错误原因: 验签出错，建议检查签名字符串或签名私钥与应用公钥是否匹配，网关生成的验签字符串为……很可能是编码问题，指定一下编码即可
		response.setContentType("text/html;charset=UTF-8");
		
		//商户订单号，商户网站订单系统中唯一订单号，必填
		//String out_trade_no = new String(request.getParameter("WIDout_trade_no").getBytes("ISO-8859-1"),"UTF-8");
		String out_trade_no = request.getParameter("msid");
		String uid = request.getParameter("uid");
		String eid = request.getParameter("eid");
		
		bean.eid = eid;
		bean.uid = uid;
		System.out.println(bean.eid + bean.uid);
		//付款金额，必填
		//String total_amount = new String(request.getParameter("WIDtotal_amount").getBytes("ISO-8859-1"),"UTF-8");
		String total_amount = request.getParameter("emoney");
		
		PrintWriter out = response.getWriter();
		
		//容错判断
		if(StringUtils.pach(out_trade_no,total_amount)) {
			out.print("<script>alert('订单信息有误,请重新支付...'); location.href='index.html';</script>");
			return;
		}
		System.out.println(out_trade_no +"--" + total_amount + "--" + uid + "--" + eid);
		//获得初始化的AlipayClient
		AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.app_id, AlipayConfig.merchant_private_key, "json", AlipayConfig.charset, AlipayConfig.alipay_public_key, AlipayConfig.sign_type);
		
		//设置请求参数
		AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
		alipayRequest.setReturnUrl(AlipayConfig.return_url);
		alipayRequest.setNotifyUrl(AlipayConfig.notify_url);

		//订单名称，必填
		//String subject = new String(request.getParameter("WIDsubject").getBytes("ISO-8859-1"),"UTF-8");
		String subject = "QQ音乐";
		String body = "QQ音乐， 应有尽有";
		
		JSONObject bizContent = new JSONObject();
		bizContent.put("out_trade_no", out_trade_no);
		bizContent.put("total_amount", total_amount);
		bizContent.put("subject", subject);
		bizContent.put("body", body);
		bizContent.put("product_code", "FAST_INSTANT_TRADE_PAY");
		

		System.out.println(bizContent.toString());
		
		//商品描述，可空
		//String body = new String(request.getParameter("WIDbody").getBytes("ISO-8859-1"),"UTF-8");
		
		
		alipayRequest.setBizContent(bizContent.toString());
		
		//若想给BizContent增加其他可选请求参数，以增加自定义超时时间参数timeout_express来举例说明
		//alipayRequest.setBizContent("{\"out_trade_no\":\""+ out_trade_no +"\"," 
		//		+ "\"total_amount\":\""+ total_amount +"\"," 
		//		+ "\"subject\":\""+ subject +"\"," 
		//		+ "\"body\":\""+ body +"\"," 
		//		+ "\"timeout_express\":\"10m\"," 
		//		+ "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");
		//请求参数可查阅【电脑网站支付的API文档-alipay.trade.page.pay-请求参数】章节
		
		String  result = "";
		//请求
		try {
			 result = alipayClient.pageExecute(alipayRequest).getBody();
		} catch (AlipayApiException e) {
			e.printStackTrace();
		}
		out.print( result );
	}


}
