package lk.ijse.gymmembershipmanagementsystem.dao.custom.impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.gymmembershipmanagementsystem.dao.CrudUtil;
import lk.ijse.gymmembershipmanagementsystem.dao.custom.SessionDAO;
import lk.ijse.gymmembershipmanagementsystem.dto.SessionDTO;
import lk.ijse.gymmembershipmanagementsystem.dto.TimeSlotDTO;
import lk.ijse.gymmembershipmanagementsystem.dto.TrainerDTO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class SessionDAOImpl implements SessionDAO {
    @Override
    public boolean save(SessionDTO sessionDTO) throws SQLException {
        return CrudUtil.execute(
                "INSERT INTO Session (trainerID, slotID, sessionType, duration) VALUES (?, ?, ?, ?)",
                sessionDTO.getTrainerId(),
                sessionDTO.getSlotId(),
                sessionDTO.getSessionType(),
                sessionDTO.getDuration()
        );
    }

    @Override
    public int getLastSessionId() throws SQLException {

        ResultSet rs = CrudUtil.execute("SELECT LAST_INSERT_ID()");

        if (rs.next()) return rs.getInt(1);

        throw new SQLException("No session ID found");
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
        return CrudUtil.execute(
                "DELETE FROM Session WHERE sessionID = ?",
                id
        );
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

    @Override
    public ObservableList<TrainerDTO> loadTrainerID() throws SQLException {
        ObservableList<TrainerDTO> trainerList = FXCollections.observableArrayList();

        ResultSet rs = CrudUtil.execute("SELECT trainerID, name FROM Trainer");

        while (rs.next()) {
            trainerList.add(new TrainerDTO(
                    rs.getInt("trainerID"),
                    rs.getString("name")
            ));
        }

        return trainerList;
    }

    @Override
    public ObservableList<TimeSlotDTO> loadSlotID() throws SQLException {
        ObservableList<TimeSlotDTO> timeSlotList = FXCollections.observableArrayList();

        ResultSet rs = CrudUtil.execute("SELECT slotID, date FROM Time_Slot");

        while (rs.next()) {
            timeSlotList.add(new TimeSlotDTO(
                    rs.getInt("slotID"),
                    rs.getDate("date").toLocalDate()
            ));
        }

        return timeSlotList;
    }
}
