package com.example.demo.web;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.example.demo.pojo.Buygoods;
import com.example.demo.pojo.Student;
import com.example.demo.service.ICardService;
import com.example.demo.service.IShoppingService;

@RestController
@RequestMapping("/card")
@CrossOrigin(allowCredentials="true", allowedHeaders="*")

public class BuyGoodsController {
//	@Autowired
//	private ICardService cardService;
	@Autowired
	private IShoppingService shoppingService;
	
	@RequestMapping(path = "/addbuygoods",method = {RequestMethod.GET,RequestMethod.POST})
	public boolean addbuyGoods(Integer buygoodid,HttpSession session,Integer stuid) {		
		System.out.println(buygoodid);
		Student stu= (Student)session.getAttribute("student");
		boolean b=false;
		if (stu!=null) {//已经登录
			b=shoppingService.addGoodsCar(buygoodid,stuid);
		}else {//没登录
			
		}
		
		
		return  b;
	}
	
	@RequestMapping(path = "/listbuygoods",method = {RequestMethod.GET,RequestMethod.POST})
	public List<Buygoods> listBuyGoods() {		
		return shoppingService.listBuyGoods();		
	}
	
	@RequestMapping(path = "/deleteBuyGoods",method = {RequestMethod.GET,RequestMethod.POST})
	public List<Buygoods> deleteCarGoods(Integer deleteid) {	//直接返回list<buygoods>	
		//System.out.println("传入的要删除的商品id是："+deleteid);
		shoppingService.deleteCarGoodById(deleteid);
		
		return shoppingService.listBuyGoods();		
	}
	
	@RequestMapping(path = "/deletecarall",method = {RequestMethod.GET,RequestMethod.POST})
	public List<Buygoods> deleteCarAllGoods(@RequestBody Integer [] ids) {	//直接返回list<buygoods>	
		for (Integer id : ids) {
			System.out.println("删除的id是："+id);	
			shoppingService.deleteCarGoodById(id);
		}		
		return shoppingService.listBuyGoods();		
	}
	
	/*@RequestMapping(path = "/submit",method = {RequestMethod.GET,RequestMethod.POST})
	public String submit(Integer [] ids,String amount,HttpServletRequest request) {	//直接返回list<buygoods>	
		for (Integer id : ids) {
			System.out.println("id是："+id);	
			//cardService.deleteCarGoodById(id);//购物车订单清空
		}		
		System.out.println("金额："+amount);
		//获得初始化的AlipayClient
		AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.app_id, AlipayConfig.merchant_private_key, "json", AlipayConfig.charset, AlipayConfig.alipay_public_key, AlipayConfig.sign_type);
		
		//设置请求参数
		AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
		alipayRequest.setReturnUrl(AlipayConfig.return_url);
		alipayRequest.setNotifyUrl(AlipayConfig.notify_url);
		
		//请求
		String result="";
		try {
			//商户订单号，商户网站订单系统中唯一订单号，必填
			String out_trade_no = new String(request.getParameter("WIDout_trade_no").getBytes("ISO-8859-1"),"UTF-8");
			//付款金额，必填
			String total_amount = new String(request.getParameter("amount").getBytes("ISO-8859-1"),"UTF-8");
			//订单名称，必填
			String subject = new String(request.getParameter("WIDsubject").getBytes("ISO-8859-1"),"UTF-8");
			//商品描述，可空
			String body = new String(request.getParameter("WIDbody").getBytes("ISO-8859-1"),"UTF-8");
			
			alipayRequest.setBizContent("{\"out_trade_no\":\""+ out_trade_no +"\"," 
					+ "\"total_amount\":\""+ total_amount +"\"," 
					+ "\"subject\":\""+ subject +"\"," 
					+ "\"body\":\""+ body +"\"," 
					+ "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");
			
			//若想给BizContent增加其他可选请求参数，以增加自定义超时时间参数timeout_express来举例说明
			//alipayRequest.setBizContent("{\"out_trade_no\":\""+ out_trade_no +"\"," 
			//		+ "\"total_amount\":\""+ total_amount +"\"," 
			//		+ "\"subject\":\""+ subject +"\"," 
			//		+ "\"body\":\""+ body +"\"," 
			//		+ "\"timeout_express\":\"10m\"," 
			//		+ "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");
			//请求参数可查阅【电脑网站支付的API文档-alipay.trade.page.pay-请求参数】章节
			
			result = alipayClient.pageExecute(alipayRequest).getBody();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (AlipayApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//输出
//		out.println(result);
		return result;		
	}*/
	
	
	
	
//	@RequestMapping(path="/showAlipay",method = {RequestMethod.POST,RequestMethod.GET})
//	public String showAlipay(Integer[] ids,Integer allmoney,HttpSession session) throws Exception{
//		Userinfo stu=(Userinfo)session.getAttribute("user");
//		String result = null;
//		System.out.println(stu);
//		System.out.println(ids);
//		System.out.println(allmoney);
//		if(stu!=null) {
//			for(Integer id:ids) {
//				
//				cardService.delOneShoppingCar(id);
//			}
//			String out_trade_no=UUID.randomUUID()+""+stu.getUserId();
//			String total_amount=allmoney+"";
//			String subject=UUID.randomUUID()+""+stu.getUserName();
//			AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.app_id, AlipayConfig.merchant_private_key, "json", AlipayConfig.charset, AlipayConfig.alipay_public_key, AlipayConfig.sign_type);
//			//设置请求参数
//			AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
//			alipayRequest.setReturnUrl(AlipayConfig.return_url);
//			alipayRequest.setNotifyUrl(AlipayConfig.notify_url);
//			//商品描述，可空
//			String body =null;
//			alipayRequest.setBizContent("{\"out_trade_no\":\""+ out_trade_no +"\"," 
//					+ "\"total_amount\":\""+ total_amount +"\"," 
//					+ "\"subject\":\""+ subject +"\"," 
//					+ "\"body\":\""+ body +"\"," 
//					+ "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");
//	        result=alipayClient.pageExecute(alipayRequest).getBody();
//	        System.out.println(result);
//		}
//		return result;
//	}

	
}
