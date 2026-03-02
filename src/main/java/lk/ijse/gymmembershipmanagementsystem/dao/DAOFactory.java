package lk.ijse.gymmembershipmanagementsystem.dao;

import lk.ijse.gymmembershipmanagementsystem.dao.custom.MemberDAO;
import lk.ijse.gymmembershipmanagementsystem.dao.custom.impl.MemberDAOImpl;

public class DAOFactory {
    public static DAOFactory daoFactory;

    private DAOFactory() {}

    public static DAOFactory getInstance() {
        return daoFactory == null ? new DAOFactory() : daoFactory;
    }

    public enum DAOType {
        MEMBER
    }

    public MemberDAO getDAO(DAOType daoType) {
        switch (daoType) {
            case MEMBER:
                return new MemberDAOImpl();
            default:
                return null;
        }
    }
}
