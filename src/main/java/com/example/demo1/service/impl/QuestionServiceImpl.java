package com.example.demo1.service.impl;

import com.example.demo1.dao.RequestMapper;
import com.example.demo1.entity.Request;
import com.example.demo1.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo1.dao.QuestionMapper;
import com.example.demo1.entity.Question;

import java.util.HashMap;
import java.util.List;

@Service("questionService")
public class QuestionServiceImpl implements QuestionService {
    @Autowired
    QuestionMapper questionMapper;
    @Autowired
    RequestMapper requestMapper;

    @Override
    public boolean isCorrect(String id, List<String> ans) {
        int tempId = Integer.valueOf(id);
        Question question = questionMapper.selectByQuestionId(tempId);
        String tempAnswer = question.getAnswer();
        String[] standard_ans = tempAnswer.split(",");
        if((ans.size() - 2) != standard_ans.length) return false;
        for(String answer: standard_ans) {
            if(!ans.contains(answer)) return false;
        }
        return true;
    }

    @Override
    public boolean isStageBegin(int questionId, int userId) {
        Question question = questionMapper.selectByQuestionId(questionId);
        int stageId = question.getStage_id();
        Request req = requestMapper.selectBy2Id(stageId, userId);
        if(req != null)
            return true;
        else
            return false;
    }

    @Override
    public Question firstQ(int stageId) {
        return questionMapper.firstQ(stageId);
    }

    @Override
    public Question getNext(int id) {
        return questionMapper.getNext(id, questionMapper.selectByQuestionId(id).getStage_id());
    }

    @Override
    public void addQuestion(Question question) {
        questionMapper.addQuestion(question);
    }

    @Override
    public void deleteQuestion(int id) {
        questionMapper.deleteQuestion(id);
    }
}
