package edu.commerce.e_commerce.controller;

import edu.commerce.e_commerce.bean.Seller;
import edu.commerce.e_commerce.bean.User;
import edu.commerce.e_commerce.dao.LoginDAO;
import edu.commerce.e_commerce.dao.SellerDAO;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static edu.commerce.e_commerce.dao.CommodityDAO.setAllSellers;
import static edu.commerce.e_commerce.dao.LoginDAO.isLoginSuccessful;

@WebServlet(name = "ServletUserAdd", value = "/useradd")
public class ServletUserAdd extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("user_add.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String status = "102";
        if(!username.equals("") && !password.equals(""))status = "100";
        if(status.equals("100")){
            User user = new User(username,password);
            try {
                status = LoginDAO.add(user);
                if(status.equals("101")){
                    response.sendRedirect("/e_commerce_war_exploded/user_add.jsp?error=" + status);
                }
                else{
                    response.sendRedirect("../e_commerce_war_exploded/");
                }
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        else{
            response.sendRedirect("/e_commerce_war_exploded/user_add.jsp?error=" + status);
        }

    }
}
