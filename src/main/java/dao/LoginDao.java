package dao;

import model.Login;
import service.DBConnection;
import javax.servlet.http.HttpServlet;
import java.sql.*;

public class LoginDao extends HttpServlet {

    public static boolean validateLogin(Login login){
        try {
            Connection connection = DBConnection.getConnection();

            String query = "select userLoginId, password from UserLogin";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while(resultSet.next()) {
                // Checking if entered email & password are correct.
                if (resultSet.getString("userLoginId").equalsIgnoreCase(login.getEmail())
                        && resultSet.getString("password").equals(login.getPassword())) {




                    return true;
                    }
                }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}