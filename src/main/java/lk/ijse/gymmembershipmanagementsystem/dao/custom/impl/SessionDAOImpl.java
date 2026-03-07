package lk.ijse.gymmembershipmanagementsystem.dao.custom.impl;

import lk.ijse.gymmembershipmanagementsystem.dao.CrudUtil;
import lk.ijse.gymmembershipmanagementsystem.dao.custom.SessionDAO;
import lk.ijse.gymmembershipmanagementsystem.entity.Session;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class SessionDAOImpl implements SessionDAO {

    @Override
    public boolean save(Session entity) throws SQLException {
        return CrudUtil.execute(
                "INSERT INTO Session (slotID, trainerID, sessionType, duration) VALUES (?,?,?,?)",
                entity.getSlotId(), entity.getTrainerId(), entity.getSessionType(), entity.getDuration());
    }

    @Override
    public int getLastSessionId() throws SQLException {
        ResultSet rs = CrudUtil.execute("SELECT LAST_INSERT_ID()");
        return rs.next() ? rs.getInt(1) : -1;
    }

    @Override public boolean update(Session entity) throws SQLException { return false; }
    @Override public boolean delete(String id) throws SQLException { return false; }
    @Override public Session search(String id) throws SQLException { return null; }
    @Override public List<Session> getAll() throws SQLException { return null; }
}