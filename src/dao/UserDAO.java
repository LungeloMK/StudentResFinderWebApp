package dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class UserDAO {

    private Connection conn;

    public UserDAO() {
        conn = dao.DBConnection.getConnection();
    }

    // Register user
    public boolean registerUser(String name, String email, String password, String role) {
        String sql = "INSERT INTO USERS (NAME, EMAIL, PASSWORD, ROLE) VALUES (?, ?, ?, ?)";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, name);
            ps.setString(2, email);
            ps.setString(3, password);
            ps.setString(4, role);

            int rowsInserted = ps.executeUpdate();

            return rowsInserted > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    // Login user
    public String login(String email, String password) {

        String sql = "SELECT ROLE FROM USERS WHERE EMAIL = ? AND PASSWORD = ?";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, email);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getString("ROLE");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    // Check if email already exists
    public boolean userExists(String email) {

        String sql = "SELECT * FROM USERS WHERE EMAIL = ?";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, email);

            ResultSet rs = ps.executeQuery();

            return rs.next();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
}
