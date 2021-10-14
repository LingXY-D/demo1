package com.example.demo1.controller;

import com.example.demo1.entity.Contest;
import com.example.demo1.service.ContestService;
import com.example.demo1.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/contest")
public class ContestController {

    @Autowired
    ContestService contestService;

    @PostMapping("/new")
    public void newContest(@RequestBody Contest contest) {
        contestService.newContest(contest);
//        int guid;     // 比赛唯一编号
//        int num;      // 关卡数量
//        int start;    // 比赛开始时间
//        int lim;      // 比赛时限
//        String name;  // 比赛名称
    }

//    @PostMapping("/newLevel")
//    public void newLevel(@RequestBody Contest contest) {
//        contestService.newContest(contest);
//    }

//    @AdminLogin
//    @PostMapping("/add")
//    public void add(@RequestBody String id) {
//
////        int guid;     // 比赛唯一编号
////        int num;      // 关卡数量
////        int start;    // 比赛开始时间
////        int lim;      // 比赛时限
////        String name;  // 比赛名称
//    }
//
//    @AdminLogin
//    @PostMapping("/delete")
//    public void delete(@RequestBody String ans) {
//
//    }
}