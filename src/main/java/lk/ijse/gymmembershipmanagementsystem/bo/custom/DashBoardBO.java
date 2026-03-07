package lk.ijse.gymmembershipmanagementsystem.bo.custom;

import lk.ijse.gymmembershipmanagementsystem.bo.SuperBO;
import lk.ijse.gymmembershipmanagementsystem.dto.DailyIncomeDTO;
import java.sql.SQLException;
import java.util.List;

public interface DashBoardBO extends SuperBO {
    public int getTotalMembers() throws SQLException;

    public int getTotalTrainers() throws Exception;

    public int getTodaySessions() throws SQLException;

    public double getTodayIncome() throws SQLException;

    public List<String> getTodaySessionsList() throws SQLException;

    public List<DailyIncomeDTO> getDailyIncomeData() throws SQLException;
}
