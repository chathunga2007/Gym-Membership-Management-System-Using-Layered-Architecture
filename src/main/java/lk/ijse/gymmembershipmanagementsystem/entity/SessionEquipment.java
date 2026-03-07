package lk.ijse.gymmembershipmanagementsystem.entity;

public class SessionEquipment {
    private int sessionId;
    private int equipmentId;

    public SessionEquipment(int sessionId, int equipmentId) {
        this.sessionId = sessionId;
        this.equipmentId = equipmentId;
    }

    public int getSessionId() { return sessionId; }
    public int getEquipmentId() { return equipmentId; }
}