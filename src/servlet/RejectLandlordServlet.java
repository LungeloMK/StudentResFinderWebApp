package servlet;

import business.VerificationSB;
import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import util.SecurityUtil;

@WebServlet(name = "RejectLandlordServlet", urlPatterns = {"/RejectLandlordServlet.do"})
public class RejectLandlordServlet extends HttpServlet {
    @EJB
    private VerificationSB verificationSB;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        SecurityUtil.addSecurityHeaders(response);
        if (!SecurityUtil.requireRole(request, response, "ADMIN")) {
            return;
        }
        verificationSB.reject(SecurityUtil.parseLong(request.getParameter("id")),
                SecurityUtil.clean(request.getParameter("reason")));
        response.sendRedirect("verifyLandlords.jsp?message=Landlord+rejected");
    }
}
