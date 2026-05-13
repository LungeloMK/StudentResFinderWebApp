package util;

import dao.UserDAO;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.User;

public final class SecurityUtil {
    public static final String SESSION_USER = "currentUser";

    private SecurityUtil() {
    }

    public static void addSecurityHeaders(HttpServletResponse response) {
        response.setHeader("X-Content-Type-Options", "nosniff");
        response.setHeader("X-Frame-Options", "SAMEORIGIN");
        response.setHeader("Referrer-Policy", "strict-origin-when-cross-origin");
        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate, max-age=0");
        response.setHeader("Pragma", "no-cache");
    }

    public static User currentUser(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            Object user = session.getAttribute(SESSION_USER);
            if (user instanceof User) {
                return (User) user;
            }
        }
        if (request.getUserPrincipal() == null) {
            return null;
        }
        User realmUser = findDatabaseUser(request.getUserPrincipal().getName());
        if (realmUser == null) {
            realmUser = createRealmUser(request);
        }
        request.getSession(true).setAttribute(SESSION_USER, realmUser);
        return realmUser;
    }

    public static boolean requireLogin(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        if (currentUser(request) == null) {
            response.sendRedirect("login.jsp?message=Please+login+to+continue");
            return false;
        }
        return true;
    }

    public static boolean requireRole(HttpServletRequest request, HttpServletResponse response, String... roles)
            throws IOException {
        User user = currentUser(request);
        if (user == null) {
            response.sendRedirect("login.jsp?message=Please+login+to+continue");
            return false;
        }
        boolean allowed = Arrays.stream(roles).anyMatch(role -> user.hasRole(role) || request.isUserInRole(role));
        if (!allowed) {
            response.sendRedirect("accessDenied.jsp");
        }
        return allowed;
    }

    private static User findDatabaseUser(String username) {
        try {
            return new UserDAO().findByEmail(username);
        } catch (SQLException ex) {
            return null;
        }
    }

    private static User createRealmUser(HttpServletRequest request) {
        User user = new User();
        user.setEmail(request.getUserPrincipal().getName());
        user.setFullName(request.getUserPrincipal().getName());
        if (request.isUserInRole("ADMIN")) {
            user.setRole("ADMIN");
        } else if (request.isUserInRole("LANDLORD")) {
            user.setRole("LANDLORD");
        } else {
            user.setRole("STUDENT");
        }
        return user;
    }

    public static String clean(String value) {
        return value == null ? "" : value.trim();
    }

    public static String escapeHtml(Object value) {
        if (value == null) {
            return "";
        }
        String text = String.valueOf(value);
        return text.replace("&", "&amp;")
                .replace("<", "&lt;")
                .replace(">", "&gt;")
                .replace("\"", "&quot;")
                .replace("'", "&#x27;");
    }

    public static Long parseLong(String value) {
        try {
            return Long.valueOf(clean(value));
        } catch (NumberFormatException ex) {
            return null;
        }
    }

    public static Double parseDouble(String value) {
        try {
            return Double.valueOf(clean(value));
        } catch (NumberFormatException ex) {
            return null;
        }
    }

    public static Integer parseInteger(String value) {
        try {
            return Integer.valueOf(clean(value));
        } catch (NumberFormatException ex) {
            return null;
        }
    }
}
