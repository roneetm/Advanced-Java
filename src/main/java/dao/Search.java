package dao;

import service.DBConnection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;

@WebServlet("/search")
public class Search extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");

        try {
            Connection connection = DBConnection.getConnection();
            String searchName = "select firstName, lastName from Party";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(searchName);

            ArrayList<String> searchResult = new ArrayList<>();

            // Checking if name entered by the user matches inside the database.
            while (resultSet.next()){
                if(resultSet.getString("firstName").equalsIgnoreCase(firstName)
                        && resultSet.getString("lastName").equalsIgnoreCase(lastName)){

                    // Fetches profile from DB based on user input
                    String profileQuery = "SELECT * FROM Data_Modelling.Party Natural Join Data_Modelling.UserLogin where firstName ="
                            +firstName+ "AND lastName =" +lastName;
                    Statement statement1 = connection.createStatement();
                    System.out.println("Profile Query Executed");
                    ResultSet result = statement1.executeQuery(profileQuery);

                    if(result.next()){
                        String email = result.getString("userLoginId");
                        String city = result.getString("city");
                        String zip = result.getString("zip");
                        String state = result.getString("state");
                        String country = result.getString("country");
                        String phone = result.getString("phone");

                        HttpSession httpSession = req.getSession();
                        httpSession.setAttribute("email", email);
                        httpSession.setAttribute("firstName", firstName);
                        httpSession.setAttribute("lastName", lastName);
                        httpSession.setAttribute("city", city);
                        httpSession.setAttribute("zip", zip);
                        httpSession.setAttribute("state", state);
                        httpSession.setAttribute("country", country);
                        httpSession.setAttribute("phone", phone);
                    }

                    RequestDispatcher requestDispatcher = req.getRequestDispatcher("profile.jsp");
                    requestDispatcher.forward(req, resp);
                } // If condition closed
            } // While Loop Closed
                PrintWriter out = resp.getWriter();
                out.println("No records found");

            req.setAttribute("searchResult", searchResult);
            req.getRequestDispatcher("/result.jsp").forward(req, resp);

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}