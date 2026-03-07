package lk.ijse.gymmembershipmanagementsystem.bo.custom;

import javafx.collections.ObservableList;
import lk.ijse.gymmembershipmanagementsystem.bo.SuperBO;
import lk.ijse.gymmembershipmanagementsystem.dto.TimeSlotDTO;
import java.sql.SQLException;
import java.util.List;

public interface TimeSlotBO extends SuperBO {
    boolean saveTimeSlot(TimeSlotDTO dto) throws SQLException, ClassNotFoundException;
    boolean updateTimeSlot(TimeSlotDTO dto) throws SQLException;
    boolean deleteTimeSlot(String id) throws SQLException, ClassNotFoundException;
    TimeSlotDTO searchTimeSlot(String id) throws SQLException, ClassNotFoundException;
    List<TimeSlotDTO> getAllTimeSlots() throws SQLException, ClassNotFoundException;
    public ObservableList<TimeSlotDTO> loadTimeSlotIDs() throws SQLException;
}