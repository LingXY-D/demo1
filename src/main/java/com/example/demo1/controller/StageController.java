package com.example.demo1.controller;

import com.example.demo1.annotation.AdminLogin;
import com.example.demo1.entity.Stage;
import com.example.demo1.service.StageService;
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

    private LocalTime LastTime(String strTime) throws ParseException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        LocalTime last_t = LocalTime.parse(strTime, formatter);
        if (StringUtils.isBlank(strTime)) return null;
        else return last_t;
    }

    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

    @AdminLogin
    @PostMapping("/new")
    public void newStage(@RequestBody HashMap<String,String> map) {
        LocalTime last_t = null;
        LocalDateTime start_t = null;
        try {
            last_t = LastTime(map.get("last_time"));
            start_t = StartTime(map.get("start_time"));
        } catch (Exception e) {}
        Stage stage = new Stage(Integer.parseInt(map.get("id")),
                Integer.parseInt(map.get("contest_id")),
                Integer.parseInt(map.get("index")),
                Integer.parseInt(map.get("time_limit")),
                last_t,
                start_t,
                Integer.parseInt(map.get("score"))
        );
        stageService.newStage(stage);
    }

    @AdminLogin
    @PostMapping("/delete")
    public void deleteStage(@RequestBody int id) {
        stageService.deleteStage(id);
    }

}
