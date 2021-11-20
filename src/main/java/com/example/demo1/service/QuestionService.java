package com.example.demo1.service;

import com.example.demo1.entity.Question;

import java.util.HashMap;
import java.util.List;

public interface QuestionService {
    public boolean isCorrect(String id, List<String> ans);
    public void addQuestion(Question question);
    public void deleteQuestion(int id);
    public boolean isStageBegin(int questionId, int userId);
    public Question getNext(int id);
}
