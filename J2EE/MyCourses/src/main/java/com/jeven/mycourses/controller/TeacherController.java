package com.jeven.mycourses.controller;

import com.jeven.mycourses.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by jeven on 2019/3/13.
 */
@Controller
@RequestMapping(value = "/teacher")
public class TeacherController {
    @Autowired
    private UserService userService;

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
}
