package lk.ijse.gymmembershipmanagementsystem.bo.custom;

import lk.ijse.gymmembershipmanagementsystem.bo.SuperBO;
import lk.ijse.gymmembershipmanagementsystem.dto.SessionDTO;
import java.sql.SQLException;
import java.util.List;

public interface SessionBO extends SuperBO {
    boolean saveSession(SessionDTO dto, List<Integer> equipmentIDs) throws Exception;
    boolean updateSession(SessionDTO dto) throws SQLException;
    boolean deleteSession(String id) throws Exception;
    SessionDTO searchSession(String id) throws SQLException;
    List<SessionDTO> getAllSessions() throws SQLException;
}