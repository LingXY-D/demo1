package com.example.demo1.dao;

import com.example.demo1.entity.Question;
import com.example.demo1.entity.Request;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface RequestMapper {
    Request selectBy2Id(int stageId, int userId);
    void newRequest(Request request);
    void addTotal(int id);
    void addScore(int id);
}
