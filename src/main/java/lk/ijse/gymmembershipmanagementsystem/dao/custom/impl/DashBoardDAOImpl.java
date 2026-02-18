package lk.ijse.gymmembershipmanagementsystem.dao.custom.impl;

import lk.ijse.gymmembershipmanagementsystem.dao.CrudUtil;
import lk.ijse.gymmembershipmanagementsystem.dao.custom.DashBoardDAO;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DashBoardDAOImpl implements DashBoardDAO {
    @Override
    public int getTotalMembers() throws SQLException {
        ResultSet rs = CrudUtil.execute("SELECT COUNT(*) FROM Member WHERE status = ?", "ACTIVE");

        return rs.next() ? rs.getInt(1) : 0;
    }

    @Override
    public int getTotalTrainers() throws SQLException {
        ResultSet rs = CrudUtil.execute("SELECT COUNT(*) FROM Trainer");

        return rs.next() ? rs.getInt(1) : 0;
    }

    @Override
    public int getTodaySessions() throws SQLException {
        String sql = "SELECT COUNT(*) FROM Session s JOIN Time_Slot t ON s.slotID = t.slotID WHERE t.date = DATE(CONVERT_TZ(NOW(), 'SYSTEM', '+05:30'))";

        ResultSet rs = CrudUtil.execute(sql);

        if (rs.next()) {
            return rs.getInt(1);
        }
        return 0;
    }

    @Override
    public double getTodayIncome() throws SQLException {
        double total = 0;

        String paymentSql = "SELECT IFNULL(SUM(amount),0) FROM Payment WHERE date = DATE(CONVERT_TZ(NOW(), 'SYSTEM', '+05:30'))";

        ResultSet rs1 = CrudUtil.execute(paymentSql);
        if (rs1.next()) {
            total += rs1.getDouble(1);
        }

        String supplementSql = "SELECT IFNULL(SUM(price * qty),0) FROM order_items oi JOIN orders o ON oi.order_id = o.id WHERE o.date = DATE(CONVERT_TZ(NOW(), 'SYSTEM', '+05:30'))";

        ResultSet rs2 = CrudUtil.execute(supplementSql);
        if (rs2.next()) {
            total += rs2.getDouble(1);
        }

        return total;
    }
}
