package controller;

import dao.UserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by jeven on 2018/12/23.
 */
@WebServlet(value = "/register")
public class RegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserDao userDao = new UserDao();
        String name =  request.getParameter("username");
        String password =  request.getParameter("password");
        boolean result = userDao.login(name,password);
        PrintWriter out = response.getWriter();
        response.setCharacterEncoding("UTF-8");
        if (result == true){
            out.print("User has been registered!");
        }
        else{
            userDao.register(name,password);
            out.print(true);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
