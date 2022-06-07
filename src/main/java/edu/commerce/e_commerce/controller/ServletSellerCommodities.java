package edu.commerce.e_commerce.controller;

import edu.commerce.e_commerce.bean.Commodity;
import edu.commerce.e_commerce.bean.Seller;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static edu.commerce.e_commerce.dao.CommodityDAO.queryByCommodityNo;
import static edu.commerce.e_commerce.dao.CommodityDAO.setAllSellers;
import static edu.commerce.e_commerce.dao.CustomDAO.isCustomExist;
import static edu.commerce.e_commerce.dao.CustomDAO.queryByCustomNo;
import static edu.commerce.e_commerce.dao.SellerDAO.queryBySellerNo;

@WebServlet(name = "ServletSellerCommodities", value = "/seller_commodities")
public class ServletSellerCommodities extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Seller seller = queryBySellerNo(Integer.parseInt(request.getParameter("seller_no")));
            List<Integer> commodities = seller.getSeller_commodities();
            List<Commodity> list = new ArrayList<>();
            for(int item:commodities){
                Commodity commodity;
                if(isCustomExist(seller.getSeller_no() * 134 + item * 272)){
                    commodity = queryByCustomNo(seller.getSeller_no() * 134 + item * 272);
                }
                else{
                    commodity = queryByCommodityNo(item);
                }
                list.add(commodity);
            }
            setAllSellers();
            request.setAttribute("list",list);
            request.getRequestDispatcher("seller_commodities.jsp").forward(request, response);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}
