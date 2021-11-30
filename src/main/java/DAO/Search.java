package DAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
            while (resultSet.next()){
                if(resultSet.getString("firstName").equals(firstName)
                        || resultSet.getString("lastName").equals(lastName)){

                    searchResult.add(firstName);
                    searchResult.add(lastName);
                    PrintWriter out = resp.getWriter();
                    out.println("First Name "+firstName);
                    out.println("LastName " + lastName);
                }
                else {
                    PrintWriter out = resp.getWriter();
                    out.println("No records found");
                }
            }
            req.setAttribute("searchResult", searchResult);
            req.getRequestDispatcher("/result.jsp").forward(req, resp);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
