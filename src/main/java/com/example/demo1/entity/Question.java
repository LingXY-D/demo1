package com.example.demo1.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Question {
    private int id;               // 题目id
    private int stage_id;         // 单选、多选或者判断
    private String statement;     // 题干
    private String option1;       // 选项
    private String option2;
    private String option3;
    private String option4;
    private String answer;        // 答案
}

