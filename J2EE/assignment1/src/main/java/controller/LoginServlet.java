package controller;

import dao.UserDao;
import po.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by jeven on 2018/12/23.
 */
@WebServlet(value = "/login")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserDao userDao = new UserDao();
        String name =  request.getParameter("username");
        String password =  request.getParameter("password");
        boolean result = userDao.login(name,password);
        HttpSession session = request.getSession();
        if (result == true){
            session.setAttribute("name",name);
        }
        String sessionId = session.getId();
        if (session.isNew()){
            System.out.println("new session "+ sessionId);
        }
        System.out.println(result);
        PrintWriter out = response.getWriter();
        out.print(result);
        out.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        //  参数false表示是否产生新session
        HttpSession session=request.getSession(false);
        if (session == null){
            response.sendRedirect("/pages/login.html");//重定向
        }
        else{
             response.sendRedirect("/pages/market.html");//重定向
        }


    }
}
