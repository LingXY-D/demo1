package com.example.demo1.service.impl;

import com.example.demo1.dao.UserMapper;
import com.example.demo1.entity.user;
import com.example.demo1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    UserMapper userMapper;
//    private user user;

    @Override
    public user login(String username, String password){
        user user = userMapper.selectByUsername(username);
        if(user != null && user.getPassword().equals(password)){
            return user;
        }
        return null;
    }

    @Override
    public user register(String username, String password) throws Exception {
        user user = userMapper.selectByUsername(username);
        if(user != null){
            throw new Exception("用户名已存在");
        }
        user = new user(0, username, password, "0");
        userMapper.insertUser(user);
        user = userMapper.selectByUsername(username);
        return user;
    }

    @Override
    public user modifyPassword(String username, String newpassword){
//        user user = userMapper.selectByUsername(username);
//        if(user == null){
//            throw new Exception("该用户不存在");
//        }
        userMapper.updateUser(username, newpassword);
        user user = userMapper.selectByUsername(username);
        return user;
    }

    public user selectByOpenid(String openid){
        user user = userMapper.selectByOpenid(openid);
        return user;
    }

    public user selectByUsername(String username){
        user user = userMapper.selectByUsername(username);
        return user;
    }
}













