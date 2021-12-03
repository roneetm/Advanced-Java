package DAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.*;

@WebServlet("/registration")
public class Registration extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {

        try {
            Connection connection = DBConnection.getConnection();

            // Checking if user is already registered.
            String email = req.getParameter("email");
            String password = req.getParameter("password");

            String checkingEmail = "select userLoginId from UserLogin";
            Statement statement1 = connection.createStatement();
            ResultSet resultSet = statement1.executeQuery(checkingEmail);

            boolean flag = false;
            while (resultSet.next()) {
                if (resultSet.getString("userLoginId").equalsIgnoreCase(email)) {
                    //If email is found then flag will be true
                    flag = true;
                    RequestDispatcher requestDispatcher = req.getRequestDispatcher("registration.jsp");
                    req.setAttribute("errMsg", "Email already exists");
                    requestDispatcher.include(req, resp);
                }
            }// If no email is found then control flow will continue to registration process

            if (flag == false) { // if Flag is False then we will continue with registration
                // Getting Max partyId from Database
                Statement statement = connection.createStatement();
                ResultSet result = statement.executeQuery("select MAX(partyId) as partyId from Party");

                // Storing max value in variable Id
                int partyId = -1;
                if (result != null) {
                    while (result.next()) {
                        partyId = (result.getInt("partyId")) + 1;
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

                // Query for inserting values into Party table
                String query = "INSERT INTO `Data_Modelling`.`Party` " +
                        "(`partyId`, `firstName`, `lastName`, `city`, `zip`, `state`, `country`, `phone`) " +
                        "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setInt(1, partyId);
                preparedStatement.setString(2, firstName);
                preparedStatement.setString(3, lastName);
                preparedStatement.setString(4, city);
                preparedStatement.setInt(5, zip);
                preparedStatement.setString(6, state);
                preparedStatement.setString(7, country);
                preparedStatement.setString(8, phone);

                // Query for inserting values into UserLogin table
                String loginQuery = "INSERT INTO `Data_Modelling`.`UserLogin` " +
                        "(`userLoginId`, `password`, `partyId`) " +
                        "VALUES (?, ?, ?)";

                PreparedStatement preparedStatement1 = connection.prepareStatement(loginQuery);
                preparedStatement1.setString(1, email);
                preparedStatement1.setString(2, password);
                preparedStatement1.setInt(3, partyId);

                // Setting values in Party Table
                preparedStatement.executeUpdate();
                preparedStatement.close();

                // Inserting values in UserLogin table
                preparedStatement1.executeUpdate();
                preparedStatement1.close(); // Closing Prepared Statement

                // Sending Email Confirmation for user registration
                //MailAuth.sendEmail();

                // Redirecting to Profile Page after successful registration
                RequestDispatcher requestDispatcher = req.getRequestDispatcher("/profile.jsp");
                HttpSession httpSession = req.getSession(true);
                httpSession.setAttribute("email", email);
                httpSession.setAttribute("password", password);
                httpSession.setAttribute("firstName", firstName);
                httpSession.setAttribute("lastName", lastName);
                httpSession.setAttribute("city", city);
                //httpSession.setAttribute("zip", zip);
                httpSession.setAttribute("state", state);
                httpSession.setAttribute("country", country);
                httpSession.setAttribute("phone", phone);
                httpSession.setAttribute("partyId", partyId);
                resp.sendRedirect("/profile.jsp");
                requestDispatcher.forward(req, resp);
            }
            connection.close(); // Closing Connection

        } catch (SQLException | ClassNotFoundException sqlException) {
            sqlException.getMessage();
        } catch (Exception e) {
            e.printStackTrace();
        }
    } // Closing doPost Method
}