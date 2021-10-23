package com.example.demo1.dao;

import com.example.demo1.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {
    User selectByUsername(String username);
    void insertUser(User user);
    void updateUser(int userId, String password);
    User selectByOpenid(String openid);
    User selectByUserId(int userId);
}
