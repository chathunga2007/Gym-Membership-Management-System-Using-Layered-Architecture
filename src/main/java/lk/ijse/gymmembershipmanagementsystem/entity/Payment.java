package lk.ijse.gymmembershipmanagementsystem.entity;

import java.time.LocalDate;

public class Payment {
    private int paymentId;
    private int memberId;
    private double amount;
    private LocalDate paymentDate;

    public Payment() {}

    public Payment(int memberId, double amount, LocalDate paymentDate) {
        this.memberId = memberId;
        this.amount = amount;
        this.paymentDate = paymentDate;
    }

    public Payment(int paymentId, int memberId, double amount, LocalDate paymentDate) {
        this.paymentId = paymentId;
        this.memberId = memberId;
        this.amount = amount;
        this.paymentDate = paymentDate;
    }

    public int getPaymentId() { return paymentId; }
    public void setPaymentId(int paymentId) { this.paymentId = paymentId; }
    public int getMemberId() { return memberId; }
    public void setMemberId(int memberId) { this.memberId = memberId; }
    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }
    public LocalDate getPaymentDate() { return paymentDate; }
    public void setPaymentDate(LocalDate paymentDate) { this.paymentDate = paymentDate; }
}