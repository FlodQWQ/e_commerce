package edu.commerce.e_commerce.dao;

import java.sql.*;

public class LoginDAO {
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

    public static String isLoginSuccessful(String username,String password) throws SQLException, ClassNotFoundException {
        openDB();
        String sql = "select * from `user` where username='"+username+"'";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        if(resultSet.next()){
            if(password.equals(resultSet.getString("password"))){
                resultSet.close();
                statement.close();
                closeDB();
                return "100";
            }
            else {
                resultSet.close();
                statement.close();
                closeDB();
                return "101";
            }
        }
        else{
            resultSet.close();
            statement.close();
            closeDB();
            return "102";
        }

    }
}
