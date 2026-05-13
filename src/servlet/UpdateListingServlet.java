package servlet;

import business.ListingSB;
import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Listing;
import model.User;
import util.SecurityUtil;

@WebServlet(name = "UpdateListingServlet", urlPatterns = {"/UpdateListingServlet.do"})
public class UpdateListingServlet extends HttpServlet {
    @EJB
    private ListingSB listingSB;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        SecurityUtil.addSecurityHeaders(response);
        if (!SecurityUtil.requireRole(request, response, "LANDLORD")) {
            return;
        }
        Long id = SecurityUtil.parseLong(request.getParameter("id"));
        request.setAttribute("listing", listingSB.findById(id));
        request.getRequestDispatcher("editListing.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        SecurityUtil.addSecurityHeaders(response);
        if (!SecurityUtil.requireRole(request, response, "LANDLORD")) {
            return;
        }
        User user = SecurityUtil.currentUser(request);
        try {
            Listing listing = AddListingServlet.listingFromRequest(request);
            listing.setLandlordId(user.getId());
            listingSB.updateListing(listing);
            response.sendRedirect("myListings.jsp?message=Listing+updated");
        } catch (RuntimeException ex) {
            request.setAttribute("error", ex.getMessage());
            request.getRequestDispatcher("editListing.jsp").forward(request, response);
        }
    }
}
