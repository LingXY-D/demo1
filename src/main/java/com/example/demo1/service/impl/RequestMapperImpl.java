package com.example.demo1.service.impl;

import com.example.demo1.entity.Request;
import com.example.demo1.service.RequestService;
import com.example.demo1.dao.RequestMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("requestService")
public class RequestMapperImpl implements RequestService{
    @Autowired
    RequestMapper requestMapper;

    @Override
    public void count(String time) {

    }
}
