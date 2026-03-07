package lk.ijse.gymmembershipmanagementsystem.dao.custom.impl;

import lk.ijse.gymmembershipmanagementsystem.dao.CrudUtil;
import lk.ijse.gymmembershipmanagementsystem.dao.custom.PrivateSessionDAO;
import lk.ijse.gymmembershipmanagementsystem.entity.PrivateSession;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PrivateSessionDAOImpl implements PrivateSessionDAO {

    @Override
    public boolean save(PrivateSession entity) throws SQLException {
        return CrudUtil.execute(
                "INSERT INTO Private_Session (memberID, timeIn, timeOut, extraCharges) VALUES (?,?,?,?)",
                entity.getMemberId(), entity.getTimeIn(), entity.getTimeOut(), entity.getExtraCharges());
    }

    @Override
    public boolean update(PrivateSession entity) throws SQLException {
        return CrudUtil.execute(
                "UPDATE Private_Session SET timeIn=?, timeOut=?, extraCharges=?, memberID=? WHERE privateSessionID=?",
                entity.getTimeIn(), entity.getTimeOut(), entity.getExtraCharges(), entity.getMemberId(), entity.getPrivateSessionId());
    }

    @Override
    public boolean delete(String id) throws SQLException {
        return CrudUtil.execute("DELETE FROM Private_Session WHERE privateSessionID=?", id);
    }

    @Override
    public PrivateSession search(String id) throws SQLException {
        ResultSet rst = CrudUtil.execute("SELECT * FROM Private_Session WHERE privateSessionID=?", id);
        if (rst.next()) {
            return new PrivateSession(
                    rst.getInt("privateSessionID"),
                    rst.getInt("memberID"),
                    rst.getString("timeIn"),
                    rst.getString("timeOut"),
                    rst.getDouble("extraCharges")
            );
        }
        return null;
    }

    @Override
    public List<PrivateSession> getAll() throws SQLException {
        List<PrivateSession> list = new ArrayList<>();
        ResultSet rst = CrudUtil.execute("SELECT * FROM Private_Session ORDER BY privateSessionID DESC");
        while (rst.next()) {
            list.add(new PrivateSession(
                    rst.getInt("privateSessionID"),
                    rst.getInt("memberID"),
                    rst.getString("timeIn"),
                    rst.getString("timeOut"),
                    rst.getDouble("extraCharges")
            ));
        }
        return list;
    }
}