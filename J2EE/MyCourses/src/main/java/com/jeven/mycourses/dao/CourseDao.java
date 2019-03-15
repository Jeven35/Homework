package com.jeven.mycourses.dao;

import com.jeven.mycourses.domain.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.Table;

/**
 * Created by jeven on 2019/3/15.
 */
@Repository
@Table(name = "courses")
public interface CourseDao extends JpaRepository<Course,Integer> {


}
