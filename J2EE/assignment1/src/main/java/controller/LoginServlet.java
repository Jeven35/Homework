package controller;

import dao.UserDao;
import po.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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
        HttpSession session=request.getSession(false);
        PrintWriter out = response.getWriter();
        if (session == null){
            out.print(false);
        }
        else{
            out.print(true);
        }
        out.close();
    }
}
