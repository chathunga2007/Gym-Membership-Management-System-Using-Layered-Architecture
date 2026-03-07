package lk.ijse.gymmembershipmanagementsystem.entity;

public class OrderSupplement {
    private int orderId;
    private int supplementId;
    private int qty;
    private double price;

    public OrderSupplement() {}

    public OrderSupplement(int orderId, int supplementId, int qty, double price) {
        this.orderId = orderId;
        this.supplementId = supplementId;
        this.qty = qty;
        this.price = price;
    }

    public int getOrderId() { return orderId; }
    public void setOrderId(int orderId) { this.orderId = orderId; }
    public int getSupplementId() { return supplementId; }
    public void setSupplementId(int supplementId) { this.supplementId = supplementId; }
    public int getQty() { return qty; }
    public void setQty(int qty) { this.qty = qty; }
    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }
}