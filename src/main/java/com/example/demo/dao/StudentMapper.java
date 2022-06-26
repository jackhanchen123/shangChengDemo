package com.example.demo.dao;

import java.util.List;

import com.example.demo.pojo.Student;

public interface StudentMapper {
    int deleteByPrimaryKey(Integer stuId);//删除学生

    int insert(Student record);

    int insertSelective(Student record);//增加学生

    Student selectByPrimaryKey(Integer stuId);//

    int updateByPrimaryKeySelective(Student record);//更新

    int updateByPrimaryKey(Student record);
    //查询所有学生
    List<Student> listAllStu();
    //删除所有
    boolean deleteAllStu(Integer[] ids);
    //模糊查询学生
    List<Student> listStuByQueryContent(String content);
    
    //根据stu中登录名密码查询stu
    Student queryByStu(Student stu);
}