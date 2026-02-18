package lk.ijse.gymmembershipmanagementsystem.dao.custom;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface MainContentDashBoardDAO {
    public List<String> getTodaySessions() throws SQLException;
    public Map<String, Double> getDailyIncomeData() throws SQLException;
}
