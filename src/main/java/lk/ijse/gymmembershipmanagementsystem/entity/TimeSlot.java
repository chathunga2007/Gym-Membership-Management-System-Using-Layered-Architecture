package lk.ijse.gymmembershipmanagementsystem.entity;

import java.time.LocalDate;

public class TimeSlot {
    private int slotId;
    private LocalDate date;
    private String timeIn;
    private String timeOut;

    public TimeSlot() {
    }

    public TimeSlot(LocalDate date, String timeIn, String timeOut) {
        this.date = date;
        this.timeIn = timeIn;
        this.timeOut = timeOut;
    }

    public TimeSlot(int slotId, LocalDate date, String timeIn, String timeOut) {
        this.slotId = slotId;
        this.date = date;
        this.timeIn = timeIn;
        this.timeOut = timeOut;
    }

    public int getSlotId() {
        return slotId;
    }

    public void setSlotId(int slotId) {
        this.slotId = slotId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getTimeIn() {
        return timeIn;
    }

    public void setTimeIn(String timeIn) {
        this.timeIn = timeIn;
    }

    public String getTimeOut() {
        return timeOut;
    }

    public void setTimeOut(String timeOut) {
        this.timeOut = timeOut;
    }
}