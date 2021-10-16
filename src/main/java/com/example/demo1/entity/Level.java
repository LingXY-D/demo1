package com.example.demo1.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Level {
    private int id;
    private String level_name;
    private int of_contest;     // 关卡所属的比赛guid号
    private int limit_time;     // 关卡总限制时间
}
