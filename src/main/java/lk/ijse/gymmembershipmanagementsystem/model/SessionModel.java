package lk.ijse.gymmembershipmanagementsystem.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.gymmembershipmanagementsystem.dao.custom.EquipmentDAO;
import lk.ijse.gymmembershipmanagementsystem.dao.custom.SessionDAO;
import lk.ijse.gymmembershipmanagementsystem.dao.custom.SessionEquipmentDAO;
import lk.ijse.gymmembershipmanagementsystem.dao.custom.TimeSlotDAO;
import lk.ijse.gymmembershipmanagementsystem.dao.custom.impl.EquipmentDAOImpl;
import lk.ijse.gymmembershipmanagementsystem.dao.custom.impl.SessionDAOImpl;
import lk.ijse.gymmembershipmanagementsystem.dao.custom.impl.SessionEquipmentDAOImpl;
import lk.ijse.gymmembershipmanagementsystem.dao.custom.impl.TimeSlotDAOImpl;
import lk.ijse.gymmembershipmanagementsystem.db.DBConnection;
import lk.ijse.gymmembershipmanagementsystem.dto.*;
import lk.ijse.gymmembershipmanagementsystem.dao.CrudUtil;

public class SessionModel {

    SessionDAO  sessionDAO = new SessionDAOImpl();
    EquipmentDAO equipmentDAO = new EquipmentDAOImpl();
    SessionEquipmentDAO  sessionEquipmentDAO = new SessionEquipmentDAOImpl();

    public boolean save(SessionDTO sessionDTO, List<Integer> equipmentIDs) throws SQLException {

        Connection con = DBConnection.getInstance().getConnection();
        con.setAutoCommit(false);

        try {
            boolean sessionSaved = sessionDAO.save(sessionDTO);

            if (!sessionSaved) {
                con.rollback();
                return false;
            }

            int sessionID = sessionDAO.getLastSessionId();

            if (equipmentIDs != null && !equipmentIDs.isEmpty()) {
                for (Integer eqID : equipmentIDs) {

                    int qty = equipmentDAO.getQty(eqID);

                    if (qty <= 0) {
                        con.rollback();
                        throw new RuntimeException("Equipment out of stock!");
                    }

                    boolean eqSaved = sessionEquipmentDAO.save(sessionID, eqID);

                    if (!eqSaved) {
                        con.rollback();
                        return false;
                    }

                    boolean qtyUpdated = equipmentDAO.reduceQty(eqID);

                    if (!qtyUpdated) {
                        con.rollback();
                        return false;
                    }
                }
            }

            con.commit();
            return true;

        } catch (Exception e) {
            con.rollback();
            throw e;
        } finally {
            con.setAutoCommit(true);
        }
    }


//    public boolean update(SessionDTO sessionDTO) throws SQLException {
//
//        boolean result = CrudUtil.execute("UPDATE Session SET sessionType = ?, duration = ?, trainerID = ?, slotID =? WHERE sessionID = ?",
//                sessionDTO.getSessionType(),
//                sessionDTO.getDuration(),
//                sessionDTO.getTrainerId(),
//                sessionDTO.getSlotId(),
//                sessionDTO.getSessionId()
//        );
//
//        return result;
//    }

    public boolean delete(String id) throws SQLException {

        Connection con = DBConnection.getInstance().getConnection();
        con.setAutoCommit(false);

        try {
            List<Integer> equipmentIDs = sessionEquipmentDAO.getEquipmentIDs(id);

            sessionEquipmentDAO.deleteBySessionID(id);

            for (Integer eqID : equipmentIDs) {
                equipmentDAO.increaseQty(eqID);
            }

            boolean result = sessionDAO.delete(id);

            if (!result) {
                con.rollback();
                return false;
            }

            con.commit();
            return true;

        } catch (Exception e) {
            con.rollback();
            throw e;
        } finally {
            con.setAutoCommit(true);
        }
    }


//    public SessionDTO search(String id) throws SQLException {
//
//        ResultSet  result = CrudUtil.execute("SELECT * FROM Session WHERE sessionID = ?", id);
//
//        if (result.next()) {
//            int sessionID = result.getInt("sessionID");
//            String sessionType = result.getString("sessionType");
//            int duration = result.getInt("duration");
//            int trainerID = result.getInt("trainerID");
//            int slotID = result.getInt("slotID");
//            return new SessionDTO(sessionID, slotID, trainerID, sessionType, duration);
//        }
//        return null;
//    }
    
//    public ObservableList<TrainerDTO> loadTrainerID()throws SQLException{
//        ObservableList<TrainerDTO> trainerList = FXCollections.observableArrayList();
//
//        ResultSet rs = CrudUtil.execute("SELECT trainerID, name FROM Trainer");
//
//        while (rs.next()) {
//            trainerList.add(new TrainerDTO(
//                    rs.getInt("trainerID"),
//                    rs.getString("name")
//            ));
//        }
//
//        return trainerList;
//    }
//
//    public ObservableList<TimeSlotDTO> loadSlotID()throws SQLException{
//
//        ObservableList<TimeSlotDTO> timeSlotList = FXCollections.observableArrayList();
//
//        ResultSet rs = CrudUtil.execute("SELECT slotID, date FROM Time_Slot");
//
//        while (rs.next()) {
//            timeSlotList.add(new TimeSlotDTO(
//                    rs.getInt("slotID"),
//                    rs.getDate("date").toLocalDate()
//            ));
//        }
//
//        return timeSlotList;
//    }
//
//    public List<SessionDTO> getAllSession() throws SQLException {
//
//        List<SessionDTO> sessionList = new ArrayList();
//
//        ResultSet  result = CrudUtil.execute("SELECT \n" +
//                "            s.sessionID,\n" +
//                "            s.sessionType,\n" +
//                "            s.duration,\n" +
//                "            t.trainerID,\n" +
//                "            t.name AS trainerName,\n" +
//                "            ts.slotID,\n" +
//                "            ts.date\n" +
//                "        FROM Session s\n" +
//                "        JOIN Trainer t ON s.trainerID = t.trainerID\n" +
//                "        JOIN Time_Slot ts ON s.slotID = ts.slotID\n" +
//                "        ORDER BY s.sessionID DESC");
//
//        while(result.next()) {
//            int sessionID = result.getInt("sessionID");
//            int  slotID = result.getInt("slotID");
//            int trainerID = result.getInt("trainerID");
//            String sessionType = result.getString("sessionType");
//            int duration = result.getInt("duration");
//            String trainerName = result.getString("trainerName");
//            LocalDate date = result.getDate("date").toLocalDate();
//
//            SessionDTO sessionDTO = new SessionDTO(sessionID, sessionType,duration, trainerID, trainerName, slotID, date);
//
//            sessionList.add(sessionDTO);
//        }
//        return sessionList;
//    }
}
