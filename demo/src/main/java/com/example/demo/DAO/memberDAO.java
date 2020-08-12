package com.example.demo.DAO;


import java.util.List;

import com.example.demo.model.member;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface memberDAO {

    int selectLogin(member memeber);
    int selectDup(member memeber);
    int authCheck(member memeber);
    List<member> selectUserList();

    int updateUser(member memeber);
    int deleteUser(member memeber);
    int insertJoin(member memeber);
}
  