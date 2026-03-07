package lk.ijse.gymmembershipmanagementsystem.dao.custom.impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.gymmembershipmanagementsystem.dao.CrudUtil;
import lk.ijse.gymmembershipmanagementsystem.dao.custom.TimeSlotDAO;
import lk.ijse.gymmembershipmanagementsystem.dto.TimeSlotDTO;
import lk.ijse.gymmembershipmanagementsystem.entity.TimeSlot;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TimeSlotDAOImpl implements TimeSlotDAO {

    @Override
    public boolean save(TimeSlot entity) throws SQLException {
        return CrudUtil.execute(
                "INSERT INTO Time_Slot (date, timeIn, timeOut) VALUES (?,?,?)",
                entity.getDate(), entity.getTimeIn(), entity.getTimeOut());
    }

    @Override
    public boolean update(TimeSlot entity) throws SQLException {
        return CrudUtil.execute(
                "UPDATE Time_Slot SET date=?, timeIn=?, timeOut=? WHERE slotID=?",
                entity.getDate(), entity.getTimeIn(), entity.getTimeOut(), entity.getSlotId());
    }

    @Override
    public boolean delete(String id) throws SQLException {
        return CrudUtil.execute("DELETE FROM Time_Slot WHERE slotID=?", id);
    }

    @Override
    public TimeSlot search(String id) throws SQLException {
        ResultSet rst = CrudUtil.execute("SELECT * FROM Time_Slot WHERE slotID=?", id);
        if (rst.next()) {
            return new TimeSlot(
                    rst.getInt("slotID"),
                    rst.getDate("date").toLocalDate(),
                    rst.getString("timeIn"),
                    rst.getString("timeOut")
            );
        }
        return null;
    }

    @Override
    public List<TimeSlot> getAll() throws SQLException {
        List<TimeSlot> list = new ArrayList<>();
        ResultSet rst = CrudUtil.execute("SELECT * FROM Time_Slot ORDER BY slotID DESC");
        while (rst.next()) {
            list.add(new TimeSlot(
                    rst.getInt("slotID"),
                    rst.getDate("date").toLocalDate(),
                    rst.getString("timeIn"),
                    rst.getString("timeOut")
            ));
        }
        return list;
    }

    @Override
    public ObservableList<TimeSlotDTO> loadTimeSlotIDs() throws SQLException {
        ObservableList<TimeSlotDTO> list = FXCollections.observableArrayList();
        ResultSet rs = CrudUtil.execute("SELECT slotID, date FROM Time_Slot");
        while (rs.next()) {
            list.add(new TimeSlotDTO(
                    rs.getInt("slotID"),
                    rs.getDate("date").toLocalDate()
            ));
        }
        return list;
    }
}