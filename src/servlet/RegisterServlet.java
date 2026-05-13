package servlet;

import business.AuthSB;
import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.User;
import util.SecurityUtil;

@WebServlet(name = "RegisterServlet", urlPatterns = {"/RegisterServlet.do"})
public class RegisterServlet extends HttpServlet {
    @EJB
    private AuthSB auth;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        SecurityUtil.addSecurityHeaders(response);
        try {
            String password = request.getParameter("password");
            String confirmPassword = request.getParameter("confirmPassword");
            if (password == null || !password.equals(confirmPassword)) {
                throw new IllegalArgumentException("Passwords do not match.");
            }

            User user = new User();
            user.setId(SecurityUtil.parseLong(request.getParameter("idnumber")));
            user.setFullName(request.getParameter("fullname"));
            user.setLastName(request.getParameter("lastname"));
            user.setEmail(request.getParameter("email"));
            user.setPhone(request.getParameter("phonenumber"));
            user.setRole(request.getParameter("role"));

            auth.register(user, password, request.getParameter("businessName"), request.getParameter("documentNumber"));
            response.sendRedirect("login.jsp?message=Account+created.+Please+login.");
        } catch (RuntimeException ex) {
            request.setAttribute("error", ex.getMessage());
            request.getRequestDispatcher("register.jsp").forward(request, response);
        }
    }
}
