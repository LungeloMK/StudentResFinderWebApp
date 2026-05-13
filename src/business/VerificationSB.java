package business;

import dao.LandlordDAO;
import java.sql.SQLException;
import java.util.List;
import javax.ejb.Stateless;
import model.Landlord;

@Stateless
public class VerificationSB {
    private final LandlordDAO landlordDAO = new LandlordDAO();

    public Landlord findByUserId(Long userId) {
        try {
            return landlordDAO.findByUserId(userId);
        } catch (SQLException ex) {
            throw new IllegalStateException("Could not load landlord profile", ex);
        }
    }

    public List<Landlord> pendingLandlords() {
        try {
            return landlordDAO.findPending();
        } catch (SQLException ex) {
            throw new IllegalStateException("Could not load pending landlords", ex);
        }
    }

    public void verify(Long landlordId) {
        update(landlordId, Landlord.STATUS_VERIFIED, null);
    }

    public void reject(Long landlordId, String reason) {
        update(landlordId, Landlord.STATUS_REJECTED, reason);
    }

    public int countPending() {
        try {
            return landlordDAO.countByStatus(Landlord.STATUS_PENDING);
        } catch (SQLException ex) {
            throw new IllegalStateException("Could not count pending landlords", ex);
        }
    }

    public int countVerified() {
        try {
            return landlordDAO.countByStatus(Landlord.STATUS_VERIFIED);
        } catch (SQLException ex) {
            throw new IllegalStateException("Could not count verified landlords", ex);
        }
    }

    private void update(Long landlordId, String status, String reason) {
        if (landlordId == null) {
            throw new IllegalArgumentException("Landlord ID is required.");
        }
        try {
            landlordDAO.updateStatus(landlordId, status, reason);
        } catch (SQLException ex) {
            throw new IllegalStateException("Could not update landlord verification", ex);
        }
    }
}
