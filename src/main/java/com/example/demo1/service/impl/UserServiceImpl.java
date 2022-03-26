package com.example.demo1.service.impl;

import com.example.demo1.dao.UserMapper;
import com.example.demo1.entity.User;
import com.example.demo1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    UserMapper userMapper;
//    private user user;

    @Override
    public User login(String username, String password) {
        User user = userMapper.selectByUsername(username);
        if (user == null) return null;
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        if (passwordEncoder.matches(password, user.getPassword())) {
            user.setPassword(passwordEncoder.encode(password));
            userMapper.updateUser(user.getId(), user.getPassword());
            return user;
        };
        return null;
    }

    @Override
    public User register(String username, String password) throws Exception {
        User user = userMapper.selectByUsername(username);
        if(user != null){
            throw new Exception("用户名已存在");
        }
        user = new User(0, username, password, "0", 0);
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userMapper.insertUser(user);
//        user = userMapper.selectByUsername(username);
        return user;
    }

    @Override
    public User modifyPassword(int userId, String password, String newpassword){
        User user = userMapper.selectByUserId(userId);
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        if (passwordEncoder.matches(password, user.getPassword())) {
            newpassword = passwordEncoder.encode(newpassword);
            userMapper.updateUser(userId, newpassword);
            user.setPassword(newpassword);
            return user;
        }
        return null;
    }

    @Override
    public boolean isAdmin(int userid) {
        User user = userMapper.selectByUserId(userid);
        return user.getAdmin() == 1;
    }

    @Override
    public User selectByOpenid(String openid){
        User user = userMapper.selectByOpenid(openid);
        return user;
    }

    @Override
    public User selectByUsername(String username){
        User user = userMapper.selectByUsername(username);
        return user;
    }
}













