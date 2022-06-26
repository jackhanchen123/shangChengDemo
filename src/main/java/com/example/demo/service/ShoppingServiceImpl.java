package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dao.BuygoodsMapper;
import com.example.demo.dao.CollegeMapper;
import com.example.demo.dao.DeptMapper;
import com.example.demo.dao.GoodsMapper;
import com.example.demo.dao.StudentMapper;
import com.example.demo.dao.UserinfoMapper;
import com.example.demo.pojo.Buygoods;
import com.example.demo.pojo.College;
import com.example.demo.pojo.Dept;
import com.example.demo.pojo.Goods;
import com.example.demo.pojo.Student;
import com.example.demo.pojo.Userinfo;
import com.example.demo.util.MD5;
@Service
public class ShoppingServiceImpl implements IShoppingService{

	@Autowired
	private DeptMapper deptMapper;
	@Autowired
	private UserinfoMapper userinfoMapper;
	@Autowired
	private StudentMapper studentMapper;
	@Autowired
	private CollegeMapper collegeMapper;
	@Autowired
	private GoodsMapper GoodsMapper;
	@Autowired
	private BuygoodsMapper buygoodsMapper;
	
//	@Transactional(propagation = Propagation.SUPPORTS,isolation = Isolation.DEFAULT,readOnly = true)
//	public Dept selectByPrimaryKey(Integer deptId) {
//		// TODO Auto-generated method stub
//		return deptMapper.selectByPrimaryKey(deptId);
//	}
//
//	@Transactional(propagation = Propagation.SUPPORTS,isolation = Isolation.DEFAULT,readOnly = true)
//	public Userinfo selectByNameAndPwd(String uname,String upass ) {
//		
//		return userinfoMapper.selectByNameAndPwd(uname, MD5.enctypeMD5("haha"+upass));
//	}
//
//	@Transactional(propagation = Propagation.SUPPORTS,isolation = Isolation.DEFAULT,readOnly = true)
//	public List<Student> listAllStu() {		
//		return studentMapper.listAllStu();
//	}
//
//	@Transactional(propagation = Propagation.SUPPORTS,isolation = Isolation.DEFAULT,readOnly = true)
//	public Student queryStuById(Integer stuId) {
//		
//		return studentMapper.selectByPrimaryKey(stuId);
//	}
//
//	@Transactional(propagation = Propagation.SUPPORTS,isolation = Isolation.DEFAULT,readOnly = true)
//	public List<College> listAllCollege() {
//		
//		return collegeMapper.listAllCollege();
//	}
//
//	@Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,rollbackFor = Exception.class)
//	public boolean updateStudent(Student record) {
//		// TODO Auto-generated method stub
//		return studentMapper.updateByPrimaryKeySelective(record)>0;
//	}
//
//	@Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,rollbackFor = Exception.class)
//	public boolean deleteStudent(Integer id) {
//		// TODO Auto-generated method stub
//		return studentMapper.deleteByPrimaryKey(id)>0;
//	}
//
//	@Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,rollbackFor = Exception.class)
//	public boolean addStudent(Student record) {
//		
//		return studentMapper.insertSelective(record)>0;
//	}
//
//	@Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,rollbackFor = Exception.class)
//	public boolean deleteAllStu(Integer[] ids) {
//		// TODO Auto-generated method stub
//		return studentMapper.deleteAllStu(ids);
//	}
//
//	@Transactional(propagation = Propagation.SUPPORTS,isolation = Isolation.DEFAULT,readOnly = true)
//	public List<Student> listStuByQueryContent(String content) {
//		
//		return studentMapper.listStuByQueryContent(content);
//	}
//
//	@Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,rollbackFor = Exception.class)
//	public boolean updateByPrimaryKeySelective(Userinfo record) {	
//		record.setUserPasswd(MD5.enctypeMD5("haha"+record.getUserPasswd()));
//		return userinfoMapper.updateByPrimaryKeySelective(record)>0;
//	}
//
//	@Transactional(propagation = Propagation.SUPPORTS,isolation = Isolation.DEFAULT,readOnly = true)
//	public List<Goods> listAllGoods() {		
//		return GoodsMapper.listAllGoods();
//	}
//
//	@Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,rollbackFor = Exception.class)
//	public boolean addGood(Goods record) {		
//		return GoodsMapper.insertSelective(record)>0;
//	}
//
//	@Transactional(propagation = Propagation.SUPPORTS,isolation = Isolation.DEFAULT,readOnly = true)
//	public Goods selectById(Integer goodsId) {
//		// TODO Auto-generated method stub
//		return GoodsMapper.selectByPrimaryKey(goodsId);
//	}
//
//	@Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,rollbackFor = Exception.class)
//	public boolean updateGood(Goods record) {
//		// TODO Auto-generated method stub
//		return GoodsMapper.updateByPrimaryKeySelective(record)>0;
//	}
//
//	@Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,rollbackFor = Exception.class)
//	public boolean deleteGood(Integer goodId) {
//		// TODO Auto-generated method stub
//		return GoodsMapper.deleteByPrimaryKey(goodId)>0;
//	}
//
//	@Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,rollbackFor = Exception.class)
//	public List<Goods> listGoodsByContent(String content) {		
//		return GoodsMapper.listByContent(content);
//	}
//
//	@Transactional(propagation = Propagation.SUPPORTS,isolation = Isolation.DEFAULT,readOnly = true)
//	public List<Goods> listGoodsByIds(Integer[] goodIds) {
//		
//		return GoodsMapper.listGoodsByIds(goodIds);
//	}

	@Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,rollbackFor = Exception.class)
	public boolean addGoodsCar(Integer buygoodid,Integer stuid) {//加入购物车
		Buygoods record=new Buygoods();//购物车
		
		Goods good =GoodsMapper.selectByPrimaryKey(buygoodid);//从库存中查询此商品
		Buygoods buyGoods= buygoodsMapper.selectByPrimaryKey(buygoodid);//从购物车查询此商品
		boolean b=false;
		if (buyGoods!=null) {//购物车有此商品
			buyGoods.setBuygoodsBuynumber(buyGoods.getBuygoodsBuynumber()+1);//购买数量+1
			b=buygoodsMapper.updateByPrimaryKeySelective(buyGoods)>0;//更新到数据库
		}else {//购物车无此商品
			//record.setBuygoodsId(buygoodid);
			record.setGoods(good);//把商品加入到购物车
 			record.setBuygoodsBuynumber(1);//数量
 			b=buygoodsMapper.insertSelective(record)>0;//添加到数据库
		}
		return b;
	}

	@Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,rollbackFor = Exception.class)
	public Buygoods querytCarGoodByPrimaryKey(Integer buygoodsId) {
		// TODO Auto-generated method stub
		return buygoodsMapper.selectByPrimaryKey(buygoodsId);
	}

	@Transactional(propagation = Propagation.SUPPORTS,isolation = Isolation.DEFAULT,readOnly = true)
	public List<Buygoods> listBuyGoods() {
		// TODO Auto-generated method stub
		return buygoodsMapper.listBuyGoods();
	}
	
	@Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,rollbackFor = Exception.class)
	public boolean deleteCarGoodById(Integer id) {		
		return buygoodsMapper.deleteByPrimaryKey(id)>0;
	}

	@Transactional(propagation = Propagation.SUPPORTS,isolation = Isolation.DEFAULT,readOnly = true)
	public Student queryByStu(Student stu) {
		stu.setStuPasswd(MD5.enctypeMD5("haha"+stu.getStuPasswd()));
		//System.out.println("service里的stu:"+stu);
		return studentMapper.queryByStu(stu) ;
	}
	
	

}
