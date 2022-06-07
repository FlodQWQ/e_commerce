package edu.commerce.e_commerce.controller;

import edu.commerce.e_commerce.bean.Seller;
import edu.commerce.e_commerce.dao.SellerDAO;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "ServletSellerAdd", value = "/selleradd")
public class ServletSellerAdd extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("seller_add.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            request.setCharacterEncoding("UTF-8");
            String seller_name = request.getParameter("seller_name");
            List<Integer> list = new ArrayList<>();
            Seller seller = new Seller(0,seller_name,list);
            SellerDAO.save(seller);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        response.sendRedirect("seller_management");
    }
}
