package com.jeven.mycourses.dao;

import com.jeven.mycourses.domain.Discuss;
import com.jeven.mycourses.domain.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.Table;
import java.util.List;

/**
 * Created by jeven on 2019/3/19.
 */
@Repository
@Table(name = "question")
public interface QuestionDao extends JpaRepository<Question,Integer> {
    List<Question> getQuestionsByDid(int did);
}
