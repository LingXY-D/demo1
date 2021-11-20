package com.example.demo1.dao;

import com.example.demo1.entity.Question;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface QuestionMapper {
    Question selectByQuestionId(int id);
    void addQuestion(Question question);
    void deleteQuestion(int id);
    Question firstQ(int stage_id);
    Question getNext(int id, int stage_id);
}
