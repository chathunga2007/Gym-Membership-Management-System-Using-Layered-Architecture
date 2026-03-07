package lk.ijse.gymmembershipmanagementsystem.entity;

import java.time.LocalDate;

public class Membership {
    private int membershipId;
    private int memberId;
    private String membershipType;
    private LocalDate issuedDate;
    private LocalDate expiryDate;

    public Membership() {
    }

    public Membership(int membershipId, int memberId, String membershipType, LocalDate issuedDate, LocalDate expiryDate) {
        this.membershipId = membershipId;
        this.memberId = memberId;
        this.membershipType = membershipType;
        this.issuedDate = issuedDate;
        this.expiryDate = expiryDate;
    }

    public Membership(int memberId, String membershipType, LocalDate issuedDate, LocalDate expiryDate) {
        this.memberId = memberId;
        this.membershipType = membershipType;
        this.issuedDate = issuedDate;
        this.expiryDate = expiryDate;
    }

    public int getMembershipId() {
        return membershipId;
    }

    public void setMembershipId(int membershipId) {
        this.membershipId = membershipId;
    }

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public String getMembershipType() {
        return membershipType;
    }

    public void setMembershipType(String membershipType) {
        this.membershipType = membershipType;
    }

    public LocalDate getIssuedDate() {
        return issuedDate;
    }

    public void setIssuedDate(LocalDate issuedDate) {
        this.issuedDate = issuedDate;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
    }
}
