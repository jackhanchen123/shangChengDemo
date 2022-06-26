package com.example.demo.dao;

import java.util.List;

import com.example.demo.pojo.Buygoods;

public interface BuygoodsMapper {
    int deleteByPrimaryKey(Integer buygoodsId);//删除

    int insert(Buygoods record);

    int insertSelective(Buygoods record);//添加购物车

    Buygoods selectByPrimaryKey(Integer buygoodsId);//查询购物车是否有此商品

    int updateByPrimaryKeySelective(Buygoods record);//更新购物车

    int updateByPrimaryKey(Buygoods record);
    
    //查询购物车
    List<Buygoods> listBuyGoods();
    
    
}