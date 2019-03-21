package com.jeven.mycourses.service;

import com.jeven.mycourses.dao.CourseDao;
import com.jeven.mycourses.dao.StudentOfCourseDao;
import com.jeven.mycourses.domain.Course;
import com.jeven.mycourses.domain.StudentOfCourse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jeven on 2019/3/15.
 */
@Service
public class CourseService {
    @Autowired
    private CourseDao courseDao;

    @Autowired
    private StudentOfCourseDao studentOfCourseDao;

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

    public List<Course> getCoursesByTime(Date now){
        return courseDao.getCoursesByStartBeforeAndEndAfter(now,now);
    }

    public Course getCourse(int cid){
        return courseDao.getOne(cid);
    }

    // 获得学生的选课记录
    public List<StudentOfCourse> findByCourseIDAndAndEmail(int courseID,String email){
        return studentOfCourseDao.findStudentOfCoursesByCourseIDAndEmail(courseID,email);
    }

    public StudentOfCourse saveStudentOfCourse(StudentOfCourse studentOfCourse){
        return studentOfCourseDao.save(studentOfCourse);
    }

    public List<Course> getStudentCourse(String email,List<Course> courses){
        List<Course> result = new ArrayList<>();
        int size = courses.size();
        for (int i=0;i<size;i++){
            int tempID = courses.get(i).getId();
            List<StudentOfCourse> temp = studentOfCourseDao.findStudentOfCoursesByCourseIDAndEmail(tempID,email);
            if (temp == null || temp.size() == 0){
                continue;
            }
            else{
                if (temp.get(0).getState() == 1){
                    continue;
                }
                else{
                    result.add(courses.get(i));
                }
            }
        }
        return result;
    }

    public List<Course> getCourseFinished(Date now){
        return courseDao.getCoursesByEndBefore(now);
    }

    // 获得所有选这门课的学生的邮箱
    public List<String> getAllStudentByCid(int cid){
        List<StudentOfCourse> studentOfCourses = studentOfCourseDao.findStudentOfCoursesByCourseID(cid);
        int len = studentOfCourses.size();
        ArrayList<String> result = new ArrayList<>();

        for(int i=0;i<len;i++){
            result.add(studentOfCourses.get(i).getEmail());
        }
        return result;
    }

}
