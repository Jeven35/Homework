package controller;

import dao.UserDao;
import po.User;

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
@WebServlet(value = "/login")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserDao userDao = new UserDao();
        User user = userDao.getUser();
        System.out.println(user);
//        response.setContentType("text/html");
//        PrintWriter out = response.getWriter();
//        out.println("This is a new servlet page2");
//        response.sendRedirect("pages/login.html");
        request.getRequestDispatcher("/pages/login.html").forward(request,response);
    }
}
