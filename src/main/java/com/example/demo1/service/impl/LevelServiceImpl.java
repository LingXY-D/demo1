package com.example.demo1.service.impl;

import com.example.demo1.dao.LevelMapper;
import com.example.demo1.entity.Level;
import com.example.demo1.service.LevelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("levelService")
public class LevelServiceImpl implements LevelService {
    @Autowired
    LevelMapper levelMapper;

    @Override
    public void newLevel(Level level) {
        levelMapper.newLevel(level);
    }
}
