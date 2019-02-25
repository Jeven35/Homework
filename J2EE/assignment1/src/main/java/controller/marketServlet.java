package controller;

import dao.ProductDao;
import net.sf.json.JSONArray;
import po.Good;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * Created by jeven on 2018/12/25.
 */
@WebServlet(value = "/market")
public class marketServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");

        PrintWriter out = response.getWriter();
        ProductDao productDao = new ProductDao();
        List<Good> goods = productDao.getGoos();
        JSONArray listArray=JSONArray.fromObject(goods);
        out.println(listArray);
    }
}
