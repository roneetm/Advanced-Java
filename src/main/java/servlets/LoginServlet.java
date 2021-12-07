package servlets;

import dao.LoginDao;
import model.Login;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String email = req.getParameter("email");
        String password = req.getParameter("password");

        Login login = new Login();
        login.setEmail(email);
        login.setPassword(password);

        Boolean success = LoginDao.validateLogin(login);

        if (success) {
            /**
             * Creating new session.
             */
            HttpSession httpSession = req.getSession(true);
            httpSession.setAttribute("email", login.getEmail());
            httpSession.setAttribute("password", login.getPassword());
            // Forwarding user to profile page.
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("profile.jsp");
            requestDispatcher.forward(req, resp);
        } else {
            // If no records are found then we'll redirect to login page with error message
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("index.jsp");
            req.setAttribute("Msg", "Bad Credentials");
            requestDispatcher.forward(req, resp);
        }
    }
}
