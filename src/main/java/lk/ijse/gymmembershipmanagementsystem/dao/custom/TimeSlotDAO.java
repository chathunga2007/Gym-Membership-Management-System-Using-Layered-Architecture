package lk.ijse.gymmembershipmanagementsystem.dao.custom;

import javafx.collections.ObservableList;
import lk.ijse.gymmembershipmanagementsystem.dao.CrudDAO;
import lk.ijse.gymmembershipmanagementsystem.dto.TimeSlotDTO;
import lk.ijse.gymmembershipmanagementsystem.entity.TimeSlot;

import java.sql.SQLException;

public interface TimeSlotDAO extends CrudDAO<TimeSlot> {
    public ObservableList<TimeSlotDTO> loadTimeSlotIDs() throws SQLException;
}
