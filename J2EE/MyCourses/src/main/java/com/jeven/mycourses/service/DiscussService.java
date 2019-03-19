package com.jeven.mycourses.service;

import com.jeven.mycourses.dao.DiscussDao;
import com.jeven.mycourses.dao.QuestionDao;
import com.jeven.mycourses.domain.Discuss;
import com.jeven.mycourses.domain.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by jeven on 2019/3/19.
 */
@Service
public class DiscussService {
    @Autowired
    private DiscussDao discussDao;

    @Autowired
    private QuestionDao questionDao;

    public List<Discuss> getDiscussByCid(int cid){
        return discussDao.getDiscussesByCid(cid);
    }

    public Discuss saveDiscuss(Discuss discuss){
        return discussDao.save(discuss);
    }

    public List<Question> getQuestionsByDid(int did){
        return questionDao.getQuestionsByDid(did);
    }

    public Discuss getDiscussByDid(int did){
        return discussDao.getOne(did);
    }
    public Question saveQuestion(Question question){
        return questionDao.save(question);
    }
}
