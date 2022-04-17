package com.example.demo1.service;

import com.example.demo1.entity.Question;

import java.util.HashMap;
import java.util.List;

public interface QuestionService {
    public boolean isCorrect(String id, List<String> ans);
    public void addQuestion(Question question);
    public void deleteQuestion(int id);
    public boolean isStageBegin(int questionId, int userId);
    public Question firstQ(int stageId);
    public Question getNext(int id);
    public Question getN(int no, int stageId);
    public Question isCurrentQ(int questionId, int userId);
}
