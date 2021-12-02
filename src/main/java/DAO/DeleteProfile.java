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

@WebServlet("/delete")
public class DeleteProfile extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        if(session.getAttribute("email") != null){
            String partyId = (String) session.getAttribute("partyId");

            try {
                Connection connection = DBConnection.getConnection();

                String query = "DELETE UserLogin , Party  FROM UserLogin  INNER JOIN Party  \n" +
                        "WHERE UserLogin.partyId= Party.partyId and Party.partyId =" + partyId;

                Statement statement = connection.createStatement();
                statement.executeUpdate(query);
                session.invalidate();
                resp.sendRedirect("/registration.html");

            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }
        } else
            resp.sendRedirect("/login.jsp");
    }
}
