package com.example.demo1.dao;

import com.example.demo1.entity.Stage;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface StageMapper {
    void newStage(Stage stage);
    void deleteStage(int id);
    int perQ(int stageId);
}
