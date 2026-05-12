package business;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import za.ac.tut.entity.User;

/**
 *
 * @author moses
 */
@Stateless
public class AuthSB implements AuthSBLocal {

    @EJB
    private UserFacadeLocal ufl;
    @Override
    public User authenticate(String email, String password) {
    
        User user = ufl.findByEmail(email); 

        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
       
}
}
