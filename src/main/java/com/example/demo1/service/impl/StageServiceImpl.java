package com.example.demo1.service.impl;

import com.example.demo1.dao.QuestionMapper;
import com.example.demo1.dao.StageMapper;
import com.example.demo1.entity.Question;
import com.example.demo1.entity.Stage;
import com.example.demo1.service.QuestionService;
import com.example.demo1.service.StageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service("stageService")
public class StageServiceImpl implements StageService {
    @Autowired
    StageMapper stageMapper;
    @Autowired
    QuestionMapper questionMapper;

    @Override
    public void newStage(Stage stage) {
        stageMapper.newStage(stage);
    }

    @Override
    public void deleteStage(int id) {
        stageMapper.deleteStage(id);
    }

}
