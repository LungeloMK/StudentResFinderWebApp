package servlet;

import business.SearchSB;
import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import util.SecurityUtil;

@WebServlet(name = "ViewListingsServlet", urlPatterns = {"/ViewListingsServlet.do"})
public class ViewListingsServlet extends HttpServlet {
    @EJB
    private SearchSB searchSB;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        SecurityUtil.addSecurityHeaders(response);
        request.setAttribute("listings", searchSB.allActiveListings());
        request.getRequestDispatcher("searchResults.jsp").forward(request, response);
    }
}
