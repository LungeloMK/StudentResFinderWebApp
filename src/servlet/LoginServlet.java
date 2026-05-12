package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import za.ac.tut.entity.User;
import za.ac.tut.entity.bl.AuthSBLocal;
import za.ac.tut.entity.bl.UserFacadeLocal;

/**
 *
 * @author moses
 */
public class LoginServlet extends HttpServlet {
@EJB
    private UserFacadeLocal ufl;
@EJB
    private AuthSBLocal auth;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String email = request.getParameter("username"); 
        String password = request.getParameter("password");
        
        User user = auth.authenticate(email, password);

        String url = "login.jsp"; 

        if (user != null) {
            
            HttpSession session = request.getSession(true);
            session.setAttribute("user", user);
            url = "loginOutcome.jsp"; 
        } else {
           
            request.setAttribute("errorMassage", "Invalid email or password. Please try again.");
        }
        RequestDispatcher disp = request.getRequestDispatcher(url);
        disp.forward(request, response);
    }

 
}

