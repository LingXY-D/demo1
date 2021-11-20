package com.example.demo1.dao;

import com.example.demo1.entity.Stage;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
@Mapper
public interface StageMapper {
    void newStage(Stage stage);
    void deleteStage(int id);
    int perQ(int stageId);
    void setEndtime(int id, LocalDateTime end_time);
}
