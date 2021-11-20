package com.example.demo1.controller;

import com.example.demo1.annotation.AdminLogin;
import com.example.demo1.entity.Stage;
import com.example.demo1.service.StageService;
import com.example.demo1.util.Result;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    private LocalDateTime StartTime(String strTime) throws ParseException {
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
            end_t = StartTime(map.get("end_time"));
            start_t = StartTime(map.get("start_time"));
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

    @AdminLogin
    @PostMapping("/delete")
    public void deleteStage(@RequestBody int id) {
        stageService.deleteStage(id);
    }

}
