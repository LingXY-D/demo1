package com.example.demo1.controller;

import com.example.demo1.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequestMapping("/api/request")
public class RequestController {
    @Autowired
    RequestService requestService;

    @PostMapping("/countScore")
    public int countScore(@RequestBody HashMap<String, String> cnt) {
        int userId = Integer.valueOf(cnt.get("userId"));
        int stageId = Integer.valueOf(cnt.get("stageId"));
        int totalScore = requestService.countScore(userId, stageId);
        return totalScore;
    }

//    @PostMapping("/ranking")
//    public
}
