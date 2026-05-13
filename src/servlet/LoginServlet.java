package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.User;
import util.SecurityUtil;

@WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlet.do"})
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        SecurityUtil.addSecurityHeaders(response);
        String username = request.getParameter("j_username");
        if (username == null) {
            username = request.getParameter("email");
        }
        String password = request.getParameter("j_password");
        if (password == null) {
            password = request.getParameter("password");
        }

        try {
            if (request.getUserPrincipal() != null) {
                request.logout();
            }
            request.login(SecurityUtil.clean(username), password);
        } catch (ServletException ex) {
            request.setAttribute("error", "Invalid realm user ID or password.");
            request.getRequestDispatcher("login.jsp").forward(request, response);
            return;
        }

        HttpSession oldSession = request.getSession(false);
        if (oldSession != null) {
            oldSession.invalidate();
        }
        HttpSession session = request.getSession(true);
        session.setMaxInactiveInterval(30 * 60);
        User user = SecurityUtil.currentUser(request);
        session.setAttribute(SecurityUtil.SESSION_USER, user);
        session.setAttribute("userRole", user.getRole());
        response.sendRedirect(dashboardFor(request, user));
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("login.jsp");
    }

    private String dashboardFor(HttpServletRequest request, User user) {
        if (request.isUserInRole("ADMIN") || user.hasRole("ADMIN")) {
            return "adminDashboard.jsp";
        }
        if (request.isUserInRole("LANDLORD") || user.hasRole("LANDLORD")) {
            return "landlordDashboard.jsp";
        }
        return "studentDashboard.jsp";
    }
}
