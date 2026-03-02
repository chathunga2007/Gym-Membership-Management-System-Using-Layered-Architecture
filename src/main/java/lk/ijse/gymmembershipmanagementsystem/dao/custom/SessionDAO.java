package lk.ijse.gymmembershipmanagementsystem.dao.custom;

import javafx.collections.ObservableList;
import lk.ijse.gymmembershipmanagementsystem.dto.SessionDTO;
import lk.ijse.gymmembershipmanagementsystem.dto.TimeSlotDTO;
import lk.ijse.gymmembershipmanagementsystem.dto.TrainerDTO;

import java.sql.SQLException;
import java.util.List;

public interface SessionDAO {

    boolean save(SessionDTO sessionDTO) throws SQLException;

    int getLastSessionId() throws SQLException;

    boolean update(SessionDTO sessionDTO) throws SQLException;

    boolean delete(String id) throws SQLException;

    SessionDTO search(String id) throws SQLException;

    List<SessionDTO> getAllSession() throws SQLException;

    ObservableList<TrainerDTO> loadTrainerID() throws SQLException;

    ObservableList<TimeSlotDTO> loadSlotID() throws SQLException;
}