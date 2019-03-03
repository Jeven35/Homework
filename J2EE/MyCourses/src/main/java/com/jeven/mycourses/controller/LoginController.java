package com.jeven.mycourses.controller;

import com.jeven.mycourses.dao.UserDao;
import com.jeven.mycourses.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Created by jeven on 2019/2/27.
 */
@Controller
@RequestMapping("/")
public class LoginController {

    @Autowired
    public UserDao userDao;

    @RequestMapping(value = "")
    public String index(){
        return "login/login";
    }

    @RequestMapping(value = "/register",method = RequestMethod.GET)
    public String toRegister(){
        return "login/register";
    }

    @ResponseBody
    @RequestMapping(value = "/register",method = RequestMethod.POST)
    public User register(@RequestParam(value = "email") String email, @RequestParam(value = "password")String password){
        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        return userDao.save(user);
    }

//    @ResponseBody
//    @RequestMapping(value = "/register",method = RequestMethod.POST)
//    public User register(@RequestBody User user){
//        return userDao.save(user);
//    }


}
