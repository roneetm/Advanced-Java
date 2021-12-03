package DAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.*;

@WebServlet("/login")
public class Login extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String email = req.getParameter("email");
        String password = req.getParameter("password");

        try {
            Connection connection = DBConnection.getConnection();

            String query = "select userLoginId, password from UserLogin";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while(resultSet.next()){
                // Checking if entered email & password are correct.
                if(resultSet.getString("userLoginId").equalsIgnoreCase(email)
                        && resultSet.getString("password").equals(password)){
                    // If credentials matches database then, we will fetch user data
                    String userData = "select * from Party natural join UserLogin where userLoginId = ?";

                    PreparedStatement preparedStatement = connection.prepareStatement(userData);
                    preparedStatement.setString(1, email);
                    ResultSet resultSet1 = preparedStatement.executeQuery();
                    String firstName = "";
                    String lastName = "";
                    String city = "";
                    String zip = "";
                    String state = "";
                    String country = "";
                    String phone = "";
                    String partyId = "";
                   
                    if(resultSet1.next()){
                       firstName = resultSet1.getString("firstName");
                       lastName = resultSet1.getString("lastName");
                       city = resultSet1.getString("city");
                       zip = resultSet1.getString("zip");
                       state = resultSet1.getString("state");
                       country = resultSet1.getString("country");
                       phone = resultSet1.getString("phone");
                       partyId = resultSet1.getString("partyId");
                    }

                    //Creating HTTP Session
                    HttpSession httpSession = req.getSession(true);
                    httpSession.setAttribute("email", email);
                    httpSession.setAttribute("password", password);
                    httpSession.setAttribute("firstName", firstName);
                    httpSession.setAttribute("lastName", lastName);
                    httpSession.setAttribute("city", city);
                    httpSession.setAttribute("zip", zip);
                    httpSession.setAttribute("state", state);
                    httpSession.setAttribute("country", country);
                    httpSession.setAttribute("phone", phone);
                    httpSession.setAttribute("partyId", partyId);

                    RequestDispatcher requestDispatcher = req.getRequestDispatcher("profile.jsp");
                    requestDispatcher.forward(req, resp);
                } // if condition closed
            } // Closing while loop

                // If no records are found then we'll redirect to login page with error message
                RequestDispatcher requestDispatcher = req.getRequestDispatcher("index.jsp");
                req.setAttribute("Msg", "Bad Credentials");
                requestDispatcher.forward(req, resp);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    } // Closed doPost
}