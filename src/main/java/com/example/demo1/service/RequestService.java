package com.example.demo1.service;

public interface RequestService {
    public int countScore(int userId, int stageId);
    public void newRequest(int stageId, int userId);
    public void addCnt(int questionId, int userId);
    public void addTime(float consuming_time, int questionId, int userId);
    public void addScore(int questionId, int userId);
}
