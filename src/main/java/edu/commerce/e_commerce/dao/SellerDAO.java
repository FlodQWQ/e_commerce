package edu.commerce.e_commerce.dao;

import edu.commerce.e_commerce.bean.Commodity;
import edu.commerce.e_commerce.bean.Seller;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static edu.commerce.e_commerce.dao.CommodityDAO.queryByCommodityNo;
import static edu.commerce.e_commerce.dao.CustomDAO.isCustomExist;
import static edu.commerce.e_commerce.dao.CustomDAO.queryByCustomNo;
import static edu.commerce.e_commerce.utils.ConvertUtils.*;

public class SellerDAO {
    private static Connection connection = null;

    private static void openDB() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        // 3. 获取数据库连接
        connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/e_commerce?characterEncoding=utf-8&serverTimezone=GMT%2B8",
                "root",
                "luoqiming2002");
    }

    private static void closeDB() throws SQLException {
        connection.close();
    }

    public static List<Seller> queryAll() throws SQLException, ClassNotFoundException {
        openDB();
        String sql = "select * from seller";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        ArrayList<Seller> list = new ArrayList<>();
        while(resultSet.next()){
            int seller_no = resultSet.getInt("seller_no");
            String seller_name = resultSet.getString("seller_name");
            String seller_commodities = resultSet.getString("seller_commodities");
            List<Integer> seller_commodities_list = StringtoList(seller_commodities);
            Seller seller = new Seller(seller_no,seller_name,seller_commodities_list);
            list.add(seller);
        }
        resultSet.close();
        statement.close();
        closeDB();
        return list;
    }

    public static boolean isSellerExist(int seller_no) throws SQLException, ClassNotFoundException {
        openDB();
        String sql = "select * from seller where seller_no=" + seller_no;
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        if(resultSet.next()){
            resultSet.close();
            statement.close();
            closeDB();
            return true;
        }
        else {
            resultSet.close();
            statement.close();
            closeDB();
            return false;
        }
    }

    public static Seller queryBySellerNo(int seller_no) throws SQLException, ClassNotFoundException {
        openDB();
        String sql = "select * from seller where seller_no=" + seller_no;
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        Seller seller= null;
        if(resultSet.next()){
            String seller_name = resultSet.getString("seller_name");
            String seller_commodities = resultSet.getString("seller_commodities");
            seller = new Seller(seller_no, seller_name, StringtoList(seller_commodities));
        }
        resultSet.close();
        statement.close();
        closeDB();
        return seller;
    }

    public static void save(Seller seller) throws SQLException, ClassNotFoundException {
        openDB();
        String sql = "insert into seller (seller_name, seller_commodities) value (?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, seller.getSeller_name());
        preparedStatement.setString(2, ListtoString(seller.getSeller_commodities()));
        preparedStatement.execute();
        preparedStatement.close();
        closeDB();
    }

    public static void del(int seller_no) throws SQLException, ClassNotFoundException{
        openDB();
        String sql = "delete from seller where seller_no = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1,seller_no);
        preparedStatement.execute();
        preparedStatement.close();
        closeDB();
    }

    public static void update(Seller seller,int seller_no) throws SQLException, ClassNotFoundException{
        openDB();
        String sql = "update seller set seller_name = ?,seller_commodities = ? where seller_no = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1,seller.getSeller_name());
        if(seller.getSeller_commodities().size() == 0){
            preparedStatement.setString(2,"");
        }
        else{
            preparedStatement.setString(2,ListtoString(seller.getSeller_commodities()));
        }
        preparedStatement.setInt(3,seller_no);
        preparedStatement.execute();
        preparedStatement.close();
        closeDB();
    }

    public static List<Seller> searchByName(String name) throws SQLException, ClassNotFoundException {
        openDB();
        String sql = "select * from seller where locate('" + name + "'" + ", seller_name) > 0";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        ArrayList<Seller> list = new ArrayList<>();
        while(resultSet.next()){
            int seller_no = resultSet.getInt("seller_no");
            String seller_name = resultSet.getString("seller_name");
            String seller_commodities = resultSet.getString("seller_commodities");
            Seller seller = new Seller(seller_no,seller_name,StringtoList(seller_commodities));
            list.add(seller);
        }
        resultSet.close();
        statement.close();
        closeDB();
        return list;
    }

    public static void delCommodity(int commodity_no,int seller_no) throws SQLException, ClassNotFoundException{
        openDB();
        String tmpsql = "select seller_commodities from seller where seller_no =" + seller_no;
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(tmpsql);
        List<Integer> list = new ArrayList<>();
        if(resultSet.next()){
            String str = htmlStringtoString(resultSet.getString("seller_commodities"));
            list = StringtoList(str);
        }
        list.removeAll(Collections.singleton(commodity_no));
        String seller_commodities = ListtoString(list);
        String sql = "update seller set seller_commodities = ? where seller_no = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1,seller_commodities);
        preparedStatement.setInt(2,seller_no);
        preparedStatement.execute();
        Commodity commodity = queryByCommodityNo(commodity_no);
        List<Integer> sellers = commodity.getCommodity_sellers();
        for(int i=0;i<sellers.size();i++){
            if(sellers.get(i)==seller_no){
                sellers.remove(i);
                break;
            }
        }
        String sellers1 = ListtoString(sellers);
        String sql1 = "update commodity set commodity_sellers = ? where commodity_no = ?";
        PreparedStatement preparedStatement1 = connection.prepareStatement(sql1);
        preparedStatement1.setString(1,sellers1);
        preparedStatement1.setInt(2,commodity_no);
        preparedStatement1.execute();
        preparedStatement1.close();
        closeDB();
    }

    public static Commodity customCommodity(int commodity_no,int seller_no) throws SQLException, ClassNotFoundException{
        openDB();
        Commodity commodity = queryByCommodityNo(commodity_no);
        if(isCustomExist(seller_no * 134 + commodity_no * 272)){
            commodity = queryByCustomNo(seller_no * 134 + commodity_no * 272);
        }
        return commodity;
    }
}
