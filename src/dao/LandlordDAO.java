package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import model.Landlord;
import model.User;

public class LandlordDAO {
    private final DBConnection db = new DBConnection();

    public void createForUser(Long userId, String businessName, String documentNumber) throws SQLException {
        String sql = "INSERT INTO landlords (user_id, business_name, document_number, verification_status) VALUES (?, ?, ?, ?)";
        try (Connection connection = db.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, userId);
            statement.setString(2, businessName);
            statement.setString(3, documentNumber);
            statement.setString(4, Landlord.STATUS_PENDING);
            statement.executeUpdate();
        }
    }

    public Landlord findByUserId(Long userId) throws SQLException {
        String sql = "SELECT l.*, u.full_name, u.last_name, u.email, u.phone, u.role, u.registration_date, u.password_hash, u.salt "
                + "FROM landlords l JOIN users u ON l.user_id = u.id_number WHERE l.user_id = ?";
        try (Connection connection = db.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, userId);
            try (ResultSet result = statement.executeQuery()) {
                return result.next() ? map(result) : null;
            }
        }
    }

    public List<Landlord> findPending() throws SQLException {
        String sql = "SELECT l.*, u.full_name, u.last_name, u.email, u.phone, u.role, u.registration_date, u.password_hash, u.salt "
                + "FROM landlords l JOIN users u ON l.user_id = u.id_number "
                + "WHERE l.verification_status = ? ORDER BY u.registration_date ASC";
        List<Landlord> landlords = new ArrayList<>();
        try (Connection connection = db.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, Landlord.STATUS_PENDING);
            try (ResultSet result = statement.executeQuery()) {
                while (result.next()) {
                    landlords.add(map(result));
                }
            }
        }
        return landlords;
    }

    public void updateStatus(Long landlordId, String status, String reason) throws SQLException {
        String sql = "UPDATE landlords SET verification_status = ?, rejection_reason = ?, verified_at = ? WHERE id = ?";
        try (Connection connection = db.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, status);
            statement.setString(2, reason);
            if (Landlord.STATUS_VERIFIED.equals(status)) {
                statement.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
            } else {
                statement.setTimestamp(3, null);
            }
            statement.setLong(4, landlordId);
            statement.executeUpdate();
        }
    }

    public int countByStatus(String status) throws SQLException {
        String sql = "SELECT COUNT(*) FROM landlords WHERE verification_status = ?";
        try (Connection connection = db.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, status);
            try (ResultSet result = statement.executeQuery()) {
                return result.next() ? result.getInt(1) : 0;
            }
        }
    }

    private Landlord map(ResultSet result) throws SQLException {
        Landlord landlord = new Landlord();
        landlord.setId(result.getLong("id"));
        landlord.setUserId(result.getLong("user_id"));
        landlord.setBusinessName(result.getString("business_name"));
        landlord.setDocumentNumber(result.getString("document_number"));
        landlord.setVerificationStatus(result.getString("verification_status"));
        landlord.setRejectionReason(result.getString("rejection_reason"));
        Timestamp verifiedAt = result.getTimestamp("verified_at");
        if (verifiedAt != null) {
            landlord.setVerifiedAt(verifiedAt);
        }

        User user = new User();
        user.setId(result.getLong("user_id"));
        user.setFullName(result.getString("full_name"));
        user.setLastName(result.getString("last_name"));
        user.setEmail(result.getString("email"));
        user.setPhone(result.getString("phone"));
        user.setRole(result.getString("role"));
        user.setPasswordHash(result.getString("password_hash"));
        user.setSalt(result.getString("salt"));
        Timestamp registrationDate = result.getTimestamp("registration_date");
        if (registrationDate != null) {
            user.setRegistrationDate(registrationDate);
        }
        landlord.setUser(user);
        return landlord;
    }
}
