package edu.commerce.e_commerce.controller;

import edu.commerce.e_commerce.bean.Commodity;
import edu.commerce.e_commerce.dao.CommodityDAO;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import static edu.commerce.e_commerce.dao.CommodityDAO.setAllSellers;

@WebServlet(name = "ServletCommodityList", value = "/commodity_management")
public class ServletCommodityList extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<Commodity> list = CommodityDAO.queryAll();
            setAllSellers();
            request.setAttribute("list", list);
            request.getRequestDispatcher("commodity_management.jsp").forward(request, response);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}
