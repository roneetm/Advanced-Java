package servlets;

import dao.RegistrationDao;
import model.Login;
import model.Party;
import service.MailAuth;

import javax.mail.MessagingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/registration")
public class Registration extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // Fetching values from Registration Page.
        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        String city = req.getParameter("city");
        int zip = Integer.parseInt(req.getParameter("zip"));
        String state = req.getParameter("state");
        String country = req.getParameter("country");
        String phone = req.getParameter("phone");
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        // Creating objects of Login & Party
        Login login = new Login();
        Party party = new Party();

        party.setFirstName(firstName);
        party.setLastName(lastName);
        party.setCity(city);
        party.setZip(zip);
        party.setState(state);
        party.setCountry(country);
        party.setPhone(phone);

        login.setEmail(email);
        login.setPassword(password);

        // Calling Save User Method to save input details
        boolean success = false;
        try {
            success = RegistrationDao.saveUser(party, login);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // If condition is true then we will send confirmation email
        if (success) {
            try {

                // Sending Email Confirmation for user registration
                MailAuth.sendEmail();
                // Redirecting to Profile Page after successful registration
                RequestDispatcher requestDispatcher = req.getRequestDispatcher("/profile.jsp");
                HttpSession httpSession = req.getSession(true);
                httpSession.setAttribute("email", email);
                httpSession.setAttribute("password", password);
                httpSession.setAttribute("firstName", party.getFirstName());
                httpSession.setAttribute("lastName", party.getLastName());
                httpSession.setAttribute("city", party.getCity());
                //httpSession.setAttribute("zip", zip);
                httpSession.setAttribute("state", party.getState());
                httpSession.setAttribute("country", party.getCountry());
                httpSession.setAttribute("phone", party.getPhone());
                resp.sendRedirect("/profile.jsp");
                requestDispatcher.forward(req, resp);
            } catch (MessagingException e) {
                e.printStackTrace();
            }
        } else {
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("registration.jsp");
            req.setAttribute("errMsg", "User already exists.");
            requestDispatcher.forward(req, resp);
        }
    }
}