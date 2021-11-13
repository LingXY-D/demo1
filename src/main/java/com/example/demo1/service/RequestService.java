package com.example.demo1.service;

public interface RequestService {
    public int countScore(int userId, int stageId);
    public void newRequest(int questionId, int userId);
    public void addCnt(int questionId, int userId);
//    public void addTime(int questionId, int userId);
    public void addScore(int questionId, int userId);
}
