package com.example.demo1.controller;

import com.example.demo1.annotation.AdminLogin;
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

    @AdminLogin
    @PostMapping("/new")
    public Result newContest(@RequestBody Contest contest) {
        try{
            contestService.newContest(contest);
        } catch (Exception e){
            return new Result(-1, null, e.getMessage());
        }
        return new Result(0, contest, "添加成功");
    }

    @AdminLogin
    @PostMapping("/delete")
    public Result deleteContest(@RequestBody int guid) {
        try{
            contestService.deleteContest(guid);
        } catch (Exception e){
            return new Result(-1, null, e.getMessage());
        }
        return new Result(0, "当前已删除比赛：" + guid, "删除成功");
    }
}