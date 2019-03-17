package com.jeven.mycourses.service;

import com.jeven.mycourses.dao.CourseDao;
import com.jeven.mycourses.domain.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by jeven on 2019/3/15.
 */
@Service
public class CourseService {
    @Autowired
    private CourseDao courseDao;

    public boolean saveCourese(Course course){
        Course result = courseDao.save(course);
        if (result != null){
            return true;
        }
        else{
            return false;
        }
    }

    public List<Course> getCourseOfTeacher(String email,int state){
        return courseDao.getCoursesByTEmailAndState(email,state);
    }

}
