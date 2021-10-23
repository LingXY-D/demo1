package com.example.demo1.controller;

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

    @PostMapping("/new")
    public void newStage(@RequestBody HashMap<String,String> map) {
        Stage stage = new Stage(Integer.parseInt(map.get("id")),
                Integer.parseInt(map.get("contest_id")),
                Integer.parseInt(map.get("index")),
                Integer.parseInt(map.get("time_limit")),
                null,
                null,
                Integer.parseInt(map.get("score"))
        );
        LocalTime last_t = null;
        LocalDateTime start_t = null;
        try {
            last_t = LastTime(map.get("last_time"));
            start_t = StartTime(map.get("start_time"));
        } catch (Exception e) {}
        stage.setLast_time(last_t);
        stage.setStart_time(start_t);
        stageService.newStage(stage);
    }

    @PostMapping("/delete")
    public void deleteStage(@RequestBody int id) {
        stageService.deleteStage(id);
    }

}
