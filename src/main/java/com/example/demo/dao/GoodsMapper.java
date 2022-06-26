package com.example.demo.dao;

import java.util.List;

import com.example.demo.pojo.Goods;

public interface GoodsMapper {
    int deleteByPrimaryKey(Integer goodsId);//删除good

    int insert(Goods record);

    int insertSelective(Goods record);//新增good

    Goods selectByPrimaryKey(Integer goodsId);//查询goodById

    int updateByPrimaryKeySelective(Goods record);//更新good

    int updateByPrimaryKey(Goods record);
    //所有goods
    List<Goods> listAllGoods();
    //模糊查询goods
    List<Goods> listByContent(String content);
    //查询goodsbyIds
    List<Goods> listGoodsByIds(Integer [] goodIds);
}