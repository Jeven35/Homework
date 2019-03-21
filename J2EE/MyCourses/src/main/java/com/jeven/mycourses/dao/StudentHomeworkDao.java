package com.jeven.mycourses.dao;

import com.jeven.mycourses.domain.StudentHomeworkRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.Table;
import java.util.List;

/**
 * Created by jeven on 2019/3/21.
 */
@Repository
@Table(name = "student_homework")
public interface StudentHomeworkDao extends JpaRepository<StudentHomeworkRecord, Integer> {
    List<StudentHomeworkRecord> findStudentHomeworkRecordsByEmailAndCourseID(String email,int id);

    List<StudentHomeworkRecord> findStudentHomeworkRecordsByFid(int fid);
}
