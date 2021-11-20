package com.example.demo1.service;

import com.example.demo1.entity.Stage;

import java.time.LocalDateTime;

public interface StageService {
    public void newStage(Stage stage);
    public void deleteStage(int id);
    public LocalDateTime setEndTime(int questionId);
}
