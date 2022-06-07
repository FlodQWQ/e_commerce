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
import java.util.ArrayList;
import java.util.List;

import static edu.commerce.e_commerce.utils.ConvertUtils.StringtoList;

@WebServlet(name = "ServletCommodityUpdate", value = "/commodityupdate")
public class ServletCommodityUpdate extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int commodity_no = Integer.parseInt(request.getParameter("commodity_no"));
            List<Seller> allSellers = SellerDAO.queryAll();
            List<Seller> availableSellers;
            List<Seller> occupiedSellers = new ArrayList<>();
            Commodity commodity = CommodityDAO.queryByCommodityNo(commodity_no);
            List<Integer> nowSellers_index =commodity.getCommodity_sellers();
            while(nowSellers_index.size()>0){
                for(int i=0;i<allSellers.size();i++){
                    if(allSellers.get(i).getSeller_no() == nowSellers_index.get(0)){
                        occupiedSellers.add(allSellers.get(i));
                        allSellers.remove(i);
                        nowSellers_index.remove(0);
                    }
                }
            }
            availableSellers = allSellers;
            request.setAttribute("availableSellers",availableSellers);
            request.setAttribute("occupiedSellers",occupiedSellers);
            request.setAttribute("commodity", commodity);
            request.getRequestDispatcher("commodity_update.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            request.setCharacterEncoding("UTF-8");
            int commodity_no = Integer.parseInt(request.getParameter("commodity_no"));
            String commodity_name = request.getParameter("commodity_name");
            Float commodity_price = Float.parseFloat(request.getParameter("commodity_price"));
            List<Integer> commodity_sellers = StringtoList(request.getParameter("commodity_sellers"));
            Commodity commodity = new Commodity(0,commodity_name,commodity_price,commodity_sellers);
            CommodityDAO.update(commodity,commodity_no);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        response.sendRedirect("commodity_management");
    }
}
