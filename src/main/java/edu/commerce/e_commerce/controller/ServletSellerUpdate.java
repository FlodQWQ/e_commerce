package edu.commerce.e_commerce.controller;

import edu.commerce.e_commerce.bean.Seller;
import edu.commerce.e_commerce.dao.SellerDAO;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;

import static edu.commerce.e_commerce.utils.ConvertUtils.StringtoList;
import static edu.commerce.e_commerce.utils.ConvertUtils.htmlStringtoString;

@WebServlet(name = "ServletSellerUpdate", value = "/sellerupdate")
public class ServletSellerUpdate extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int seller_no = Integer.parseInt(request.getParameter("seller_no"));
            Seller seller = SellerDAO.queryBySellerNo(seller_no);
            request.setAttribute("seller", seller);
            request.getRequestDispatcher("seller_update.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            request.setCharacterEncoding("UTF-8");
            int seller_no = Integer.parseInt(request.getParameter("seller_no"));
            String seller_name = request.getParameter("seller_name");
            String seller_commodities = htmlStringtoString(request.getParameter("seller_commodities"));
            Seller seller = new Seller(seller_no,seller_name,StringtoList(seller_commodities));
            SellerDAO.update(seller,seller_no);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        response.sendRedirect("seller_management");
    }
}
