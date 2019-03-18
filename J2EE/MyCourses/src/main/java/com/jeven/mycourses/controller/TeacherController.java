package com.jeven.mycourses.controller;

import com.jeven.mycourses.domain.Course;
import com.jeven.mycourses.domain.File;
import com.jeven.mycourses.service.CourseService;
import com.jeven.mycourses.service.FileService;
import com.jeven.mycourses.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by jeven on 2019/3/13.
 */
@Controller
@RequestMapping(value = "/teacher")
public class TeacherController {
    @Autowired
    private UserService userService;

    @Autowired
    private CourseService courseService;

    @Autowired
    private FileService fileService;

    /**
     * 返回教师信息页面
     * @return
     */
    @RequestMapping(value = "InfoPage")
    public String InfoPage(){
        return "teacher/info";
    }


    /**
     * 返回创建课程框架
     * @return
     */
    @RequestMapping(value = "/CreateFrame")
    public String CreateFrame(){
        return "teacher/createFrame";
    }


    /**
     * 返回管理课程框架
     * @return
     */
    @RequestMapping(value = "/ManageFrame")
    public String ManageFrame(){
        return "teacher/manageFrame";
    }


    /**
     * 返回创建课程页面
     * @return
     */
    @RequestMapping(value = "/Create")
    public String Creat(){
        return "teacher/create";
    }


    /**
     * 保存课程信息
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/saveCourse",method = RequestMethod.POST)
    public Boolean saveCourse(HttpServletRequest request){
        Course course = new Course();

        String email = request.getSession().getAttribute("UserEmail").toString();
        course.setTEmail(email);
        course.setCourseName(request.getParameter("name"));
        course.setClassTime(request.getParameter("classTime"));
        course.setCourseDes(request.getParameter("des"));
        Date start,end;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            start = format.parse(request.getParameter("start"));
            end = format.parse(request.getParameter("end"));
            java.sql.Date start1 = new java.sql.Date(start.getTime());
            java.sql.Date end1 = new java.sql.Date(end.getTime());
            course.setStart(start1);
            course.setEnd(end1);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        int num = Integer.parseInt(request.getParameter("num"));
        course.setNumber(num);
        course.setState(0);
        courseService.saveCourese(course);
        return true;
    }


    /**
     * 返回管理课程页面
     * @return
     */
    @RequestMapping(value = "/Manage")
    public String Manage(){
        return "teacher/manage";
    }


    /**
     * 返回管理课程框架
     * @return
     */
    @RequestMapping(value = "/CourseFrame")
    public String CourseFrame(){
        return "/teacher/courseFrame";
    }

    /**
     * 返回课程信息页面
     * @return
     */
    @RequestMapping(value = "/courseInfo")
    public String courseInfo(){
        return "teacher/courseInfo";
    }


    /**
     * 返回教师已经审核过的课程信息
     */
    @ResponseBody
    @RequestMapping(value = "/getCoursesOfTeacher",method = RequestMethod.POST)
    public List<Course> getCoursesOfTeacher(HttpServletRequest request){
        String email = request.getSession().getAttribute("UserEmail").toString();
        List<Course> result = courseService.getCourseOfTeacher(email,1);
        return result;
    }

    /**
     * 保存查看的课程ID
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "setCourseID",method = RequestMethod.POST)
    public boolean setCourseID(HttpServletRequest request){
        String id = request.getParameter("cid");
        HttpSession session = request.getSession();
        session.setAttribute("CourseID",id);
        return true;
    }

    @ResponseBody
    @RequestMapping(value = "getCourseID",method = RequestMethod.GET)
    public String getCourseID(HttpServletRequest request){
        String id = request.getSession().getAttribute("CourseID").toString();
        return id;
    }

    /**
     * 获得课程的课件资料
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "getPPTFilesByCid",method = RequestMethod.POST)
    public List<File> getPPTFilesByCid(HttpServletRequest request){
        int cid = Integer.parseInt(request.getParameter("courseID"));
        return fileService.getFilesByCidAndType(cid,1);
    }

    /**
     * 上传文件
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "upload",method = RequestMethod.POST)
    public File upload(HttpServletRequest request){
        File file = new File();
        file.setCid(Integer.parseInt(request.getParameter("cid")));
        file.setFileName(request.getParameter("fileName"));
        file.setReName(request.getParameter("reName"));
        file.setType(Integer.parseInt(request.getParameter("type")));
        return fileService.saveFile(file);
    }
}
