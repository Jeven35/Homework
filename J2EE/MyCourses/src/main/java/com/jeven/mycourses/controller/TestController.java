package com.jeven.mycourses.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by jeven on 2019/3/7.
 */
@Controller
public class TestController {
    @RequestMapping(value = "test")
    public String test(){
        return "/teacher/infoFrame";
    }

    @RequestMapping(value = "/username1", method = RequestMethod.GET)
    @ResponseBody
    public String currentUserName(Authentication authentication) {
        return authentication.getName();
    }


    @RequestMapping(value = "/test2", method = RequestMethod.GET)
    @ResponseBody
    public String test2(HttpServletRequest request) {
        HttpSession session = request.getSession();
        System.out.println(session);
        return "lalaal";
    }

}
