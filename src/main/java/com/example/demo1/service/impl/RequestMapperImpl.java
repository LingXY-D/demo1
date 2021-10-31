package com.example.demo1.service.impl;

import com.example.demo1.dao.QuestionMapper;
import com.example.demo1.dao.StageMapper;
import com.example.demo1.entity.Question;
import com.example.demo1.entity.Request;
import com.example.demo1.service.RequestService;
import com.example.demo1.dao.RequestMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("requestService")
public class RequestMapperImpl implements RequestService{
    @Autowired
    RequestMapper requestMapper;
    QuestionMapper questionMapper;
    StageMapper stageMapper;

    @Override
    public void newRequest(int questionId, int userId) {
        Question question = questionMapper.selectByQuestionId(questionId);
        int stageId = question.getStage_id();
        Request request = new Request(0, userId, stageId, 0, null, 0, 0);
        requestMapper.newRequest(request);
    }

    @Override
    public void addTotal(int questionId, int userId) {
        Question question = questionMapper.selectByQuestionId(questionId);
        int stageId = question.getStage_id();
        Request request = requestMapper.selectBy2Id(stageId, userId);
        int requestId = request.getId();
        requestMapper.addTotal(requestId);
    }

//    @Override
//    public void addTime(int questionId, int userId) {
//    }

    @Override
    public void addScore(int questionId, int userId) {
        Question question = questionMapper.selectByQuestionId(questionId);
        int stageId = question.getStage_id();
        Request request = requestMapper.selectBy2Id(stageId, userId);
        int requestId = request.getId();
        requestMapper.addScore(requestId);
    }

    @Override
    public int countScore(int userId, int stageId) {
        int score = requestMapper.selectBy2Id(stageId, userId).getScore();
        int perQ = stageMapper.perQ(stageId);
        return score * perQ;
    }
}
