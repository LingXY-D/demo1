package com.example.demo1.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Contest {
    private int guid;     // 比赛唯一编号
    private int level_num;      // 关卡数量
    private int start_time;      // 比赛开始时间
    private int limit_time;      // 比赛时限
    private String contest_name;  // 比赛名称
}
