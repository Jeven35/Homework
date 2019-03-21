package com.jeven.mycourses.controller;

import com.alibaba.fastjson.JSONObject;
import com.jeven.mycourses.bl.email.SendEmail;
import com.jeven.mycourses.bl.oss.OssService;
import com.jeven.mycourses.domain.User;
import com.jeven.mycourses.service.CourseService;
import com.jeven.mycourses.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by jeven on 2019/3/7.
 */
@Controller
public class ToolController {

    @Autowired
    private UserService userService;

    @Autowired
    private OssService ossService;

    @Autowired
    private CourseService courseService;

    @ResponseBody
    @RequestMapping(value = "getUserName",method = RequestMethod.POST)
    public Map getUserName(HttpServletRequest request){
        Map result = new HashMap();
        if (request.getSession().getAttribute("UserEmail") != null) {
            String email = request.getSession().getAttribute("UserEmail").toString();
            User user = userService.findUserByEmail(email);

            result.put("isSuccess", true);
            result.put("userName", user.getName());

        } else {
            result.put("isSuccess", false);
            result.put("userName", "未登录");
        }
        return result;
    }

    @RequestMapping(value = "nav-teacher")
    public String getNav(){
        return "tool/teacher-nav";
    }

    @RequestMapping(value = "nav-student")
    public String getsNav(){
        return "tool/student-nav";
    }

    @RequestMapping(value = "nav-admin")
    public String getaNav(){
        return "tool/admin-nav";
    }

    @RequestMapping(value = "InfoFrame")
    public String tInfoFrame(HttpServletRequest request){
        HttpSession session = request.getSession();
        if (session.getAttribute("Role").equals("teacher")){
            return "teacher/infoFrame";
        }

        else if(session.getAttribute("Role").equals("student")){
            return "student/infoFrame";
        }

        else{
            return "adminPage";
        }
    }


    /**
     * 返回用户信息
     */
    @ResponseBody
    @RequestMapping(value = "getUserInfo", method = RequestMethod.POST)
    public Map getUserInfo(HttpServletRequest request){
        Map result = new HashMap();
        if (request.getSession().getAttribute("UserEmail") != null){
            String email = request.getSession().getAttribute("UserEmail").toString();
            User user = userService.findUserByEmail(email);

            result.put("isSuccess", true);
            result.put("userName", user.getName());
            result.put("userEmail", user.getEmail());
            result.put("tel", user.getTel());
            result.put("sex",user.getSex());

        }
        else {
            result.put("isSuccess", false);
            result.put("userInfo", "false");
        }
        System.out.println(result);
        return result;
    }


    /**
     * 更新用户信息
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "updateUserInfo",method = RequestMethod.POST)
    public Boolean updateUserInfo(HttpServletRequest request){
        String email = request.getSession().getAttribute("UserEmail").toString();
        User user = userService.findUserByEmail(email);
        String name = request.getParameter("name");
        String sex = request.getParameter("sex");
        String tel = request.getParameter("tel");
        user.setName(name);
        user.setSex(sex);
        user.setTel(tel);
        userService.saveUser(user);
        return true;

    }

    @ResponseBody
    @RequestMapping("/getSTS")
    public JSONObject getSTS(HttpServletRequest request){

        return ossService.getSTS();
    }

    @ResponseBody
    @RequestMapping(value = "/getUserEmail",method = RequestMethod.POST)
    public String getUserEmail(HttpServletRequest request){
        String email = request.getSession().getAttribute("UserEmail").toString();
        return email;
    }
    @ResponseBody
    @RequestMapping(value = "/sendEmail",method = RequestMethod.POST)
    public Boolean sendEmail(HttpServletRequest request){
        String contend = request.getParameter("contend");
        int cid = Integer.parseInt(request.getSession().getAttribute("CourseID").toString());
        List<String> students = courseService.getAllStudentByCid(cid);
        int len = students.size();
        for(int i=0;i<len;i++){
            String email = students.get(i);
            SendEmail.send(email,contend,"课程通知邮件");
        }

        return false;
    }

}
