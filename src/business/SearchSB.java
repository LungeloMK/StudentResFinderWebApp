package business;

import dao.ListingDAO;
import java.sql.SQLException;
import java.util.List;
import javax.ejb.Stateless;
import model.Listing;

@Stateless
public class SearchSB {
    private final ListingDAO listingDAO = new ListingDAO();

    public List<Listing> allActiveListings() {
        try {
            return listingDAO.findActive();
        } catch (SQLException ex) {
            throw new IllegalStateException("Could not load listings", ex);
        }
    }

    public List<Listing> search(String location, Double minPrice, Double maxPrice) {
        try {
            return listingDAO.search(location, minPrice, maxPrice);
        } catch (SQLException ex) {
            throw new IllegalStateException("Could not search listings", ex);
        }
    }
}
