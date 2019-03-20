package com.jeven.mycourses.controller;

import com.jeven.mycourses.domain.Course;
import com.jeven.mycourses.domain.StudentOfCourse;
import com.jeven.mycourses.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * Created by jeven on 2019/3/19.
 */
@Controller
@RequestMapping(value = "/student")
public class StudentController {

    @Autowired
    private CourseService courseService;

    @RequestMapping(value = "/toAllCourse")
    public String toAllCourse(){
        return "/student/allCourse";
    }

    @RequestMapping(value = "/toClassFrame")
    public String toClassFrame(){
        return "/student/classFrame";
    }
    @RequestMapping(value = "/toMyCourse")
    public String toMyCourse(){
        return "/student/myCourse";
    }

    @RequestMapping(value = "/toCourseFrame")
    public String toCourseFrame(){
        return "/student/courseFrame";
    }

    @RequestMapping(value = "/toTongJi")
    public String toTongJi(){
        return "/student/tongji";
    }

    @RequestMapping(value = "/toCourseware")
    public String toPPT(){
        return "/student/courseware";
    }

    @RequestMapping(value = "/toHomework")
    public String toHomework(){
        return "/student/homework";
    }

    /**
     * 获得当前正在进行的所有课程
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "getAllCourseNow")
    public List<Course> getAllCourseNow(){
        Date now = new Date();
        long l = now.getTime();
        java.sql.Date nowTime=new java.sql.Date(l);
        return courseService.getCoursesByTime(nowTime);
    }

    @ResponseBody
    @RequestMapping(value = "judgeJoin",method = RequestMethod.POST)
    public Map judgeJoin(HttpServletRequest request){
        int CourseID = Integer.parseInt(request.getParameter("cid"));
        Course course = courseService.getCourse(CourseID);
        Map map = new HashMap();
        if (course.getChosen()+1>course.getNumber()){
            map.put("state",false);
            map.put("msg","课程人数已满");
            return map;
        }
        String email = request.getSession().getAttribute("UserEmail").toString();
        List<StudentOfCourse> studentOfCourses = courseService.findByCourseIDAndAndEmail(CourseID,email);
        if (studentOfCourses.size() == 0 || studentOfCourses == null){
             map.put("state",true);
             map.put("msg","成功加入该课程");
             StudentOfCourse studentOfCourse = new StudentOfCourse();
             studentOfCourse.setCourseID(CourseID);
             studentOfCourse.setEmail(email);
             studentOfCourse.setState(0);
             courseService.saveStudentOfCourse(studentOfCourse);
             course.setChosen(course.getChosen()+1);
             courseService.saveCourese(course);
             return map;
        }
        else{
            StudentOfCourse temp = studentOfCourses.get(0);
            if (temp.getState() == 1){
                map.put("state",false);
                map.put("msg","你已退课，不能重复加入！");
                return map;
            }
            else{
                map.put("state",false);
                map.put("msg","你正在学习这门课程");
                return map;
            }
        }
    }


    /**
     * 获得当前正在进行的学生的课程
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "getMyCourseNow",method = RequestMethod.POST)
    public List<Course> getMyCourseNow(HttpServletRequest request){
        String email = request.getSession().getAttribute("UserEmail").toString();
        Date now = new Date();
        long l = now.getTime();
        java.sql.Date nowTime=new java.sql.Date(l);
        List<Course> courses =  courseService.getCoursesByTime(nowTime);
        return courseService.getStudentCourse(email,courses);
    }

    /**
     * 获得学生完结的课程
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "getMyCourseFinished",method = RequestMethod.POST)
    public List<Course> getMyCourseFinished(HttpServletRequest request){
        String email = request.getSession().getAttribute("UserEmail").toString();
        Date now = new Date();
        long l = now.getTime();
        java.sql.Date nowTime=new java.sql.Date(l);
        List<Course> courses =  courseService.getCourseFinished(nowTime);
        return courseService.getStudentCourse(email,courses);
    }


    /**
     * 学生退课
     */
    @ResponseBody
    @RequestMapping(value = "/drop",method = RequestMethod.POST)
    public Map drop(HttpServletRequest request){
        String email = request.getSession().getAttribute("UserEmail").toString();
        int cid = Integer.parseInt(request.getParameter("cid"));
        Course course = courseService.getCourse(cid);
        course.setChosen(course.getChosen()-1);
        courseService.saveCourese(course);
        List<StudentOfCourse> studentOfCourse = courseService.findByCourseIDAndAndEmail(cid,email);
        StudentOfCourse studentOfCourse1 = studentOfCourse.get(0);
        studentOfCourse1.setState(1);
        courseService.saveStudentOfCourse(studentOfCourse1);
        Map map = new HashMap();
        map.put("msg","退选成功！");
        return map;
    }



}
