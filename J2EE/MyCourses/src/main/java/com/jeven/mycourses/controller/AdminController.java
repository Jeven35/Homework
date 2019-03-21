package com.jeven.mycourses.controller;

import com.jeven.mycourses.domain.Course;
import com.jeven.mycourses.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by jeven on 2019/3/22.
 */
@Controller
@RequestMapping(value = "/admin")
public class AdminController {

    @Autowired
    private CourseService courseService;

    @RequestMapping(value = "/toCheckFrame")
    public String toCheckFrame(){
        return "/admin/checkFrame";
    }

    @RequestMapping(value = "/toCheckPage")
    public String toCheckPage(){
        return "/admin/checkCourse";
    }

    @RequestMapping(value = "/toTongjiFrame")
    public String toTongjiFrame(){
        return "/admin/tongjiFrame";
    }

    @ResponseBody
    @RequestMapping(value = "/getAllCheckingCourse")
    public List<Course> getAllCheckingCourse(HttpServletRequest request){
        return courseService.getAllCheckingCourse();
    }

    @ResponseBody
    @RequestMapping(value = "/checkPass",method = RequestMethod.POST)
    public Map checkPass(HttpServletRequest request){
        int cid = Integer.parseInt(request.getParameter("cid"));
        Course course = courseService.getCourse(cid).get();
        course.setState(1);
        courseService.saveCourese(course);
        Map map = new HashMap();
        map.put("msg","审核通过");
        return map;
    }
}
