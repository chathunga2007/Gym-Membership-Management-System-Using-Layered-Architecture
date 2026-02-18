package lk.ijse.gymmembershipmanagementsystem.dao.custom.impl;

import lk.ijse.gymmembershipmanagementsystem.dao.CrudUtil;
import lk.ijse.gymmembershipmanagementsystem.dao.custom.MainContentDashBoardDAO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class MainContentDashBoardDAOImpl implements MainContentDashBoardDAO {
    @Override
    public List<String> getTodaySessions() throws SQLException {
        List<String> sessions = new ArrayList<>();

        ResultSet rs = CrudUtil.execute("SELECT t.timeIn, t.timeOut, s.sessionType, tr.name AS trainerName FROM Session s LEFT JOIN Time_Slot t ON s.slotID = t.slotID LEFT JOIN Trainer tr ON s.trainerID = tr.trainerID WHERE t.date = DATE(CONVERT_TZ(NOW(), 'SYSTEM', '+05:30')) ORDER BY t.timeIn");

        while (rs.next()) {
            String row = rs.getTime("timeIn") + " - " +
                    rs.getTime("timeOut") + " | " +
                    rs.getString("sessionType") +
                    " (Trainer: " + rs.getString("trainerName") + ")";
            sessions.add(row);
        }
        return sessions;
    }

    @Override
    public Map<String, Double> getDailyIncomeData() throws SQLException {
        Map<String, Double> incomeData = new LinkedHashMap<>();

        ResultSet rs = CrudUtil.execute("SELECT d, SUM(total) AS income FROM (SELECT date AS d, SUM(amount) AS total FROM Payment GROUP BY date UNION ALL SELECT o.date AS d, SUM(oi.price * oi.qty) AS total FROM orders o JOIN order_items oi ON o.id = oi.order_id GROUP BY o.date) x GROUP BY d ORDER BY d");

        while (rs.next()) {
            incomeData.put(rs.getDate("d").toString(), rs.getDouble("income"));
        }
        return incomeData;
    }
}
