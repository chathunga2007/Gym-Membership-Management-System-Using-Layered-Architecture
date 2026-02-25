package lk.ijse.gymmembershipmanagementsystem.dao.custom;

import lk.ijse.gymmembershipmanagementsystem.dto.TimeSlotDTO;

import java.sql.SQLException;
import java.util.List;

public interface TimeSlotDAO {
    public boolean save(TimeSlotDTO slotDTO) throws SQLException;
    public boolean update(TimeSlotDTO slotDTO) throws SQLException;
    public boolean delete(String id) throws SQLException;
    public TimeSlotDTO search(String id) throws SQLException;
    public List<TimeSlotDTO> getAllTimeSlot() throws SQLException;
}
