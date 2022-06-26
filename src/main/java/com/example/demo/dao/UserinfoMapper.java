package com.example.demo.dao;

import org.apache.ibatis.annotations.Param;

import com.example.demo.pojo.Userinfo;

public interface UserinfoMapper {
    int deleteByPrimaryKey(Integer userId);

    int insert(Userinfo record);

    int insertSelective(Userinfo record);

    Userinfo selectByPrimaryKey(Integer userId);

    int updateByPrimaryKeySelective(Userinfo record);//更新密码

    int updateByPrimaryKey(Userinfo record);
    
    Userinfo selectByNameAndPwd(@Param("uname")String uname,@Param("upass")String upass);
    
}