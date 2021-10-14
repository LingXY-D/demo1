package com.example.demo1.controller;

import com.example.demo1.entity.Contest;
import com.example.demo1.entity.Question;
import com.example.demo1.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/question")
public class QuestionController {
    @Autowired
    QuestionService questionService;

    @PostMapping("/judge")
    public boolean isCorrect(@RequestBody Question question) {
        if(questionService.isCorrect(question)) return true;
        else return false;
    }
}
