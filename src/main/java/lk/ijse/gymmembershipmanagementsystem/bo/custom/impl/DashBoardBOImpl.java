package lk.ijse.gymmembershipmanagementsystem.bo.custom.impl;

import lk.ijse.gymmembershipmanagementsystem.bo.custom.DashBoardBO;
import lk.ijse.gymmembershipmanagementsystem.dao.DAOFactory;
import lk.ijse.gymmembershipmanagementsystem.dao.custom.MemberDAO;
import lk.ijse.gymmembershipmanagementsystem.dao.custom.QueryDAO;
import lk.ijse.gymmembershipmanagementsystem.dao.custom.TrainerDAO;
import lk.ijse.gymmembershipmanagementsystem.dto.DailyIncomeDTO;
import java.sql.SQLException;
import java.util.List;

public class DashBoardBOImpl implements DashBoardBO {
    private final MemberDAO memberDAO = (MemberDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.MEMBER);
    private final TrainerDAO trainerDAO = (TrainerDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.TRAINER);
    private final QueryDAO queryDAO = (QueryDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.QUERY);

    @Override
    public int getTotalMembers() throws SQLException {
        return memberDAO.getTotalActiveMembers();
    }

    @Override
    public int getTotalTrainers() throws Exception {
        return trainerDAO.getTotalTrainers();
    }

    @Override
    public int getTodaySessions() throws SQLException {
        return queryDAO.getTodaySessions();
    }

    @Override
    public double getTodayIncome() throws SQLException {
        return queryDAO.getTodayIncome();
    }

    @Override
    public List<String> getTodaySessionsList() throws SQLException {
        return queryDAO.getTodaySessionsList();
    }

    @Override
    public List<DailyIncomeDTO> getDailyIncomeData() throws SQLException {
        return queryDAO.getDailyIncomeData();
    }
}
