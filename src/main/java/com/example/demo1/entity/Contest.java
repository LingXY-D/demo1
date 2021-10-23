package com.example.demo1.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Contest {
    private int id;               // 比赛id
    private String contest_name;          // 关卡数量
    private String intro;         // 比赛开始时间
    private String code;          // guid
}
