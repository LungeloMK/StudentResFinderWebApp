package model;

import java.io.Serializable;
import java.util.Date;

public class Landlord implements Serializable {
    public static final String STATUS_PENDING = "PENDING";
    public static final String STATUS_VERIFIED = "VERIFIED";
    public static final String STATUS_REJECTED = "REJECTED";

    private Long id;
    private Long userId;
    private String businessName;
    private String documentNumber;
    private String verificationStatus;
    private String rejectionReason;
    private Date verifiedAt;
    private User user;

    public Landlord() {
    }

    public Landlord(Long id, Long userId, String businessName, String documentNumber,
            String verificationStatus, String rejectionReason, Date verifiedAt) {
        this.id = id;
        this.userId = userId;
        this.businessName = businessName;
        this.documentNumber = documentNumber;
        this.verificationStatus = verificationStatus;
        this.rejectionReason = rejectionReason;
        this.verifiedAt = verifiedAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public String getDocumentNumber() {
        return documentNumber;
    }

    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }

    public String getVerificationStatus() {
        return verificationStatus;
    }

    public void setVerificationStatus(String verificationStatus) {
        this.verificationStatus = verificationStatus;
    }

    public String getRejectionReason() {
        return rejectionReason;
    }

    public void setRejectionReason(String rejectionReason) {
        this.rejectionReason = rejectionReason;
    }

    public Date getVerifiedAt() {
        return verifiedAt;
    }

    public void setVerifiedAt(Date verifiedAt) {
        this.verifiedAt = verifiedAt;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean isVerified() {
        return STATUS_VERIFIED.equalsIgnoreCase(verificationStatus);
    }
}
