package edu.commerce.e_commerce.controller;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;

import static edu.commerce.e_commerce.dao.CommodityDAO.setAllSellers;
import static edu.commerce.e_commerce.dao.LoginDAO.isLoginSuccessful;

@WebServlet(name = "ServletLogin", value = "/login")
public class ServletLogin extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String status = "102";
        try {
            status = isLoginSuccessful(username, password);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        if(status == "100"){
            request.getRequestDispatcher("select.jsp").forward(request,response);
        }
        else{
            try {
                setAllSellers();
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }
            response.sendRedirect("/e_commerce_war_exploded/login.jsp?error=" + status);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
