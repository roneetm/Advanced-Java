package DAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet("/registration")
public class Registration extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {

        try {
            Connection connection = DBConnection.getConnection();

            // Getting Max partyId from Database
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery("select MAX(partyId) as maxId from Party");

            // Storing max value in variable Id
            int maxId = -1;
            if (result != null) {
                while (result.next()) {
                    maxId = result.getInt("maxId");
                }
            }
            statement.close(); // Closing Statement

            // Fetching values from Registration Page.
            String firstName = req.getParameter("firstName");
            String lastName = req.getParameter("lastName");
            String city = req.getParameter("city");
            int zip = Integer.parseInt(req.getParameter("zip"));
            String state = req.getParameter("state");
            String country = req.getParameter("country");
            String phone = req.getParameter("phone");

            String query = "INSERT INTO `Data_Modelling`.`Party` " +
                    "(`partyId`, `firstName`, `lastName`, `city`, `zip`, `state`, `country`, `phone`) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, maxId + 1);
            preparedStatement.setString(2, firstName);
            preparedStatement.setString(3, lastName);
            preparedStatement.setString(4, city);
            preparedStatement.setInt(5, zip);
            preparedStatement.setString(6, state);
            preparedStatement.setString(7, country);
            preparedStatement.setString(8, phone);

            String email = req.getParameter("email");
            String password = req.getParameter("password");

            String loginQuery = "INSERT INTO `Data_Modelling`.`UserLogin` " +
                    "(`userLoginId`, `password`, `partyId`) " +
                    "VALUES (?, ?, ?)";

            PreparedStatement preparedStatement1 = connection.prepareStatement(loginQuery);
            preparedStatement1.setString(1, email);
            preparedStatement1.setString(2, password);
            preparedStatement1.setInt(3, maxId + 1);

            String checkingEmail = "select userLoginId from UserLogin";
            Statement statement1 = connection.createStatement();
            ResultSet resultSet = statement1.executeQuery(checkingEmail);

            while (resultSet.next()) {
                if (resultSet.getString("userLoginId").equals(email)) {
                    RequestDispatcher requestDispatcher = req.getRequestDispatcher("registration.html");
                    requestDispatcher.include(req, resp);

                } else {
                    // Setting values in Party Table
                    //preparedStatement.executeUpdate();
                    preparedStatement.close();

                    // Inserting values in UserLogin table
                    //preparedStatement1.executeUpdate();
                    preparedStatement1.close(); // Closing Prepared Statement
                }
            }

            connection.close(); // Closing Connection

            try {
                PrintWriter out = resp.getWriter();
                out.println("Inserted Successfully");
            }
            catch (IOException ioException){
                ioException.getMessage();
            }

        } catch (SQLException | ClassNotFoundException sqlException) {
            sqlException.getMessage();
        } catch (Exception e) {
            e.printStackTrace();
        }
    } // Closing doPost Method
}