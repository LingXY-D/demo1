package com.example.demo1.controller;

import com.example.demo1.annotation.AdminLogin;
import com.example.demo1.annotation.UserLogin;
import com.example.demo1.entity.Question;
import com.example.demo1.entity.Stage;
import com.example.demo1.service.QuestionService;
import com.example.demo1.service.RequestService;
import com.example.demo1.service.StageService;
import com.example.demo1.util.JwtUtil;
import com.example.demo1.util.Result;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.sql.Time;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

@RestController
@RequestMapping("/api/stage")
public class StageController {
    @Autowired
    StageService stageService;
    @Autowired
    RequestService requestService;
    @Autowired
    QuestionService questionService;

    private LocalDateTime SetTime(String strTime) throws ParseException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        if (StringUtils.isBlank(strTime)) return null;
        else return LocalDateTime.parse(strTime, formatter);
    }

//    private LocalTime LastTime(String strTime) throws ParseException {
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
//        LocalTime last_t = LocalTime.parse(strTime, formatter);
//        if (StringUtils.isBlank(strTime)) return null;
//        else return last_t;
//    }

    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

    @AdminLogin
    @PostMapping("/new")
    public Result newStage(@RequestBody HashMap<String,String> map) {
        LocalDateTime start_t = null;
        LocalDateTime end_t = null;
        try {
            end_t = SetTime(map.get("end_time"));
            start_t = SetTime(map.get("start_time"));
        } catch (Exception e) {
            return new Result(-1, null, e.getMessage());
        }
        Stage stage = new Stage();
        stage.setContest_id(Integer.parseInt(map.get("contest_id")));
        stage.setIndex(Integer.parseInt(map.get("index")));
        stage.setTime_limit(Integer.parseInt(map.get("time_limit")));
        stage.setScore(Integer.parseInt(map.get("score")));
        stage.setStart_time(start_t);
        stage.setEnd_time(end_t);
        stageService.newStage(stage);
        return new Result(0, stage, "添加成功");
    }

    @UserLogin
    @PostMapping("/start")  // 需要检测是否是第一次开始答题
    public Question startStage(HttpServletRequest request, @RequestBody HashMap<String,String> map) {
        int stageId = Integer.valueOf(map.get("stageId"));
        String token = request.getHeader("Authorization").substring(7);
        int userId = JwtUtil.getUserID(token);
        int q = requestService.newRequest(stageId, userId);     // 新建request元组
        if(q == 0) return questionService.firstQ(stageId);
        return questionService.getN(q, stageId);
    }


    @AdminLogin
    @PostMapping("/delete")
    public Result deleteStage(@RequestBody int id) {
        try{
            stageService.deleteStage(id);
        } catch (Exception e){
            return new Result(-1, null, e.getMessage());
        }
        return new Result(0, "当前已删除关卡：" + id, "删除成功");
    }

}
