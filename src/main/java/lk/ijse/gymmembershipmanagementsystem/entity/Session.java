package lk.ijse.gymmembershipmanagementsystem.entity;

public class Session {
    private int sessionId;
    private int slotId;
    private int trainerId;
    private String sessionType;
    private int duration;

    public Session() {}

    public Session(int slotId, int trainerId, String sessionType, int duration) {
        this.slotId = slotId;
        this.trainerId = trainerId;
        this.sessionType = sessionType;
        this.duration = duration;
    }

    public Session(int sessionId, int slotId, int trainerId, String sessionType, int duration) {
        this.sessionId = sessionId;
        this.slotId = slotId;
        this.trainerId = trainerId;
        this.sessionType = sessionType;
        this.duration = duration;
    }

    // Getters & Setters
    public int getSessionId() { return sessionId; }
    public void setSessionId(int sessionId) { this.sessionId = sessionId; }
    public int getSlotId() { return slotId; }
    public void setSlotId(int slotId) { this.slotId = slotId; }
    public int getTrainerId() { return trainerId; }
    public void setTrainerId(int trainerId) { this.trainerId = trainerId; }
    public String getSessionType() { return sessionType; }
    public void setSessionType(String sessionType) { this.sessionType = sessionType; }
    public int getDuration() { return duration; }
    public void setDuration(int duration) { this.duration = duration; }
}