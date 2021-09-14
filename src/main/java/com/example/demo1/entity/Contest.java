package com.example.demo1.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Contest {
    private int guid;     // 比赛唯一编号
    private int num;      // 关卡数量
    private int sta;    // 比赛开始时间
    private int lim;      // 比赛时限
    private String na;  // 比赛名称
}
