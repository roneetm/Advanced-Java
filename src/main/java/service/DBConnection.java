package service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        Connection connection;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Data_Modelling", "roneet", "123456");
            System.out.println("Connection Established");
            return connection;
        } catch (SQLException sqlException){
            sqlException.getMessage();
            return null;
        }
    }
}
