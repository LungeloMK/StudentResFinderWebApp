package business;

import dao.LandlordDAO;
import dao.ListingDAO;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import model.Landlord;
import model.Listing;
import util.SecurityUtil;

@Stateless
public class ListingSB {
    private final ListingDAO listingDAO = new ListingDAO();
    private final LandlordDAO landlordDAO = new LandlordDAO();

    public Long addListing(Listing listing) {
        validateListing(listing);
        try {
            Landlord landlord = landlordDAO.findByUserId(listing.getLandlordId());
            if (landlord == null || !landlord.isVerified()) {
                throw new IllegalArgumentException("Only verified landlords can post listings.");
            }
            listing.setStatus(Listing.STATUS_ACTIVE);
            listing.setCreatedAt(new Date());
            return listingDAO.create(listing);
        } catch (SQLException ex) {
            throw new IllegalStateException("Could not add listing", ex);
        }
    }

    public void updateListing(Listing listing) {
        validateListing(listing);
        try {
            listingDAO.update(listing);
        } catch (SQLException ex) {
            throw new IllegalStateException("Could not update listing", ex);
        }
    }

    public void deleteListing(Long listingId, Long landlordId) {
        try {
            listingDAO.delete(listingId, landlordId);
        } catch (SQLException ex) {
            throw new IllegalStateException("Could not delete listing", ex);
        }
    }

    public Listing findById(Long id) {
        try {
            return listingDAO.findById(id);
        } catch (SQLException ex) {
            throw new IllegalStateException("Could not find listing", ex);
        }
    }

    public List<Listing> findByLandlord(Long landlordId) {
        try {
            return listingDAO.findByLandlord(landlordId);
        } catch (SQLException ex) {
            throw new IllegalStateException("Could not load listings", ex);
        }
    }

    public int countListings() {
        try {
            return listingDAO.countAll();
        } catch (SQLException ex) {
            throw new IllegalStateException("Could not count listings", ex);
        }
    }

    private void validateListing(Listing listing) {
        if (listing.getLandlordId() == null) {
            throw new IllegalArgumentException("A landlord account is required.");
        }
        if (SecurityUtil.clean(listing.getTitle()).length() < 4) {
            throw new IllegalArgumentException("Listing title is too short.");
        }
        if (SecurityUtil.clean(listing.getLocation()).length() < 2) {
            throw new IllegalArgumentException("Location is required.");
        }
        if (listing.getPrice() == null || listing.getPrice() <= 0) {
            throw new IllegalArgumentException("Price must be greater than zero.");
        }
        if (listing.getRooms() == null || listing.getRooms() < 1) {
            throw new IllegalArgumentException("Rooms must be at least one.");
        }
        listing.setTitle(SecurityUtil.clean(listing.getTitle()));
        listing.setDescription(SecurityUtil.clean(listing.getDescription()));
        listing.setAddress(SecurityUtil.clean(listing.getAddress()));
        listing.setLocation(SecurityUtil.clean(listing.getLocation()));
        listing.setImageUrl(SecurityUtil.clean(listing.getImageUrl()));
    }
}
