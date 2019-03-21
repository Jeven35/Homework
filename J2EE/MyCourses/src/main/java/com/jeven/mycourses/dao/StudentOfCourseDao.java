package com.jeven.mycourses.dao;

import com.jeven.mycourses.domain.Course;
import com.jeven.mycourses.domain.StudentOfCourse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.Table;
import java.util.List;

/**
 * Created by jeven on 2019/3/20.
 */

@Repository
@Table(name = "studentofcourses")
public interface StudentOfCourseDao extends JpaRepository<StudentOfCourse,Integer> {
    List<StudentOfCourse> findStudentOfCoursesByCourseIDAndEmail(int course,String email);

    List<StudentOfCourse> findStudentOfCoursesByCourseID(int cid);

}
