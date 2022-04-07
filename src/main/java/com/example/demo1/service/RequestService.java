package com.example.demo1.service;

import com.example.demo1.entity.Request;

import java.util.HashMap;
import java.util.List;

public interface RequestService {
    public int countScore(int userId, int stageId);
    public void newRequest(int stageId, int userId);
    public void addCnt(int questionId, int userId);
    public void addTime(float consuming_time, int questionId, int userId);
    public void addScore(int questionId, int userId);
    public HashMap<Request, Integer> rank(int userId, int stageId);
}
