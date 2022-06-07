package edu.commerce.e_commerce.controller;

import edu.commerce.e_commerce.bean.Seller;
import edu.commerce.e_commerce.dao.SellerDAO;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import static edu.commerce.e_commerce.dao.CommodityDAO.setAllSellers;

@WebServlet(name = "ServletSellerList", value = "/seller_management")
public class ServletSellerList extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<Seller> list = SellerDAO.queryAll();
            setAllSellers();
            request.setAttribute("list", list);
            request.getRequestDispatcher("seller_management.jsp").forward(request, response);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}
