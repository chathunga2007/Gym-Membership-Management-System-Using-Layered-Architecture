package lk.ijse.gymmembershipmanagementsystem.dao.custom.impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.gymmembershipmanagementsystem.dao.CrudUtil;
import lk.ijse.gymmembershipmanagementsystem.dao.custom.SessionDAO;
import lk.ijse.gymmembershipmanagementsystem.db.DBConnection;
import lk.ijse.gymmembershipmanagementsystem.dto.SessionDTO;
import lk.ijse.gymmembershipmanagementsystem.dto.TimeSlotDTO;
import lk.ijse.gymmembershipmanagementsystem.dto.TrainerDTO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class SessionDAOImpl implements SessionDAO {
    @Override
    public boolean save(SessionDTO sessionDTO, List<Integer> equipmentIDs) throws SQLException {
        Connection con = DBConnection.getInstance().getConnection();
        con.setAutoCommit(false);

        try {
            boolean sessionSaved = CrudUtil.execute(
                    "INSERT INTO Session (trainerID, slotID, sessionType, duration) VALUES (?, ?, ?, ?)",
                    sessionDTO.getTrainerId(),
                    sessionDTO.getSlotId(),
                    sessionDTO.getSessionType(),
                    sessionDTO.getDuration()
            );

            if (!sessionSaved) {
                con.rollback();
                return false;
            }

            ResultSet rs = CrudUtil.execute("SELECT LAST_INSERT_ID()");
            rs.next();
            int sessionID = rs.getInt(1);

            if (equipmentIDs != null && !equipmentIDs.isEmpty()) {
                for (Integer eqID : equipmentIDs) {

                    ResultSet qtyRs = CrudUtil.execute(
                            "SELECT qty FROM Equipments WHERE equipmentsID = ?",
                            eqID
                    );
                    qtyRs.next();
                    int qty = qtyRs.getInt("qty");

                    if (qty <= 0) {
                        con.rollback();
                        throw new RuntimeException("Equipment out of stock!");
                    }

                    boolean eqSaved = CrudUtil.execute(
                            "INSERT INTO Session_Equipments (sessionID, equipmentsID) VALUES (?, ?)",
                            sessionID,
                            eqID
                    );

                    if (!eqSaved) {
                        con.rollback();
                        return false;
                    }

                    boolean qtyUpdated = CrudUtil.execute(
                            "UPDATE Equipments SET qty = qty - 1 WHERE equipmentsID = ?",
                            eqID
                    );

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

    @Override
    public boolean update(SessionDTO sessionDTO) throws SQLException {
        boolean result = CrudUtil.execute("UPDATE Session SET sessionType = ?, duration = ?, trainerID = ?, slotID =? WHERE sessionID = ?",
                sessionDTO.getSessionType(),
                sessionDTO.getDuration(),
                sessionDTO.getTrainerId(),
                sessionDTO.getSlotId(),
                sessionDTO.getSessionId()
        );

        return result;
    }

    @Override
    public boolean delete(String id) throws SQLException {
        Connection con = DBConnection.getInstance().getConnection();
        con.setAutoCommit(false);

        try {
            ResultSet rs = CrudUtil.execute(
                    "SELECT equipmentsID FROM Session_Equipments WHERE sessionID = ?",
                    id
            );

            List<Integer> equipmentIDs = new ArrayList<>();
            while (rs.next()) {
                equipmentIDs.add(rs.getInt("equipmentsID"));
            }

            CrudUtil.execute(
                    "DELETE FROM Session_Equipments WHERE sessionID = ?",
                    id
            );

            for (Integer eqID : equipmentIDs) {
                CrudUtil.execute(
                        "UPDATE Equipments SET qty = qty + 1 WHERE equipmentsID = ?",
                        eqID
                );
            }

            boolean result = CrudUtil.execute(
                    "DELETE FROM Session WHERE sessionID = ?",
                    id
            );

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

    @Override
    public SessionDTO search(String id) throws SQLException {
        ResultSet  result = CrudUtil.execute("SELECT * FROM Session WHERE sessionID = ?", id);

        if (result.next()) {
            int sessionID = result.getInt("sessionID");
            String sessionType = result.getString("sessionType");
            int duration = result.getInt("duration");
            int trainerID = result.getInt("trainerID");
            int slotID = result.getInt("slotID");
            return new SessionDTO(sessionID, slotID, trainerID, sessionType, duration);
        }
        return null;
    }

    @Override
    public ObservableList<TrainerDTO> loadTrainerID() throws SQLException {
        ObservableList<TrainerDTO> trainerDTO = FXCollections.observableArrayList();

        String sql = "SELECT trainerID, name FROM Trainer";

        ResultSet rs = CrudUtil.execute(sql);

        while (rs.next()) {
            trainerDTO.add(new TrainerDTO(
                    rs.getInt("trainerID"),
                    rs.getString("name")
            ));
        }

        return trainerDTO;
    }

    @Override
    public ObservableList<TimeSlotDTO> loadSlotID() throws SQLException {
        ObservableList<TimeSlotDTO> timeSlotDTO = FXCollections.observableArrayList();

        String sql = "SELECT slotID, date FROM Time_Slot";

        ResultSet rs = CrudUtil.execute(sql);

        while (rs.next()) {
            timeSlotDTO.add(new TimeSlotDTO(
                    rs.getInt("slotID"),
                    rs.getDate("date").toLocalDate() // safe conversion
            ));
        }

        return timeSlotDTO;
    }

    @Override
    public List<SessionDTO> getAllSession() throws SQLException {
        List<SessionDTO> sessionList = new ArrayList();

        ResultSet  result = CrudUtil.execute("SELECT \n" +
                "            s.sessionID,\n" +
                "            s.sessionType,\n" +
                "            s.duration,\n" +
                "            t.trainerID,\n" +
                "            t.name AS trainerName,\n" +
                "            ts.slotID,\n" +
                "            ts.date\n" +
                "        FROM Session s\n" +
                "        JOIN Trainer t ON s.trainerID = t.trainerID\n" +
                "        JOIN Time_Slot ts ON s.slotID = ts.slotID\n" +
                "        ORDER BY s.sessionID DESC");

        while(result.next()) {
            int sessionID = result.getInt("sessionID");
            int  slotID = result.getInt("slotID");
            int trainerID = result.getInt("trainerID");
            String sessionType = result.getString("sessionType");
            int duration = result.getInt("duration");
            String trainerName = result.getString("trainerName");
            LocalDate date = result.getDate("date").toLocalDate();

            SessionDTO sessionDTO = new SessionDTO(sessionID, sessionType,duration, trainerID, trainerName, slotID, date);

            sessionList.add(sessionDTO);
        }
        return sessionList;
    }
}
