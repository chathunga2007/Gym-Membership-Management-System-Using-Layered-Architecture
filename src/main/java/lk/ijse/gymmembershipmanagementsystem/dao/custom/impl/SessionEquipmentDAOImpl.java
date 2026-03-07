package lk.ijse.gymmembershipmanagementsystem.dao.custom.impl;

import lk.ijse.gymmembershipmanagementsystem.dao.CrudUtil;
import lk.ijse.gymmembershipmanagementsystem.dao.custom.SessionEquipmentDAO;
import lk.ijse.gymmembershipmanagementsystem.entity.SessionEquipment;
import java.sql.SQLException;
import java.util.List;

public class SessionEquipmentDAOImpl implements SessionEquipmentDAO {
    @Override
    public boolean save(SessionEquipment entity) throws SQLException {
        return CrudUtil.execute(
                "INSERT INTO Session_Equipments (sessionID, equipmentsID) VALUES (?,?)",
                entity.getSessionId(), entity.getEquipmentId());
    }

    @Override
    public boolean update(SessionEquipment entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public SessionEquipment search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public List<SessionEquipment> getAll() throws SQLException, ClassNotFoundException {
        return List.of();
    }

}