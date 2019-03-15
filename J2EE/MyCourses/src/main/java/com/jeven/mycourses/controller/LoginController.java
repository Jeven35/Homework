package com.jeven.mycourses.controller;

import com.jeven.mycourses.bl.email.RegisterValidateService;
import com.jeven.mycourses.domain.User;
import com.jeven.mycourses.service.RoleService;
import com.jeven.mycourses.service.UserService;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.mail.internet.ParseException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by jeven on 2019/2/27.
 */
@Controller
@RequestMapping("/")
public class LoginController {

    @Resource
    private RegisterValidateService service;

    @Resource
    private RoleService roleService;

    @Autowired
    public UserService userService;

    @RequestMapping(value = "")
    public String index(){
        return "login/login";
    }

    @ResponseBody
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public void login(HttpServletRequest request){

    }

    @RequestMapping(value = "/toRegister",method = RequestMethod.GET)
    public String toRegister(){
        return "login/register";
    }

    @ResponseBody
    @RequestMapping(value = "/register",method = {RequestMethod.POST,RequestMethod.GET})
    public void register(HttpServletRequest request,
                         HttpServletResponse response){
        String action = request.getParameter("action");

        // 处理注册
        if("register".equals(action)){
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            String name = request.getParameter("name");
            service.processregister(email,password,name);
        }
        // 处理激活
        else if ("activate".equals(action)){
            System.out.println(action);
            String email = request.getParameter("email");//获取email
            String validateCode = request.getParameter("validateCode");//激活码
            try {
                service.processActivate(email,validateCode);
            } catch (ServiceException e) {
                request.setAttribute("message" , e.getMessage());
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

    }


    @RequestMapping(value = "/toCenter")
    public String toCenter(HttpServletRequest request,
                           Authentication authentication){
        HttpSession session = request.getSession();
        User user = userService.findUserByEmail(authentication.getName());
        session.setAttribute("UserName",user.getName());
        session.setAttribute("UserEmail",user.getEmail());
        session.setAttribute("Role",roleService.getRolesOfUser(user.getEmail()).get(0).getRole_name());

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


}
