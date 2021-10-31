package com.example.demo1.service.impl;

import com.example.demo1.dao.RequestMapper;
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
    RequestMapper requestMapper;

    @Override
    public boolean isCorrect(String id, List<String> ans) {
        int tempId = Integer.valueOf(id);
        Question question = questionMapper.selectByQuestionId(tempId);
        String tempAnswer = question.getAnswer();
        String[] standard_ans = tempAnswer.split(",");
        if((ans.size() - 1) != standard_ans.length) return false;
        for(String answer: standard_ans) {
            if(!ans.contains(answer)) return false;
        }
        return true;
    }

    @Override
    public boolean isStageBegin(int questionId, int userId) {
        Question question = questionMapper.selectByQuestionId(questionId);
        int stageId = question.getStage_id();
        if(requestMapper.selectBy2Id(stageId, userId) != null) return true;
        return false;
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
