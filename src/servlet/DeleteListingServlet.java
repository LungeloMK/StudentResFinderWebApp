package servlet;

import business.ListingSB;
import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.User;
import util.SecurityUtil;

@WebServlet(name = "DeleteListingServlet", urlPatterns = {"/DeleteListingServlet.do"})
public class DeleteListingServlet extends HttpServlet {
    @EJB
    private ListingSB listingSB;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        SecurityUtil.addSecurityHeaders(response);
        if (!SecurityUtil.requireRole(request, response, "LANDLORD")) {
            return;
        }
        User user = SecurityUtil.currentUser(request);
        Long id = SecurityUtil.parseLong(request.getParameter("id"));
        listingSB.deleteListing(id, user.getId());
        response.sendRedirect("myListings.jsp?message=Listing+deleted");
    }
}
