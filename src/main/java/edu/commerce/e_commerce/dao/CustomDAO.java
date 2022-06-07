package edu.commerce.e_commerce.dao;

import edu.commerce.e_commerce.bean.Commodity;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static edu.commerce.e_commerce.utils.ConvertUtils.StringtoList;

public class CustomDAO {
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

    public static void add(Commodity commodity,int custom_no)throws ClassNotFoundException, SQLException{
        openDB();
        String sql = "insert into custom(custom_no,custom_orino ,custom_name, custom_price) value (?,?,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1,custom_no);
        preparedStatement.setInt(2,commodity.getCommodity_no());
        preparedStatement.setString(3,commodity.getCommodity_name());
        preparedStatement.setFloat(4,commodity.getCommodity_price());
        preparedStatement.execute();
        preparedStatement.close();
        closeDB();
    }

    public static void update(Commodity commodity,int custom_no)throws ClassNotFoundException, SQLException{
        openDB();
        String sql = "update custom set custom_name = ?,custom_price = ? where custom_no = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1,commodity.getCommodity_name());
        preparedStatement.setFloat(2,commodity.getCommodity_price());
        preparedStatement.setInt(3,custom_no);
        preparedStatement.execute();
        preparedStatement.close();
        closeDB();
    }

    public static boolean isCustomExist(int custom_no) throws SQLException,ClassNotFoundException{
        openDB();
        String sql = "select * from custom where custom_no =" + custom_no;
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        if(resultSet.next()){
            resultSet.close();
            statement.close();
            closeDB();
            return true;
        }

        else{
            resultSet.close();
            statement.close();
            closeDB();
            return false;
        }
    }
    public static Commodity queryByCustomNo(int custom_no) throws SQLException, ClassNotFoundException{
        openDB();
        String sql = "select * from custom where custom_no =" + custom_no;
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        Commodity commodity = null;
        if(resultSet.next()){
            int commodity_no = resultSet.getInt("custom_orino");
            String commodity_name = resultSet.getString("custom_name");
            Float commodity_price = resultSet.getFloat("custom_price");
            List<Integer> list = new ArrayList<Integer>();
            commodity = new Commodity(commodity_no,commodity_name,commodity_price,list);
        }
        resultSet.close();
        statement.close();
        closeDB();
        return commodity;
    }
}
