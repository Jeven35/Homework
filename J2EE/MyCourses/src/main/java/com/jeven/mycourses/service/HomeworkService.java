package com.jeven.mycourses.service;

import com.jeven.mycourses.dao.StudentHomeworkDao;
import com.jeven.mycourses.domain.StudentHomeworkRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by jeven on 2019/3/21.
 */
@Service
public class HomeworkService {
    @Autowired
    private StudentHomeworkDao studentHomeworkDao;

    public List<StudentHomeworkRecord> getRecordsByEmailAndCourse(String email,int courseID){
        return studentHomeworkDao.findStudentHomeworkRecordsByEmailAndCourseID(email,courseID);
    }

    public StudentHomeworkRecord saveRecord(StudentHomeworkRecord studentHomeworkRecord){
        return studentHomeworkDao.save(studentHomeworkRecord);
    }

}
