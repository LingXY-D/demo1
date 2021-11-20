package com.example.demo1.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.time.LocalTime;
@Data
@AllArgsConstructor
public class Stage {
    private int id;
    private int contest_id;
    private int index;
    private int time_limit;
    private LocalDateTime end_time;
    private LocalDateTime start_time;
    private int score;

    public Stage() {
        id = contest_id = index = time_limit = score = 0;
        end_time = start_time = null;
    }
}
