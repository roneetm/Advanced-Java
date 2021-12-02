package DAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@WebServlet("/editprofile")
public class EditProfile extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        System.out.println(req.getParameter("firstName"));

        try {
            Connection connection = DBConnection.getConnection();

            String partyId = (String) session.getAttribute("partyId");

            String updateQuery = "UPDATE Party SET firstName = ?, lastName = ?, city = ?, zip = ?, state = ?, " +
            "country = ?, phone = ? WHERE partyId =" + partyId;

            PreparedStatement statement = connection.prepareStatement(updateQuery);
            statement.setString(1, req.getParameter("firstName"));
            statement.setString(2, req.getParameter("lastName"));
            statement.setString(3, req.getParameter("city"));
            statement.setInt(4, Integer.parseInt(req.getParameter("zip")));
            statement.setString(5, req.getParameter("state"));
            statement.setString(6, req.getParameter("country"));
            statement.setString(7, req.getParameter("phone"));
            statement.executeUpdate();

            RequestDispatcher requestDispatcher = req.getRequestDispatcher("/profile.jsp");
            requestDispatcher.include(req, resp);
            connection.close();

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}
