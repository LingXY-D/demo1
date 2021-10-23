package com.example.demo1.controller;

import com.example.demo1.entity.Contest;
import com.example.demo1.entity.Level;
import com.example.demo1.service.LevelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/level")
public class LevelController {
    @Autowired
    LevelService levelService;

    @PostMapping("/new")
    public void newLevel(@RequestBody Level level) {
        levelService.newLevel(level);
    }

    @PostMapping("/delete")
    public void deleteLevel(@RequestBody int id) {
        levelService.deleteLevel(id);
    }
}
