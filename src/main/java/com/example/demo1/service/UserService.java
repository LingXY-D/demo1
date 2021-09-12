package com.example.demo1.service;

import com.example.demo1.entity.user;

public interface UserService {
    public user login(String username, String password);
    public user register(String username, String password) throws Exception;
    public user modifyPassword(String username, String newpassword);
    public user selectByOpenid(String openid);
    public user selectByUsername(String username);
}
