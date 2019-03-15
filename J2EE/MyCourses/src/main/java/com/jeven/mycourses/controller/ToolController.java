package com.jeven.mycourses.controller;

import com.alibaba.fastjson.JSONObject;
import com.jeven.mycourses.domain.User;
import com.jeven.mycourses.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by jeven on 2019/3/7.
 */
@Controller
public class ToolController {

    @Autowired
    public UserService userService;

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
}
