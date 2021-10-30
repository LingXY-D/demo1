package com.example.demo1.service.impl;

import com.example.demo1.service.ScoreService;
import com.example.demo1.entity.Score;
import com.example.demo1.dao.ScoreMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("scoreService")
public class ScoreServiceImpl implements ScoreService {
    @Autowired
    ScoreMapper scoreMapper;

    @Override
    public void sum(int score) {

    }
}
