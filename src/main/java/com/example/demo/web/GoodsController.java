package com.example.demo.web;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.pojo.College;
import com.example.demo.pojo.Goods;
import com.example.demo.pojo.Student;
import com.example.demo.pojo.Userinfo;
import com.example.demo.service.ICardService;

@RestController
@RequestMapping("/card")
@CrossOrigin(allowCredentials="true", allowedHeaders="*")
public class GoodsController {
	@Autowired
	private ICardService cardService;	
	
	@RequestMapping(path = "/listgoods",method = {RequestMethod.GET,RequestMethod.POST})
	public List<Goods> listAllGoods() {		
		
		return cardService.listAllGoods();
	}
	
	@RequestMapping(path = "/addgood",method = {RequestMethod.GET,RequestMethod.POST})
	public boolean addGoods(@RequestBody Goods good) {		
		System.out.println("进入添加了");
		System.out.println(good);
		boolean b=false;
		if (cardService.addGood(good)) {//新增good成功
			b=true;
		}
		return b;
	}
	
	@RequestMapping(path = "/querygood",method = {RequestMethod.GET,RequestMethod.POST})
	public Goods queryGood(Integer goodid) {		
		//System.out.println("查询单个good进入了");
		//System.out.println(goodid);	
		Goods good=cardService.selectById(goodid);		
		return good;
	}
	
	@RequestMapping(path = "/updategood",method = {RequestMethod.GET,RequestMethod.POST})
	public boolean updateGood(@RequestBody Goods good) {		
		//System.out.println("查询单个good进入了");
		//System.out.println(good);				
		return cardService.updateGood(good);
	}
	
	@RequestMapping(path = "/deletegood",method = {RequestMethod.GET,RequestMethod.POST})
	public boolean delteGood(Integer goodId) {		
		//System.out.println("删除的商品Id是："+goodId);			
		return cardService.deleteGood(goodId);
//		return false;
	}
	
	@RequestMapping(path = "/deleteselectgoods",method = {RequestMethod.GET,RequestMethod.POST})
	public boolean deleteSelectGoods(@RequestBody Integer [] ids) {
		boolean b=false;
		for (Integer id : ids) {
			System.out.println("删除的id是"+id);
			 b=cardService.deleteGood(id);
 		}				
		//return cardService.deleteGood(goodId);
 		return b;
	}
	
	@RequestMapping(path = "/querygoods",method = {RequestMethod.GET,RequestMethod.POST})
	public List<Goods> querygoods(String content) {		
 		return cardService.listGoodsByContent(content);
	}
	
	@RequestMapping(path = "/querybuygoods",method = {RequestMethod.GET,RequestMethod.POST})
	public List<Goods> queryBuyGoods(@RequestBody Integer [] ids) {
//		System.out.println(ids);
		for (Integer id : ids) {
			System.out.println(id);
		}		
 		return  cardService.listGoodsByIds(ids);
	}
	
	
	
}
