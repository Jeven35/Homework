package com.jeven.mycourses.dao;

import com.jeven.mycourses.domain.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.Table;
import java.sql.Date;
import java.util.List;

/**
 * Created by jeven on 2019/3/15.
 */
@Repository
@Table(name = "courses")
public interface CourseDao extends JpaRepository<Course,Integer>{
    List<Course> getCoursesByTEmailAndState(String email,int state);

    List<Course> getCoursesByStartBeforeAndEndAfter(Date now1,Date now2);

    List<Course> getCoursesByEndBefore(Date now);

    List<Course> findCoursesByState(int state);
}
