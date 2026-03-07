package lk.ijse.gymmembershipmanagementsystem.dao.custom;

import lk.ijse.gymmembershipmanagementsystem.dao.CrudDAO;
import lk.ijse.gymmembershipmanagementsystem.entity.SessionEquipment;
import java.sql.SQLException;

public interface SessionEquipmentDAO extends CrudDAO<SessionEquipment> {
    boolean save(SessionEquipment entity) throws SQLException;
}