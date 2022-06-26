package com.example.demo.service;



import java.util.List;

import com.example.demo.pojo.Buygoods;
import com.example.demo.pojo.College;
import com.example.demo.pojo.Dept;
import com.example.demo.pojo.Goods;
import com.example.demo.pojo.Student;
import com.example.demo.pojo.Userinfo;

public interface ICardService {

	Dept selectByPrimaryKey(Integer deptId);
	
	Userinfo selectByNameAndPwd(String uname,String upass);
	
	boolean updateByPrimaryKeySelective(Userinfo record);//更新密码
	
	//查询所有学生
    List<Student> listAllStu();    
    Student queryStuById(Integer stuId);    
    //查询登录Stu
//    Student queryByStu(Student stu);
    
    //查询所有学院   
    List<College> listAllCollege();
   
    boolean updateStudent(Student record);//更新学生
    boolean deleteStudent(Integer id);//删除学生
    boolean addStudent(Student record);//增加学生  
    boolean deleteAllStu(Integer[] ids);//删除所有学生
    //模糊查询学生
    List<Student> listStuByQueryContent(String content);
    
    
    //查询所有Goods
    List<Goods> listAllGoods();
    //新增good
    boolean addGood(Goods record);
    //查询单个good
    Goods selectById(Integer goodsId);
    //更新good
    boolean updateGood(Goods record);
    //删除good
    boolean deleteGood(Integer goodId);
    //模糊查询goods
    List<Goods> listGoodsByContent(String content);
    //查询goodsbyIds
    List<Goods> listGoodsByIds(Integer [] goodIds);
    
    
    //添加购物车
  // boolean addGoodsCar(Integer buygoodid,Integer stuid);
   //查询购物车是否有此商品
   //Buygoods querytCarGoodByPrimaryKey(Integer buygoodsId); 
   //查询购物车
  // List<Buygoods> listBuyGoods();
   //删除购物车商品
   //boolean deleteCarGoodById(Integer id);
}
