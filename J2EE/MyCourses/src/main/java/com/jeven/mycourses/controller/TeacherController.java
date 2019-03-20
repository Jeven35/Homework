package com.jeven.mycourses.controller;

import com.jeven.mycourses.domain.Course;
import com.jeven.mycourses.domain.Discuss;
import com.jeven.mycourses.domain.File;
import com.jeven.mycourses.domain.Question;
import com.jeven.mycourses.service.CourseService;
import com.jeven.mycourses.service.DiscussService;
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

    @Autowired
    private DiscussService discussService;

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
        String uname = request.getSession().getAttribute("UserName").toString();
        course.setTEmail(email);
        course.setTeacherName(uname);
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
        file.setDdl(request.getParameter("ddl"));
        return fileService.saveFile(file);
    }


    /**
     * 返回作业信息页面
     * @return
     */
    @RequestMapping(value = "/homeworkInfo")
    public String homeworkInfo(){
        return "teacher/homework";
    }

    /**
     * 获得课程的作业信息
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "getHomeworkByCid",method = RequestMethod.POST)
    public List<File> getHomeworkByCid(HttpServletRequest request){
        int cid = Integer.parseInt(request.getParameter("courseID"));
        return fileService.getFilesByCidAndType(cid,2);
    }

    /**
     * 返回讨论页面
     * @return
     */
    @RequestMapping(value = "/discuss")
    public String discuss(){
        return "teacher/discuss";
    }


    /**
     * 获得课程的作业信息
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "getDiscuss",method = RequestMethod.POST)
    public List<Discuss> getDiscuss(HttpServletRequest request){
        int cid = Integer.parseInt(request.getParameter("CourseID"));
        return discussService.getDiscussByCid(cid);
    }


    /**
     * 新建论坛
     * @param request
     */
    @ResponseBody
    @RequestMapping(value = "saveDiscuss",method = RequestMethod.POST)
    public boolean saveDiscuss(HttpServletRequest request){
        String email = request.getSession().getAttribute("UserEmail").toString();
        String uName = userService.findUserByEmail(email).getName();
        int cid = Integer.parseInt(request.getSession().getAttribute("CourseID").toString());
        String discussDes = request.getParameter("discuss");
        Discuss discuss = new Discuss();
        discuss.setCid(cid);
        discuss.setEmail(email);
        discuss.setQName(discussDes);
        discuss.setUName(uName);
        discussService.saveDiscuss(discuss);
        return true;
    }


    /**
     * 保存问题ID
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "toQuestion",method = RequestMethod.POST)
    public boolean toQuestion(HttpServletRequest request){
        String did = request.getParameter("did");
        HttpSession session = request.getSession();
        session.setAttribute("discussID",did);
        return true;
    }

    /**
     * 返回问题页面
     * @return
     */
    @RequestMapping(value = "/toQuestion")
    public String toQuestion(){
        return "teacher/question";
    }

    /**
     * 获得问题的讨论信息
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getQuestions",method = RequestMethod.POST)
    public List<Question> getQuestions(HttpServletRequest request){
        int did = Integer.parseInt(request.getSession().getAttribute("discussID").toString());
        return discussService.getQuestionsByDid(did);
    }

    /**
     * 获得问题信息
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "getDiscussByDid",method = RequestMethod.POST)
    public String getDiscussByDid(HttpServletRequest request){
        int did = Integer.parseInt(request.getSession().getAttribute("discussID").toString());
        Discuss temp = discussService.getDiscussByDid(did);
        return temp.getUName()+": "+temp.getQName();
    }

    @ResponseBody
    @RequestMapping(value = "/saveComment",method = RequestMethod.POST)
    public boolean saveComment(HttpServletRequest request){
        Question question = new Question();
        question.setComment(request.getParameter("comment"));
        question.setEmail(request.getSession().getAttribute("UserEmail").toString());
        question.setDid(Integer.parseInt(request.getSession().getAttribute("discussID").toString()));
        question.setUName(request.getSession().getAttribute("UserName").toString());
        discussService.saveQuestion(question);
        return true;


    }

}
