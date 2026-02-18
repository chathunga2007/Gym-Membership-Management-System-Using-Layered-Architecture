package lk.ijse.gymmembershipmanagementsystem.dao.custom;

import java.sql.SQLException;

public interface DashBoardDAO {
    public int getTotalMembers() throws SQLException;
    public int getTotalTrainers() throws SQLException;
    public int getTodaySessions() throws SQLException;
    public double getTodayIncome() throws SQLException;
}
