package com.example.demo1.controller;

import com.example.demo1.annotation.UserLogin;
import com.example.demo1.entity.Request;
import com.example.demo1.service.RequestService;
import com.example.demo1.util.JwtUtil;
import com.example.demo1.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

@RestController
@RequestMapping("/api/request")
public class RequestController {
    @Autowired
    RequestService requestService;

    @UserLogin
    @PostMapping("/countScore")
    public Result countScore(@RequestBody HashMap<String, String> cnt) {
        int userId = Integer.valueOf(cnt.get("userId"));
        int stageId = Integer.valueOf(cnt.get("stageId"));
        int totalScore = 0;
        try{
            totalScore = requestService.countScore(userId, stageId);
        } catch (Exception e){
            return new Result(-1, null, e.getMessage());
        }
        return new Result(0, totalScore, "查询成功");
    }

    @UserLogin
    @PostMapping("/rank")
    public Result rank(HttpServletRequest request, @RequestBody HashMap<String, String> query) {
        int stageId = Integer.valueOf(query.get("stageId"));
        String token = request.getHeader("Authorization").substring(7);
        int userId = JwtUtil.getUserID(token);
        HashMap<Request, Integer> result = requestService.rank(userId, stageId);
        return new Result(0, result, "查询成功");
    }
}
