package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Listing;

public class ListingDAO {

    private Connection conn;

    public ListingDAO() {
        conn = DBConnection.getConnection();
    }

    // ADD listing
    public boolean addListing(Listing l) {

        String sql = "INSERT INTO LISTINGS (TITLE, DESCRIPTION, PRICE, LOCATION, IMAGE_PATH, LANDLORD_ID) VALUES (?, ?, ?, ?, ?, ?)";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, l.getTitle());
            ps.setString(2, l.getDescription());
            ps.setDouble(3, l.getPrice());
            ps.setString(4, l.getLocation());
            ps.setString(5, l.getImagePath());
            ps.setInt(6, l.getLandlordId());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    // GET ALL listings
    public List<Listing> getAllListings() {

        List<Listing> list = new ArrayList<>();

        String sql = "SELECT * FROM LISTINGS";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Listing l = new Listing();

                l.setListingId(rs.getInt("LISTING_ID"));
                l.setTitle(rs.getString("TITLE"));
                l.setDescription(rs.getString("DESCRIPTION"));
                l.setPrice(rs.getDouble("PRICE"));
                l.setLocation(rs.getString("LOCATION"));
                l.setImagePath(rs.getString("IMAGE_PATH"));
                l.setLandlordId(rs.getInt("LANDLORD_ID"));

                list.add(l);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    // SEARCH by location
    public List<Listing> searchByLocation(String location) {

        List<Listing> list = new ArrayList<>();

        String sql = "SELECT * FROM LISTINGS WHERE LOCATION LIKE ?";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, "%" + location + "%");

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Listing l = new Listing();

                l.setListingId(rs.getInt("LISTING_ID"));
                l.setTitle(rs.getString("TITLE"));
                l.setDescription(rs.getString("DESCRIPTION"));
                l.setPrice(rs.getDouble("PRICE"));
                l.setLocation(rs.getString("LOCATION"));
                l.setImagePath(rs.getString("IMAGE_PATH"));
                l.setLandlordId(rs.getInt("LANDLORD_ID"));

                list.add(l);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    // DELETE listing
    public boolean deleteListing(int id) {

        String sql = "DELETE FROM LISTINGS WHERE LISTING_ID = ?";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, id);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
}