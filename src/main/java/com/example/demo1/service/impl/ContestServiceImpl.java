package com.example.demo1.service.impl;

import com.example.demo1.dao.ContestMapper;
import com.example.demo1.dao.UserMapper;
import com.example.demo1.entity.Contest;
import com.example.demo1.entity.Question;
import com.example.demo1.service.ContestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("contestService")
public class ContestServiceImpl implements ContestService {
    @Autowired
    ContestMapper contestMapper;

    @Override
    public void newContest(Contest contest) {
        contestMapper.insertContest(contest);
    }

    @Override
    public void deleteContest(int guid) {
        contestMapper.deleteContest(guid);
    }

}
