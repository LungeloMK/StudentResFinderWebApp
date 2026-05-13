package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import model.Listing;

public class ListingDAO {
    private final DBConnection db = new DBConnection();

    public Long create(Listing listing) throws SQLException {
        String sql = "INSERT INTO listings (landlord_id, title, description, address, location, price, rooms, image_url, status, created_at) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = db.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            setListingFields(statement, listing);
            statement.executeUpdate();
            try (ResultSet keys = statement.getGeneratedKeys()) {
                return keys.next() ? keys.getLong(1) : null;
            }
        }
    }

    public void update(Listing listing) throws SQLException {
        String sql = "UPDATE listings SET title = ?, description = ?, address = ?, location = ?, price = ?, rooms = ?, "
                + "image_url = ?, status = ? WHERE id = ? AND landlord_id = ?";
        try (Connection connection = db.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, listing.getTitle());
            statement.setString(2, listing.getDescription());
            statement.setString(3, listing.getAddress());
            statement.setString(4, listing.getLocation());
            statement.setDouble(5, listing.getPrice());
            statement.setInt(6, listing.getRooms());
            statement.setString(7, listing.getImageUrl());
            statement.setString(8, listing.getStatus());
            statement.setLong(9, listing.getId());
            statement.setLong(10, listing.getLandlordId());
            statement.executeUpdate();
        }
    }

    public void delete(Long id, Long landlordId) throws SQLException {
        String sql = "DELETE FROM listings WHERE id = ? AND landlord_id = ?";
        try (Connection connection = db.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, id);
            statement.setLong(2, landlordId);
            statement.executeUpdate();
        }
    }

    public Listing findById(Long id) throws SQLException {
        String sql = baseSelect() + " WHERE li.id = ?";
        try (Connection connection = db.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, id);
            try (ResultSet result = statement.executeQuery()) {
                return result.next() ? map(result) : null;
            }
        }
    }

    public List<Listing> findActive() throws SQLException {
        String sql = baseSelect() + " WHERE li.status = ? ORDER BY li.created_at DESC";
        try (Connection connection = db.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, Listing.STATUS_ACTIVE);
            return collect(statement);
        }
    }

    public List<Listing> findByLandlord(Long landlordId) throws SQLException {
        String sql = baseSelect() + " WHERE li.landlord_id = ? ORDER BY li.created_at DESC";
        try (Connection connection = db.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, landlordId);
            return collect(statement);
        }
    }

    public List<Listing> search(String location, Double minPrice, Double maxPrice) throws SQLException {
        StringBuilder sql = new StringBuilder(baseSelect()).append(" WHERE li.status = ?");
        List<Object> params = new ArrayList<>();
        params.add(Listing.STATUS_ACTIVE);

        if (location != null && !location.trim().isEmpty()) {
            sql.append(" AND LOWER(li.location) LIKE LOWER(?)");
            params.add("%" + location.trim() + "%");
        }
        if (minPrice != null) {
            sql.append(" AND li.price >= ?");
            params.add(minPrice);
        }
        if (maxPrice != null) {
            sql.append(" AND li.price <= ?");
            params.add(maxPrice);
        }
        sql.append(" ORDER BY li.created_at DESC");

        try (Connection connection = db.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql.toString())) {
            for (int i = 0; i < params.size(); i++) {
                statement.setObject(i + 1, params.get(i));
            }
            return collect(statement);
        }
    }

    public int countAll() throws SQLException {
        String sql = "SELECT COUNT(*) FROM listings";
        try (Connection connection = db.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql);
                ResultSet result = statement.executeQuery()) {
            return result.next() ? result.getInt(1) : 0;
        }
    }

    private void setListingFields(PreparedStatement statement, Listing listing) throws SQLException {
        statement.setLong(1, listing.getLandlordId());
        statement.setString(2, listing.getTitle());
        statement.setString(3, listing.getDescription());
        statement.setString(4, listing.getAddress());
        statement.setString(5, listing.getLocation());
        statement.setDouble(6, listing.getPrice());
        statement.setInt(7, listing.getRooms());
        statement.setString(8, listing.getImageUrl());
        statement.setString(9, listing.getStatus());
        statement.setTimestamp(10, new Timestamp(listing.getCreatedAt().getTime()));
    }

    private List<Listing> collect(PreparedStatement statement) throws SQLException {
        List<Listing> listings = new ArrayList<>();
        try (ResultSet result = statement.executeQuery()) {
            while (result.next()) {
                listings.add(map(result));
            }
        }
        return listings;
    }

    private String baseSelect() {
        return "SELECT li.*, u.full_name, u.last_name FROM listings li "
                + "JOIN landlords la ON li.landlord_id = la.user_id "
                + "JOIN users u ON la.user_id = u.id_number";
    }

    private Listing map(ResultSet result) throws SQLException {
        Listing listing = new Listing();
        listing.setId(result.getLong("id"));
        listing.setLandlordId(result.getLong("landlord_id"));
        listing.setTitle(result.getString("title"));
        listing.setDescription(result.getString("description"));
        listing.setAddress(result.getString("address"));
        listing.setLocation(result.getString("location"));
        listing.setPrice(result.getDouble("price"));
        listing.setRooms(result.getInt("rooms"));
        listing.setImageUrl(result.getString("image_url"));
        listing.setStatus(result.getString("status"));
        Timestamp createdAt = result.getTimestamp("created_at");
        if (createdAt != null) {
            listing.setCreatedAt(createdAt);
        }
        listing.setLandlordName((result.getString("full_name") + " " + result.getString("last_name")).trim());
        return listing;
    }
}
