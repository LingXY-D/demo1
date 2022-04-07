package com.example.demo1.service.impl;

import com.example.demo1.dao.QuestionMapper;
import com.example.demo1.dao.StageMapper;
import com.example.demo1.entity.Question;
import com.example.demo1.entity.Request;
import com.example.demo1.service.RequestService;
import com.example.demo1.dao.RequestMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

@Service("requestService")
public class RequestServiceImpl implements RequestService{
    @Autowired
    RequestMapper requestMapper;
    @Autowired
    QuestionMapper questionMapper;
    @Autowired
    StageMapper stageMapper;

    @Override
    public void newRequest(int stageId, int userId) {
        Request request = new Request(0, userId, stageId, 0, LocalDateTime.now(), 0, 0);
        requestMapper.newRequest(request);
    }

    @Override
    public void addCnt(int questionId, int userId) {    // 每答一题+1
        int stageId = questionMapper.selectByQuestionId(questionId).getStage_id();
        int requestId = requestMapper.selectBy2Id(stageId, userId).getId();
        requestMapper.addCnt(requestId);
    }

    @Override
    public void addScore(int questionId, int userId) {    // 答对正确+1
        int stageId = questionMapper.selectByQuestionId(questionId).getStage_id();
        int requestId = requestMapper.selectBy2Id(stageId, userId).getId();
        requestMapper.addScore(requestId);
    }

    @Override
    public void addTime(float consuming_time, int questionId, int userId) {
        int stageId = questionMapper.selectByQuestionId(questionId).getStage_id();
        int requestId = requestMapper.selectBy2Id(stageId, userId).getId();
        requestMapper.addTime(consuming_time, requestId);
    }

    @Override
    public int countScore(int userId, int stageId) {
        int score = requestMapper.selectBy2Id(stageId, userId).getScore();  // 正确题数
        int perQ = stageMapper.perQ(stageId);   // 每题得分
        return (int)score * perQ;
    }

    @Override
    public HashMap<Request, Integer> rank(int userId, int stageId) {
        HashMap<Request, Integer> rank = new HashMap<>();
//        List<Request> query = new LinkedList<>();
        int userRank = requestMapper.userRank(userId, stageId);
        rank.put(requestMapper.selectBy2Id(stageId, userId), userRank);
        List<Request> query = requestMapper.ranking(stageId);
        int cnt = 1;
        for(Request re: query) {
            rank.put(re, cnt);
            cnt++;
        }
        return rank;
    }
}
