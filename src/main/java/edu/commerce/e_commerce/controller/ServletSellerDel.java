package edu.commerce.e_commerce.controller;

import edu.commerce.e_commerce.dao.SellerDAO;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "ServletSellerDel", value = "/sellerdel")
public class ServletSellerDel extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int seller_no = Integer.parseInt(request.getParameter("seller_no"));
        try {
            SellerDAO.del(seller_no);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        response.sendRedirect("seller_management");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
