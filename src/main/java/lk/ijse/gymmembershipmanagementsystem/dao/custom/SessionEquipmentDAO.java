package lk.ijse.gymmembershipmanagementsystem.dao.custom;

import java.sql.SQLException;
import java.util.List;

public interface SessionEquipmentDAO {
    boolean save(int sessionID, int equipmentID) throws SQLException;

    boolean deleteBySessionID(String sessionID) throws SQLException;

    List<Integer> getEquipmentIDs(String sessionID) throws SQLException;
}
