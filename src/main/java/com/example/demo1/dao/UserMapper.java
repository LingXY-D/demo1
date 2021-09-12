package com.example.demo1.dao;

import com.example.demo1.entity.user;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {
    user selectByUsername(String username);
    void insertUser(user user);
    void updateUser(String username, String password);
    user selectByOpenid(String openid);
}
