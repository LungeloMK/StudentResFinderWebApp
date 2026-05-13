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

@WebServlet(name = "AddListingServlet", urlPatterns = {"/AddListingServlet.do"})
public class AddListingServlet extends HttpServlet {
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
        try {
            Listing listing = listingFromRequest(request);
            listing.setLandlordId(user.getId());
            listingSB.addListing(listing);
            response.sendRedirect("myListings.jsp?message=Listing+created");
        } catch (RuntimeException ex) {
            request.setAttribute("error", ex.getMessage());
            request.getRequestDispatcher("addListing.jsp").forward(request, response);
        }
    }

    static Listing listingFromRequest(HttpServletRequest request) {
        Listing listing = new Listing();
        listing.setId(SecurityUtil.parseLong(request.getParameter("id")));
        listing.setTitle(request.getParameter("title"));
        listing.setDescription(request.getParameter("description"));
        listing.setAddress(request.getParameter("address"));
        listing.setLocation(request.getParameter("location"));
        listing.setPrice(SecurityUtil.parseDouble(request.getParameter("price")));
        listing.setRooms(SecurityUtil.parseInteger(request.getParameter("rooms")));
        listing.setImageUrl(request.getParameter("imageUrl"));
        String status = SecurityUtil.clean(request.getParameter("status"));
        listing.setStatus(status.isEmpty() ? Listing.STATUS_ACTIVE : status);
        return listing;
    }
}
