package com.example.demo1.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Grade {           // 每个关卡的得分
    private int levelId;       // 关卡id
    private int userId;        // yon
    private int score;         // 总得分
}
