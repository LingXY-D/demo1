package com.example.demo1.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalTime;

@Data
@AllArgsConstructor
public class Score {
    private int id;
    private int user_id;
    private int stage_id;
    private int score;
    private LocalTime consuming_time;
}
