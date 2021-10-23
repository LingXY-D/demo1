package com.example.demo1.service.impl;

import com.example.demo1.dao.StageMapper;
import com.example.demo1.entity.Stage;
import com.example.demo1.service.StageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("stageService")
public class StageServiceImpl implements StageService {
    @Autowired
    StageMapper stageMapper;

    @Override
    public void newStage(Stage stage) {
        stageMapper.newStage(stage);
    }

    @Override
    public void deleteStage(int id) {
        stageMapper.deleteStage(id);
    }
}
