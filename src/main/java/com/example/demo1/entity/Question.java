package com.example.demo1.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Question {
    private int id;           // 题目id
    private int type;         // 单选、多选或者判断
    private String stem;      // 题干
    private String options; // 选项
    private String answer;    // 答案
}

