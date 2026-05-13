package business;

import dao.LandlordDAO;
import dao.UserDAO;
import java.sql.SQLException;
import java.util.Date;
import javax.ejb.Stateless;
import model.User;
import util.PasswordUtil;
import util.SecurityUtil;

@Stateless
public class AuthSB {
    private final UserDAO userDAO = new UserDAO();
    private final LandlordDAO landlordDAO = new LandlordDAO();

    public User authenticate(String email, String password) {
        try {
            User user = userDAO.findByEmail(SecurityUtil.clean(email));
            if (user != null && PasswordUtil.verifyPassword(password, user.getPasswordHash(), user.getSalt())) {
                return user;
            }
            return null;
        } catch (SQLException ex) {
            throw new IllegalStateException("Could not authenticate user", ex);
        }
    }

    public void register(User user, String rawPassword, String businessName, String documentNumber) {
        validateUser(user, rawPassword);
        validateRoleDetails(user, businessName, documentNumber);
        try {
            if (userDAO.findByEmail(user.getEmail()) != null) {
                throw new IllegalArgumentException("An account with this email already exists.");
            }
            String salt = PasswordUtil.generateSalt();
            user.setSalt(salt);
            user.setPasswordHash(PasswordUtil.hashPassword(rawPassword, salt));
            user.setRegistrationDate(new Date());
            userDAO.create(user);

            if (user.hasRole("LANDLORD")) {
                landlordDAO.createForUser(user.getId(), businessName, documentNumber);
            }
        } catch (SQLException ex) {
            throw new IllegalStateException("Could not register user", ex);
        }
    }

    public int countUsersByRole(String role) {
        try {
            return userDAO.countByRole(role);
        } catch (SQLException ex) {
            throw new IllegalStateException("Could not count users", ex);
        }
    }

    private void validateUser(User user, String rawPassword) {
        if (user.getId() == null || String.valueOf(user.getId()).length() < 8) {
            throw new IllegalArgumentException("Enter a valid ID number.");
        }
        if (SecurityUtil.clean(user.getFullName()).length() < 2) {
            throw new IllegalArgumentException("Full name is required.");
        }
        if (!SecurityUtil.clean(user.getEmail()).matches("^[^@\\s]+@[^@\\s]+\\.[^@\\s]+$")) {
            throw new IllegalArgumentException("Enter a valid email address.");
        }
        if (!SecurityUtil.clean(user.getPhone()).matches("^[0-9+ ]{10,15}$")) {
            throw new IllegalArgumentException("Enter a valid phone number.");
        }
        if (rawPassword == null || rawPassword.length() < 8) {
            throw new IllegalArgumentException("Password must be at least 8 characters.");
        }
        String role = SecurityUtil.clean(user.getRole()).toUpperCase();
        if (!"STUDENT".equals(role) && !"LANDLORD".equals(role)) {
            throw new IllegalArgumentException("Choose a valid role.");
        }
        user.setRole(role);
        user.setEmail(SecurityUtil.clean(user.getEmail()).toLowerCase());
        user.setFullName(SecurityUtil.clean(user.getFullName()));
        user.setLastName(SecurityUtil.clean(user.getLastName()));
        user.setPhone(SecurityUtil.clean(user.getPhone()));
    }

    private void validateRoleDetails(User user, String businessName, String documentNumber) {
        if (!user.hasRole("LANDLORD")) {
            return;
        }
        if (SecurityUtil.clean(businessName).length() < 2) {
            throw new IllegalArgumentException("Business name is required for landlord accounts.");
        }
        if (SecurityUtil.clean(documentNumber).length() < 2) {
            throw new IllegalArgumentException("Verification document number is required for landlord accounts.");
        }
    }
}
