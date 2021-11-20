package com.example.demo1.controller;

import com.example.demo1.annotation.AdminLogin;
import com.example.demo1.annotation.UserLogin;
import com.example.demo1.entity.Question;
import com.example.demo1.entity.Request;
import com.example.demo1.service.QuestionService;
import com.example.demo1.service.RequestService;
import com.example.demo1.service.StageService;
import com.example.demo1.util.JwtUtil;
import com.example.demo1.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api/question")
public class QuestionController {
    @Autowired
    QuestionService questionService;
    @Autowired
    RequestService requestService;
    @Autowired
    StageService stageService;

    @UserLogin
    @PostMapping("/judge")
    public Result isCorrect(HttpServletRequest request, @RequestBody HashMap<String, String> question) {
        String id = question.get("id");
        float conduming_time = Float.parseFloat(question.get("consuming_time"));
        List<String> ans = new ArrayList<>();
        for(String val: question.values()) {
            ans.add(val);
        }
        String token = request.getHeader("Authorization").substring(7);
        int userId = JwtUtil.getUserID(token);
        requestService.addCnt(Integer.valueOf(id), userId);     // 题目id和用户id
        requestService.addTime(conduming_time, Integer.valueOf(id), userId);
        Question nextQ = questionService.getNext(Integer.valueOf(id));

        if(questionService.isCorrect(id, ans)) {
            requestService.addScore(Integer.valueOf(id), userId);
            return new Result(1, nextQ, "回答正确");
        }
        return new Result(-1, nextQ, "回答错误");
    }

    @AdminLogin
    @PostMapping("/add")
    public void addQuestion(@RequestBody Question question) {
        questionService.addQuestion(question);
    }

    @AdminLogin
    @PostMapping("/delete")
    public void deleteQuestion(@RequestBody int id) {
        questionService.deleteQuestion(id);
    }
}
