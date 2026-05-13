package servlet;

import business.ListingSB;
import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import util.SecurityUtil;

@WebServlet(name = "ViewListingDetailsServlet", urlPatterns = {"/ViewListingDetailsServlet.do"})
public class ViewListingDetailsServlet extends HttpServlet {
    @EJB
    private ListingSB listingSB;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        SecurityUtil.addSecurityHeaders(response);
        Long id = SecurityUtil.parseLong(request.getParameter("id"));
        request.setAttribute("listing", listingSB.findById(id));
        request.getRequestDispatcher("viewListing.jsp").forward(request, response);
    }
}
