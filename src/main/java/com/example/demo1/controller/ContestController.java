package com.example.demo1.controller;

import com.example.demo1.annotation.AdminLogin;
import com.example.demo1.entity.Contest;
import com.example.demo1.service.ContestService;
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
    public void newContest(@RequestBody Contest contest) {
        contestService.newContest(contest);
    }

    @AdminLogin
    @PostMapping("/delete")
    public void deleteContest(@RequestBody int guid) {
        contestService.deleteContest(guid);
    }
}