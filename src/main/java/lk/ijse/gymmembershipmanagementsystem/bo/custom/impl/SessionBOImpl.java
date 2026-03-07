package lk.ijse.gymmembershipmanagementsystem.bo.custom.impl;

import lk.ijse.gymmembershipmanagementsystem.bo.custom.SessionBO;
import lk.ijse.gymmembershipmanagementsystem.dao.CrudUtil;
import lk.ijse.gymmembershipmanagementsystem.dao.DAOFactory;
import lk.ijse.gymmembershipmanagementsystem.dao.custom.*;
import lk.ijse.gymmembershipmanagementsystem.db.DBConnection;
import lk.ijse.gymmembershipmanagementsystem.dto.SessionDTO;
import lk.ijse.gymmembershipmanagementsystem.entity.Session;
import lk.ijse.gymmembershipmanagementsystem.entity.SessionEquipment;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SessionBOImpl implements SessionBO {

    SessionDAO sessionDAO = (SessionDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.SESSION);
    SessionEquipmentDAO sessionEquipmentDAO = (SessionEquipmentDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.SESSION_EQUIPMENT);
    EquipmentDAO equipmentDAO = (EquipmentDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.Equipment);
    QueryDAO queryDAO = (QueryDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.QUERY);

    @Override
    public boolean saveSession(SessionDTO dto, List<Integer> equipmentIDs) throws Exception {
        Connection connection = DBConnection.getInstance().getConnection();
        try {
            connection.setAutoCommit(false);

            boolean sessionSaved = sessionDAO.save(new Session(dto.getSlotId(), dto.getTrainerId(), dto.getSessionType(), dto.getDuration()));
            if (!sessionSaved) throw new Exception("Session save failed");

            int sessionId = sessionDAO.getLastSessionId();
            if (sessionId == -1) throw new Exception("Failed to get session ID");

            for (Integer eqId : equipmentIDs) {
                boolean eqSaved = sessionEquipmentDAO.save(new SessionEquipment(sessionId, eqId));
                if (!eqSaved) throw new Exception("Session equipment save failed");

                boolean qtyUpdated = equipmentDAO.decreaseQty(eqId, 1);
                if (!qtyUpdated) throw new Exception("Equipment qty update failed");
            }

            connection.commit();
            return true;

        } catch (Exception e) {
            connection.rollback();
            throw e;
        } finally {
            connection.setAutoCommit(true);
        }
    }

    @Override
    public boolean updateSession(SessionDTO dto) throws SQLException {
        return false;
    }

    @Override
    public boolean deleteSession(String id) throws Exception {
        Connection connection = DBConnection.getInstance().getConnection();

        try {
            connection.setAutoCommit(false);

            ResultSet rs = CrudUtil.execute(
                    "SELECT equipmentsID FROM Session_Equipments WHERE sessionID = ?", id);

            List<Integer> equipmentIDs = new ArrayList<>();
            while (rs.next()) {
                equipmentIDs.add(rs.getInt("equipmentsID"));
            }

            CrudUtil.execute("DELETE FROM Session_Equipments WHERE sessionID = ?", id);

            for (Integer eqID : equipmentIDs) {
                equipmentDAO.increaseQty(eqID, 1);
            }

            boolean deleted = CrudUtil.execute("DELETE FROM Session WHERE sessionID = ?", id);

            if (deleted) {
                connection.commit();
                return true;
            } else {
                connection.rollback();
                return false;
            }

        } catch (Exception e) {
            connection.rollback();
            throw e;
        } finally {
            connection.setAutoCommit(true);
        }
    }

    @Override
    public SessionDTO searchSession(String id) throws SQLException {
        return null;
    }

    @Override
    public List<SessionDTO> getAllSessions() throws SQLException {
        return queryDAO.getAllSessions();
    }
}