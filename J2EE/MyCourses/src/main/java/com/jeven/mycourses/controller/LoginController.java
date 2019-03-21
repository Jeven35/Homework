package com.jeven.mycourses.controller;

import com.jeven.mycourses.bl.email.RegisterValidateService;
import com.jeven.mycourses.domain.User;
import com.jeven.mycourses.service.RoleService;
import com.jeven.mycourses.service.UserService;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
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
    public String register(HttpServletRequest request,
                         HttpServletResponse response){
        String action = request.getParameter("action");

        // 处理注册
        if("register".equals(action)){
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            String name = request.getParameter("name");
            service.processregister(email,password,name);
            return "请尽快点击邮箱激活链接!";
        }
        // 处理激活
        else if ("activate".equals(action)){
            System.out.println(action);
            String email = request.getParameter("email");//获取email
            String validateCode = request.getParameter("validateCode");//激活码
            try {
                service.processActivate(email,validateCode);
                return "激活完成，请登录";
            } catch (ServiceException e) {
                request.setAttribute("message" , e.getMessage());
                return e.getMessage();
            } catch (ParseException e) {
                e.printStackTrace();
                return e.getMessage();
            }
        }
        return "";
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
        else if(session.getAttribute("Role").equals("admin")){
            return "adminPage";
        }
        else{
            return "/";
        }
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpServletRequest request, HttpServletResponse response){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "/";
    }

    @ResponseBody
    @RequestMapping(value = "/zhuxiao",method = RequestMethod.GET)
    public Boolean zhuxiao(HttpServletRequest request,HttpServletResponse response){
        String email = request.getSession().getAttribute("UserEmail").toString();
        userService.deleteUser(email);
        return true;
    }


}
