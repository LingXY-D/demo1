package com.example.demo1.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Rank {
    private int userId;
    private int score;
    private float sumTime;
}
