package servlet;


import java.io.IOException;
import java.util.Date;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import za.ac.tut.entity.User;
import za.ac.tut.entity.bl.UserFacadeLocal;

/**
 * @author moses
 */
public class RegisterServlet extends HttpServlet {

    @EJB 
    private UserFacadeLocal ufl;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String fullName = request.getParameter("fullname");
        String email = request.getParameter("email");
        Long idNumber =Long.parseLong( request.getParameter("idnumber"));
        Long  phoneNumber =Long.parseLong( request.getParameter("phonenumber"));
        String password = request.getParameter("password");

        User user = createUser(fullName, email, idNumber, phoneNumber, password);

        ufl.create(user);

        request.setAttribute("user", user);

        RequestDispatcher disp = request.getRequestDispatcher("registerOutcome.jsp");
        disp.forward(request, response);
    }
    private User createUser(String fullName, String email, Long idNumber, Long phoneNumber, String password) {
        User u = new User();
        u.setFullname(fullName);
        u.setEmail(email);
        u.setId(idNumber);
        u.setPhone(phoneNumber);
        u.setPassword(password);
        u.setRegistrationDate(new Date()); 
        return u;
    }
}
