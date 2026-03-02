package lk.ijse.gymmembershipmanagementsystem.dao.custom.impl;

import lk.ijse.gymmembershipmanagementsystem.dao.CrudUtil;
import lk.ijse.gymmembershipmanagementsystem.dao.custom.SessionEquipmentDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SessionEquipmentDAOImpl implements SessionEquipmentDAO {
    @Override
    public boolean save(int sessionID, int equipmentID) throws SQLException {

        return CrudUtil.execute(
                "INSERT INTO Session_Equipments (sessionID, equipmentsID) VALUES (?, ?)",
                sessionID,
                equipmentID
        );
    }

    @Override
    public boolean deleteBySessionID(String sessionID) throws SQLException {

        return CrudUtil.execute(
                "DELETE FROM Session_Equipments WHERE sessionID = ?",
                sessionID
        );
    }

    @Override
    public List<Integer> getEquipmentIDs(String sessionID) throws SQLException {

        ResultSet rs = CrudUtil.execute(
                "SELECT equipmentsID FROM Session_Equipments WHERE sessionID = ?",
                sessionID
        );

        List<Integer> list = new ArrayList<>();

        while (rs.next()) {
            list.add(rs.getInt("equipmentsID"));
        }

        return list;
    }
}
