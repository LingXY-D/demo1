package com.example.demo1.service.impl;

import com.example.demo1.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo1.dao.QuestionMapper;
import com.example.demo1.entity.Question;

@Service("questionService")
public class QuestionServiceImpl implements QuestionService {
    @Autowired
    QuestionMapper questionMapper;

    @Override
    public boolean isCorrect(Question question) {
        if(questionMapper.selectByQuestionId(question.getId()).getAnswer().equals(question.getAnswer())) return true;
        else return false;
    }
}
