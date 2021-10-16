package com.example.demo1.dao;

import com.example.demo1.entity.Level;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface LevelMapper {
    void newLevel(Level level);
}
