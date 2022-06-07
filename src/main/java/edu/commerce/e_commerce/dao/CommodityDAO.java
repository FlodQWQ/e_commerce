package edu.commerce.e_commerce.dao;

import edu.commerce.e_commerce.bean.Commodity;
import edu.commerce.e_commerce.bean.Seller;

import java.sql.*;
import java.util.*;

import static edu.commerce.e_commerce.dao.SellerDAO.queryBySellerNo;
import static edu.commerce.e_commerce.utils.ConvertUtils.ListtoString;
import static edu.commerce.e_commerce.utils.ConvertUtils.StringtoList;

public class CommodityDAO {
    private static Connection connection = null;

    private static void openDB() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/e_commerce?characterEncoding=utf-8&serverTimezone=GMT%2B8",
                "root",
                "luoqiming2002");
    }

    private static void closeDB() throws SQLException {
        connection.close();
    }

    public static List<Commodity> queryAll() throws SQLException, ClassNotFoundException {
        openDB();
        String sql = "select * from commodity";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        ArrayList<Commodity> list = new ArrayList<>();
        while(resultSet.next()){
            int commodity_no = resultSet.getInt("commodity_no");
            String commodity_name = resultSet.getString("commodity_name");
            Float commodity_price = resultSet.getFloat("commodity_price");
            List<Integer> commodity_sellers = StringtoList(resultSet.getString("commodity_sellers"));
            Commodity commodity = new Commodity(commodity_no,commodity_name,commodity_price,commodity_sellers);
            list.add(commodity);
        }
        resultSet.close();
        statement.close();
        closeDB();
        return list;
    }

    public static void add(Commodity commodity) throws SQLException, ClassNotFoundException{
        openDB();
        String sql = "insert into commodity (commodity_name, commodity_price, commodity_sellers) value (?,?,?)";
        PreparedStatement preparedStatement= connection.prepareStatement(sql);
        preparedStatement.setString(1,commodity.getCommodity_name());
        preparedStatement.setFloat(2,commodity.getCommodity_price());
        preparedStatement.setString(3,ListtoString(commodity.getCommodity_sellers()));
        preparedStatement.execute();
        preparedStatement.close();
        closeDB();
    }

    public static void del(int commodity_no) throws SQLException, ClassNotFoundException {
        openDB();
        String sql = "delete from commodity where commodity_no = ?";
        PreparedStatement prepStatement = connection.prepareStatement(sql);
        prepStatement.setInt(1,commodity_no);
        prepStatement.execute();
        prepStatement.close();
        closeDB();
    }

    public static void update(Commodity commodity,int commodity_no) throws SQLException, ClassNotFoundException {
        openDB();
        String sql = "update commodity set commodity_name = ?,commodity_price = ?, commodity_sellers = ? where commodity_no = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1,commodity.getCommodity_name());
        preparedStatement.setFloat(2,commodity.getCommodity_price());
        preparedStatement.setString(3,ListtoString(commodity.getCommodity_sellers()));
        preparedStatement.setInt(4,commodity_no);
        preparedStatement.execute();
        preparedStatement.close();
        closeDB();
    }

    public static Commodity queryByCommodityNo(int commodity_no) throws SQLException, ClassNotFoundException{
        openDB();
        String sql = "select * from commodity where commodity_no =" + commodity_no;
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        Commodity commodity = null;
        if(resultSet.next()){
            String commodity_name = resultSet.getString("commodity_name");
            Float commodity_price = resultSet.getFloat("commodity_price");
            List<Integer> commodity_sellers = StringtoList(resultSet.getString("commodity_sellers"));
            commodity = new Commodity(commodity_no,commodity_name,commodity_price,commodity_sellers);
        }
        resultSet.close();
        statement.close();
        closeDB();
        return commodity;
    }

    public static List<Commodity> searchByName(String name) throws SQLException, ClassNotFoundException {
        openDB();
        String sql = "select * from commodity where locate('" + name + "'" + ", commodity_name) > 0";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        ArrayList<Commodity> list = new ArrayList<>();
        while(resultSet.next()){
            int commodity_no = resultSet.getInt("commodity_no");
            String commodity_name = resultSet.getString("commodity_name");
            Float commodity_price = resultSet.getFloat("commodity_price");
            List<Integer> commodity_sellers = StringtoList(resultSet.getString("commodity_sellers"));
            Commodity commodity = new Commodity(commodity_no,commodity_name,commodity_price,commodity_sellers);
            list.add(commodity);
        }
        resultSet.close();
        statement.close();
        closeDB();
        return list;
    }

    public static void setAllSellers() throws SQLException, ClassNotFoundException{
        List<Commodity> list = queryAll();
        List<Seller> sellers = SellerDAO.queryAll();
        Map<Integer,List<Integer>> list_commodities = new HashMap<>();
        for(Seller seller:sellers){
            list_commodities.put(seller.getSeller_no(),new ArrayList<>());
        }
        for(Commodity item:list){
            for(int index:item.getCommodity_sellers()){
                List<Integer> tmpList;
                if(list_commodities.containsKey(index)){
                    tmpList = list_commodities.get(index);
                }
                else{
                    tmpList = new ArrayList<>();
                }
                tmpList.add(item.getCommodity_no());
                list_commodities.put(index,tmpList);
            }
        }
        System.out.println(list_commodities);
        for(Map.Entry<Integer,List<Integer>> entry: list_commodities.entrySet()){
            int seller_no = entry.getKey();
            if(!SellerDAO.isSellerExist(seller_no)){
                for(Commodity commodity:list){
                    List<Integer> commodity_sellers = commodity.getCommodity_sellers();
                    commodity_sellers.removeAll(Collections.singleton(seller_no));
                    commodity.setCommodity_sellers(commodity_sellers);
                    update(commodity,commodity.getCommodity_no());
                }
            }
            else{
                Seller seller = SellerDAO.queryBySellerNo(seller_no);
                seller.setSeller_commodities(entry.getValue());
                SellerDAO.update(seller,seller_no);
            }
        }
    }
}
