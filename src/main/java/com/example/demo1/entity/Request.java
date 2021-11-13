package com.example.demo1.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class Request {
    private int id;
    private int user_id;
    private int stage_id;
    private int cnt;
    private LocalDateTime start_time;
    private int score;
    private float consuming_time;
}
