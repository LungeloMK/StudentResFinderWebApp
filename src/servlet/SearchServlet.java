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

@WebServlet(name = "SearchServlet", urlPatterns = {"/SearchServlet.do"})
public class SearchServlet extends HttpServlet {
    @EJB
    private SearchSB searchSB;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        SecurityUtil.addSecurityHeaders(response);
        String location = request.getParameter("location");
        Double minPrice = SecurityUtil.parseDouble(request.getParameter("minPrice"));
        Double maxPrice = SecurityUtil.parseDouble(request.getParameter("maxPrice"));
        request.setAttribute("listings", searchSB.search(location, minPrice, maxPrice));
        request.setAttribute("location", location);
        request.setAttribute("minPrice", minPrice);
        request.setAttribute("maxPrice", maxPrice);
        request.getRequestDispatcher("searchResults.jsp").forward(request, response);
    }
}
