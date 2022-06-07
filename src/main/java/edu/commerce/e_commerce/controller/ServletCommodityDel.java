package edu.commerce.e_commerce.controller;

import edu.commerce.e_commerce.dao.CommodityDAO;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "ServletCommodityDel", value = "/commoditydel")
public class ServletCommodityDel extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int commodity_no = Integer.parseInt(request.getParameter("commodity_no"));
        try {
            CommodityDAO.del(commodity_no);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        response.sendRedirect("commodity_management");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}
