package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LandlordDAO {

    private Connection conn;

    public LandlordDAO() {
        conn = DBConnection.getConnection();
    }

    // Get landlord by ID
    public ResultSet getLandlordById(int landlordId) {

        String sql = "SELECT * FROM USERS WHERE USER_ID = ? AND ROLE = 'landlord'";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, landlordId);

            return ps.executeQuery();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    // Check if landlord is verified (if you have a column)
    public boolean isVerified(int landlordId) {

        String sql = "SELECT VERIFIED FROM USERS WHERE USER_ID = ? AND ROLE = 'landlord'";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, landlordId);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getBoolean("VERIFIED");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    // Get all landlords (for admin use if needed)
    public ResultSet getAllLandlords() {

        String sql = "SELECT * FROM USERS WHERE ROLE = 'landlord'";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);

            return ps.executeQuery();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}
