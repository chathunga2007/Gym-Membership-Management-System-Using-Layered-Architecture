package lk.ijse.gymmembershipmanagementsystem.entity;

import java.util.Date;

public class Order {
    private int id;
    private int memberId;
    private Date orderDate;

    public Order() {}

    public Order(int memberId, Date orderDate) {
        this.memberId = memberId;
        this.orderDate = orderDate;
    }

    public Order(int id, int memberId, Date orderDate) {
        this.id = id;
        this.memberId = memberId;
        this.orderDate = orderDate;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getMemberId() { return memberId; }
    public void setMemberId(int memberId) { this.memberId = memberId; }
    public Date getOrderDate() { return orderDate; }
    public void setOrderDate(Date orderDate) { this.orderDate = orderDate; }
}