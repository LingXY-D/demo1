package com.example.demo1.controller;

import com.example.demo1.annotation.AdminLogin;
import com.example.demo1.annotation.UserLogin;
import com.example.demo1.entity.Contest;
import com.example.demo1.entity.Question;
import com.example.demo1.service.QuestionService;
import com.example.demo1.service.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api/question")
public class QuestionController {
    @Autowired
    QuestionService questionService;
    ScoreService scoreService;

    @UserLogin
    @PostMapping("/judge")
    public boolean isCorrect(@RequestBody HashMap<String, String> question) {
        String id = question.get("id");
        List<String> ans = new ArrayList<>();
        for(String val: question.values()) {
            ans.add(val);
        }
        if(questionService.isCorrect(id, ans)) {
            return true;
        }
        else return false;
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
