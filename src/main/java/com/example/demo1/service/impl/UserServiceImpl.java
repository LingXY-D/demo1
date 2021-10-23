package com.example.demo1.service.impl;

import com.example.demo1.dao.UserMapper;
import com.example.demo1.entity.User;
import com.example.demo1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    UserMapper userMapper;
//    private user user;

    @Override
    public User login(String username, String password){
        User user = userMapper.selectByUsername(username);
        if(user != null && user.getPassword().equals(password)){
            return user;
        }
        return null;
    }

    @Override
    public User register(String username, String password) throws Exception {
        User user = userMapper.selectByUsername(username);
        if(user != null){
            throw new Exception("用户名已存在");
        }
        user = new User(0, username, password, "0",0);
        userMapper.insertUser(user);
        user = userMapper.selectByUsername(username);
        return user;
    }

    @Override
    public User modifyPassword(String username, String newpassword){
//        user user = userMapper.selectByUsername(username);
//        if(user == null){
//            throw new Exception("该用户不存在");
//        }
        userMapper.updateUser(username, newpassword);
        User user = userMapper.selectByUsername(username);
        return user;
    }

    public User selectByOpenid(String openid){
        User user = userMapper.selectByOpenid(openid);
        return user;
    }

    public User selectByUsername(String username){
        User user = userMapper.selectByUsername(username);
        return user;
    }
}













