package com.example.demo;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

class MySQLConnection {
    public static void main(String[] args) {
        String host = "jdbc:mysql://localhost:3306/carshop";
        String username = "root";
        String password = "password";
        try {
            Connection conn = DriverManager.getConnection(host, username, password);
            System.out.println("Connected to MySQL database");
//            String Query = "SELECT * FROM buyer WHERE first_name = Micheal;";
//            ResultSet rs = conn.createStatement().executeQuery(Query);
//
            String sql = "SELECT * FROM company where maker_id = 1";
            // Example SQL query
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            resultSet.next();
            int id = resultSet.getInt("maker_id");
            String name = resultSet.getString("name");
            String coo = resultSet.getString("coo");
            System.out.println("ID: " + id + ", Name: " + name + ", Coo: " + coo);

            // Process the result set
//            while (resultSet.next()) {
//                int id = resultSet.getInt("maker_id");
//                String name = resultSet.getString("name");
//                String coo = resultSet.getString("coo");
//                System.out.println("ID: " + id + ", Name: " + name + ", Coo: " + coo);
//            }

            // Close connections
            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println("Failed to connect to MySQL database");
            e.printStackTrace();
        }

    //com.mysql.cj.jdbc.ClientPreparedStatement: SELECT * FROM carshop
    }

    public void Query(String q, Connection conn) throws SQLException
    {
        Statement statement = conn.createStatement();
        ResultSet resultSet = statement.executeQuery(q);
        resultSet.next();
    }
}