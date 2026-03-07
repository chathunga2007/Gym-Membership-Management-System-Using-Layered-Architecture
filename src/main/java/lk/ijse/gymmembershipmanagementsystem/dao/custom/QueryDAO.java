package lk.ijse.gymmembershipmanagementsystem.dao.custom;

import lk.ijse.gymmembershipmanagementsystem.dao.SuperDAO;
import lk.ijse.gymmembershipmanagementsystem.dto.DailyIncomeDTO;
import lk.ijse.gymmembershipmanagementsystem.dto.MembershipDTO;
import lk.ijse.gymmembershipmanagementsystem.dto.PrivateSessionDTO;
import lk.ijse.gymmembershipmanagementsystem.dto.SessionDTO;
import java.sql.SQLException;
import java.util.List;

public interface QueryDAO extends SuperDAO {
    int getTodaySessions() throws SQLException;
    double getTodayIncome() throws SQLException;
    List<String> getTodaySessionsList() throws SQLException;
    List<DailyIncomeDTO> getDailyIncomeData() throws SQLException;
    List<MembershipDTO> getAllMembershipWithMemberName() throws SQLException;
    List<SessionDTO> getAllSessions() throws SQLException;
    public List<PrivateSessionDTO> getAllPrivateSessions() throws SQLException;
}
