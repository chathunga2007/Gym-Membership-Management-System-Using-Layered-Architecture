package lk.ijse.gymmembershipmanagementsystem.dao.custom.impl;

import lk.ijse.gymmembershipmanagementsystem.dao.CrudUtil;
import lk.ijse.gymmembershipmanagementsystem.dao.custom.QueryDAO;
import lk.ijse.gymmembershipmanagementsystem.dto.DailyIncomeDTO;
import lk.ijse.gymmembershipmanagementsystem.dto.MembershipDTO;
import lk.ijse.gymmembershipmanagementsystem.dto.PrivateSessionDTO;
import lk.ijse.gymmembershipmanagementsystem.dto.SessionDTO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class QueryDAOImpl implements QueryDAO {
    @Override
    public int getTodaySessions() throws SQLException {
        String sql = """
            SELECT COUNT(*) 
            FROM Session s
            JOIN Time_Slot t ON s.slotID = t.slotID
            WHERE t.date = DATE(CONVERT_TZ(NOW(), 'SYSTEM', '+05:30'))
        """;
        ResultSet rs = CrudUtil.execute(sql);
        return rs.next() ? rs.getInt(1) : 0;
    }

    @Override
    public double getTodayIncome() throws SQLException {
        double payment = 0, supplement = 0;

        ResultSet rs1 = CrudUtil.execute(
                "SELECT IFNULL(SUM(amount),0) FROM Payment WHERE date = DATE(CONVERT_TZ(NOW(), 'SYSTEM', '+05:30'))"
        );
        if (rs1.next()) payment = rs1.getDouble(1);

        ResultSet rs2 = CrudUtil.execute("""
            SELECT IFNULL(SUM(price * qty),0)
            FROM order_items oi
            JOIN orders o ON oi.order_id = o.id
            WHERE o.date = DATE(CONVERT_TZ(NOW(), 'SYSTEM', '+05:30'))
        """);
        if (rs2.next()) supplement = rs2.getDouble(1);

        return payment + supplement;
    }

    @Override
    public List<String> getTodaySessionsList() throws SQLException {
        List<String> list = new ArrayList<>();
        String sql = """
            SELECT t.timeIn, t.timeOut, s.sessionType, tr.name AS trainerName
            FROM Session s
            LEFT JOIN Time_Slot t ON s.slotID = t.slotID
            LEFT JOIN Trainer tr ON s.trainerID = tr.trainerID
            WHERE t.date = DATE(CONVERT_TZ(NOW(), 'SYSTEM', '+05:30'))
            ORDER BY t.timeIn
        """;

        ResultSet rs = CrudUtil.execute(sql);
        boolean hasData = false;
        while (rs.next()) {
            hasData = true;
            String row = rs.getTime("timeIn") + " - " + rs.getTime("timeOut") +
                    " | " + rs.getString("sessionType") +
                    " (Trainer: " + rs.getString("trainerName") + ")";
            list.add(row);
        }
        if (!hasData) list.add("No sessions scheduled for today");
        return list;
    }

    @Override
    public List<DailyIncomeDTO> getDailyIncomeData() throws SQLException {
        List<DailyIncomeDTO> list = new ArrayList<>();
        String sql = """
            SELECT d, SUM(total) AS income
            FROM (
                SELECT date AS d, SUM(amount) AS total FROM Payment GROUP BY date
                UNION ALL
                SELECT o.date AS d, SUM(oi.price * oi.qty) AS total
                FROM orders o JOIN order_items oi ON o.id = oi.order_id GROUP BY o.date
            ) x
            GROUP BY d ORDER BY d
        """;

        ResultSet rs = CrudUtil.execute(sql);
        while (rs.next()) {
            list.add(new DailyIncomeDTO(rs.getDate("d").toString(), rs.getDouble("income")));
        }
        return list;
    }

    @Override
    public List<MembershipDTO> getAllMembershipWithMemberName() throws SQLException {
        List<MembershipDTO> list = new ArrayList<>();
        String sql = """
        SELECT 
            mship.membershipID,
            mship.memberID,
            mem.name AS memberName,
            mship.membershipType,
            mship.issuedDate,
            mship.expiryDate
        FROM Membership mship
        JOIN Member mem ON mship.memberID = mem.memberID
        ORDER BY mship.membershipID DESC;
    """;

        ResultSet rst = CrudUtil.execute(sql);
        while (rst.next()) {
            list.add(new MembershipDTO(
                    rst.getInt("membershipID"),
                    rst.getInt("memberID"),
                    rst.getString("membershipType"),
                    rst.getDate("issuedDate").toLocalDate(),
                    rst.getDate("expiryDate").toLocalDate(),
                    rst.getString("memberName")
            ));
        }
        return list;
    }

    @Override
    public List<SessionDTO> getAllSessions() throws SQLException {
        List<SessionDTO> list = new ArrayList<>();
        String sql = """
        SELECT s.sessionID, s.sessionType, s.duration,
               t.trainerID, t.name AS trainerName,
               ts.slotID, ts.date
        FROM Session s
        JOIN Trainer t ON s.trainerID = t.trainerID
        JOIN Time_Slot ts ON s.slotID = ts.slotID
        ORDER BY s.sessionID DESC
    """;

        ResultSet rs = CrudUtil.execute(sql);
        while (rs.next()) {
            list.add(new SessionDTO(
                    rs.getInt("sessionID"),
                    rs.getString("sessionType"),
                    rs.getInt("duration"),
                    rs.getInt("trainerID"),
                    rs.getString("trainerName"),
                    rs.getInt("slotID"),
                    rs.getDate("date").toLocalDate()
            ));
        }
        return list;
    }

    @Override
    public List<PrivateSessionDTO> getAllPrivateSessions() throws SQLException {
        List<PrivateSessionDTO> list = new ArrayList<>();
        String sql = """
        SELECT ps.privateSessionID, ps.memberID, m.name AS memberName,
               ps.timeIn, ps.timeOut, ps.extraCharges
        FROM Private_Session ps
        JOIN Member m ON ps.memberID = m.memberID
        ORDER BY ps.privateSessionID DESC
    """;

        ResultSet rst = CrudUtil.execute(sql);
        while (rst.next()) {
            list.add(new PrivateSessionDTO(
                    rst.getInt("privateSessionID"),
                    rst.getInt("memberID"),
                    rst.getString("timeIn"),
                    rst.getString("timeOut"),
                    rst.getDouble("extraCharges"),
                    rst.getString("memberName")
            ));
        }
        return list;
    }
}
