package edu.commerce.e_commerce.controller;

import edu.commerce.e_commerce.bean.Commodity;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.util.List;

import static edu.commerce.e_commerce.dao.CommodityDAO.searchByName;

@WebServlet(name = "ServletCommoditySearch", value = "/commoditysearch")
public class ServletCommoditySearch extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("commodity_management.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(request.getParameter("commodity_search") == null){
            response.sendRedirect("commodity_management");
        }
        else{
            List<Commodity> list = null;
            String str = request.getParameter("commodity_search");
            if (str!=null) {
                str = new String(str.getBytes("ISO8859-1"), StandardCharsets.UTF_8);
            }
            try {
                list= searchByName(str);
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }
            request.setAttribute("list",list);
            request.getRequestDispatcher("commodity_management.jsp").forward(request,response);
        }
    }
}
