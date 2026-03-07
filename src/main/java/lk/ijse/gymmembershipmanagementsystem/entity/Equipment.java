package lk.ijse.gymmembershipmanagementsystem.entity;

public class Equipment {
    private int equipmenstId;
    private String name;
    private int qty;
    private String availability;

    public Equipment() {
    }

    public Equipment(int equipmenstId, String name, int qty, String availability) {
        this.equipmenstId = equipmenstId;
        this.name = name;
        this.qty = qty;
        this.availability = availability;
    }

    public Equipment(String name, int qty, String availability) {
        this.name = name;
        this.qty = qty;
        this.availability = availability;
    }

    public int getEquipmenstId() {
        return equipmenstId;
    }

    public void setEquipmenstId(int equipmenstId) {
        this.equipmenstId = equipmenstId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public String getAvailability() {
        return availability;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }
}
