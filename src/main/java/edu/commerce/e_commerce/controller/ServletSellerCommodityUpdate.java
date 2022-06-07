package edu.commerce.e_commerce.controller;

import edu.commerce.e_commerce.bean.Commodity;
import edu.commerce.e_commerce.bean.Seller;
import edu.commerce.e_commerce.dao.CustomDAO;
import edu.commerce.e_commerce.dao.SellerDAO;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static edu.commerce.e_commerce.dao.CommodityDAO.queryByCommodityNo;
import static edu.commerce.e_commerce.dao.CustomDAO.add;
import static edu.commerce.e_commerce.dao.CustomDAO.isCustomExist;
import static edu.commerce.e_commerce.utils.ConvertUtils.delPoundSign;
import static edu.commerce.e_commerce.utils.EqualUtils.isCommodityEqual;

@WebServlet(name = "ServletSellerCommodityUpdate", value = "/sellercommodityupdate")
public class ServletSellerCommodityUpdate extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int seller_no = Integer.parseInt(request.getParameter("seller_no"));
        int commodity_no = Integer.parseInt(request.getParameter("commodity_no"));
        Seller seller = null;
        Commodity commodity = null;
        try {
            seller = SellerDAO.queryBySellerNo(seller_no);
            commodity = SellerDAO.customCommodity(commodity_no,seller_no);
            System.out.println(commodity.getCommodity_price());
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        request.setAttribute("seller", seller);
        request.setAttribute("commodity",commodity);
        request.getRequestDispatcher("seller_commodities_update.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        int commodity_no = Integer.parseInt(request.getParameter("commodity_no"));
        int seller_no = Integer.parseInt(delPoundSign(request.getParameter("seller_no")));
        try{
            String commodity_name = request.getParameter("commodity_name");
            Float commodity_price = Float.parseFloat(request.getParameter("commodity_price"));
            Commodity oriCommodity = queryByCommodityNo(commodity_no);
            Commodity commodity = new Commodity(commodity_no,commodity_name,commodity_price,oriCommodity.getCommodity_sellers());
            if(!isCustomExist(seller_no * 134 + commodity_no * 272)){
                if(!isCommodityEqual(commodity,oriCommodity)){
                    add(commodity,seller_no * 134 + commodity_no * 272);
                }
            }
            else{
                CustomDAO.update(commodity,seller_no * 134 + commodity_no * 272);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        response.sendRedirect("seller_commodities?seller_no=" + seller_no);
    }
}
