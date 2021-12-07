package service;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AuthFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
       HttpServletRequest req =  (HttpServletRequest) request;
       HttpSession session = req.getSession();

       String email = (String) session.getAttribute("email");
       if(email != null) {
           // If str (email) is not null. It means user is logged in
           chain.doFilter(request, response);
       } else {
           RequestDispatcher requestDispatcher = request.getRequestDispatcher("index.jsp");
           request.setAttribute("errMsg", "Please login first");
           requestDispatcher.include(request, response);
       }
    }
}
