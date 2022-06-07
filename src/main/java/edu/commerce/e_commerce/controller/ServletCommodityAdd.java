package edu.commerce.e_commerce.controller;

import edu.commerce.e_commerce.bean.Commodity;
import edu.commerce.e_commerce.bean.Seller;
import edu.commerce.e_commerce.dao.CommodityDAO;
import edu.commerce.e_commerce.dao.SellerDAO;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import static edu.commerce.e_commerce.utils.ConvertUtils.StringtoList;

@WebServlet(name = "ServletCommodityAdd", value = "/commodityadd")
public class ServletCommodityAdd extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<Seller> sellers = SellerDAO.queryAll();
            request.setAttribute("sellers",sellers);
            request.getRequestDispatcher("commodity_add.jsp").forward(request, response);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            request.setCharacterEncoding("UTF-8");
            String commodity_name = request.getParameter("commodity_name");
            Float commodity_price = Float.parseFloat(request.getParameter("commodity_price"));
            List<Integer> commodity_sellers = StringtoList(request.getParameter("commodity_sellers"));
            Commodity commodity = new Commodity(0, commodity_name, commodity_price,commodity_sellers);
            CommodityDAO.add(commodity);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        response.sendRedirect("commodity_management");
    }
}

