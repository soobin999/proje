package com.example.demo.controller;


import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import com.example.demo.model.member;
import com.example.demo.DAO.memberDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class RtContreoller {

    @Autowired(required = false)
    memberDAO memberDAO;
    //로그인
    @RequestMapping("/login")
    @ResponseBody
    public Object login(member member,HttpSession session) {
        System.out.println(member);
        HashMap<String,Integer> log=new HashMap<>();
        int logi=memberDAO.selectLogin(member);
        if(logi==1){
            session.setAttribute("userID",member.getUid());
        }
        log.put("success",logi);   
        return log;
    }

    //중복체크
    @RequestMapping("/idDupCheck")
    public Object idDupCheck(member member){
        HashMap<String,Integer> dup=new HashMap<>();
        dup.put("dup",memberDAO.selectDup(member));
        return dup;
    }

    @RequestMapping("/join")
    public Object join(member member) {
        HashMap<String, Integer> join = new HashMap<>();
        join.put("join", memberDAO.insertJoin(member));
        
        return join;
    }

    @RequestMapping("/userUpdate")
    public Object userUpdate(member memeber){
        HashMap<String, Integer> upda = new HashMap<>();
        upda.put("update",memberDAO.updateUser(memeber));
        return upda;
    }
    //탈퇴
    @RequestMapping("/withdrawal")
    public Object withdrawl(member member){
        HashMap<String, Integer> with = new HashMap<>();
        with.put("with", memberDAO.deleteUser(member));
        return with;
    }

    //회원목록조회
    @RequestMapping("/userListSelect")
    public Object userListSelect(member member){
        List<member> userList = memberDAO.selectUserList();
        return userList;
    } 

    @RequestMapping("/logout")
    public Object logout(HttpSession session){
        session.invalidate();
        HashMap<String, String> out = new HashMap<>();
        out.put("log","성공");
        return out;
    }

    @RequestMapping("authCheck")
    public Object authCheck(member member){
        HashMap<String, Integer> auth = new HashMap<>();
        auth.put("auth",memberDAO.authCheck(member));
        return auth;
    }
}
  