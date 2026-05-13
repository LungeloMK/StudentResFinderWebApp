package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import model.User;

public class UserDAO {
    private final DBConnection db = new DBConnection();

    public void create(User user) throws SQLException {
        String sql = "INSERT INTO users (id_number, full_name, last_name, email, password_hash, salt, phone, role, registration_date) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = db.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, user.getId());
            statement.setString(2, user.getFullName());
            statement.setString(3, user.getLastName());
            statement.setString(4, user.getEmail().toLowerCase());
            statement.setString(5, user.getPasswordHash());
            statement.setString(6, user.getSalt());
            statement.setString(7, user.getPhone());
            statement.setString(8, user.getRole());
            statement.setTimestamp(9, new Timestamp(user.getRegistrationDate().getTime()));
            statement.executeUpdate();
        }
    }

    public User findByEmail(String email) throws SQLException {
        String sql = "SELECT * FROM users WHERE LOWER(email) = LOWER(?)";
        try (Connection connection = db.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, email);
            try (ResultSet result = statement.executeQuery()) {
                return result.next() ? map(result) : null;
            }
        }
    }

    public User findById(Long id) throws SQLException {
        String sql = "SELECT * FROM users WHERE id_number = ?";
        try (Connection connection = db.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, id);
            try (ResultSet result = statement.executeQuery()) {
                return result.next() ? map(result) : null;
            }
        }
    }

    public int countByRole(String role) throws SQLException {
        String sql = "SELECT COUNT(*) FROM users WHERE role = ?";
        try (Connection connection = db.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, role);
            try (ResultSet result = statement.executeQuery()) {
                return result.next() ? result.getInt(1) : 0;
            }
        }
    }

    private User map(ResultSet result) throws SQLException {
        User user = new User();
        user.setId(result.getLong("id_number"));
        user.setFullName(result.getString("full_name"));
        user.setLastName(result.getString("last_name"));
        user.setEmail(result.getString("email"));
        user.setPasswordHash(result.getString("password_hash"));
        user.setSalt(result.getString("salt"));
        user.setPhone(result.getString("phone"));
        user.setRole(result.getString("role"));
        Timestamp registrationDate = result.getTimestamp("registration_date");
        if (registrationDate != null) {
            user.setRegistrationDate(registrationDate);
        }
        return user;
    }
}
