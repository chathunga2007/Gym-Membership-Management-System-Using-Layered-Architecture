package lk.ijse.gymmembershipmanagementsystem.entity;

public class Supplement {
    private int id;
    private String name;
    private int qty;
    private double UnitPrice;

    public Supplement() {}

    public Supplement(String name, int qty, double unitPrice) {
        this.name = name;
        this.qty = qty;
        this.UnitPrice = unitPrice;
    }

    public Supplement(int id, String name, int qty, double unitPrice) {
        this.id = id;
        this.name = name;
        this.qty = qty;
        this.UnitPrice = unitPrice;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public int getQty() { return qty; }
    public void setQty(int qty) { this.qty = qty; }
    public double getUnitPrice() { return UnitPrice; }
    public void setUnitPrice(double unitPrice) { this.UnitPrice = unitPrice; }
}
