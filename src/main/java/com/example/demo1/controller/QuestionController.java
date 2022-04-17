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
        Question nextQ = questionService.getNext(Integer.valueOf(id));

        // 应回答的题目：
        Question temp = questionService.isCurrentQ(Integer.valueOf(id), userId);
        if(temp != null) {
            if(temp.getId() == 0) return new Result(2,null,"本关卡回答已结束");
            return new Result(3, temp, "当前题目已回答，跳转应答题目");
        }

        try{
            requestService.addCnt(Integer.valueOf(id), userId);     // 题目id和用户id
            requestService.addTime(conduming_time, Integer.valueOf(id), userId);
            if(questionService.isCorrect(id, ans)) {
                requestService.addScore(Integer.valueOf(id), userId);
                if(nextQ == null) {
                    return new Result(1, null, "回答正确，本关卡回答已结束");
                }
                return new Result(1, nextQ, "回答正确");
            }
        } catch (Exception e){
            return new Result(-1, null, e.getMessage());
        }
        if(nextQ == null) {
            return new Result(0, null, "回答错误，本关卡回答已结束");
        }
        return new Result(0, nextQ, "回答错误");
    }

    @AdminLogin
    @PostMapping("/add")
    public Result addQuestion(@RequestBody Question question) {
        try{
            questionService.addQuestion(question);
        } catch (Exception e){
            return new Result(-1, null, e.getMessage());
        }
        return new Result(0, question, "题目添加成功");
    }

    @AdminLogin
    @PostMapping("/delete")
    public Result deleteQuestion(@RequestBody int id) {
        try{
            questionService.deleteQuestion(id);
        } catch (Exception e){
            return new Result(-1, null, e.getMessage());
        }
        return new Result(0, "当前已删除题目：" + id, "删除成功");
    }
}
