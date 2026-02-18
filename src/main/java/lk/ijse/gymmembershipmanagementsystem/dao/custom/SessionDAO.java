package lk.ijse.gymmembershipmanagementsystem.dao.custom;

import javafx.collections.ObservableList;
import lk.ijse.gymmembershipmanagementsystem.dto.SessionDTO;
import lk.ijse.gymmembershipmanagementsystem.dto.TimeSlotDTO;
import lk.ijse.gymmembershipmanagementsystem.dto.TrainerDTO;
import java.sql.SQLException;
import java.util.List;

public interface SessionDAO {
    public boolean save(SessionDTO sessionDTO, List<Integer> equipmentIDs) throws SQLException;
    public boolean update(SessionDTO sessionDTO) throws SQLException;
    public boolean delete(String id) throws SQLException;
    public SessionDTO search(String id) throws SQLException;
    public ObservableList<TrainerDTO> loadTrainerID() throws SQLException;
    public ObservableList<TimeSlotDTO> loadSlotID() throws SQLException;
    public List<SessionDTO> getAllSession() throws SQLException;
}
