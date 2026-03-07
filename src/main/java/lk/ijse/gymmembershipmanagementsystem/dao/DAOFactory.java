package lk.ijse.gymmembershipmanagementsystem.dao;

import lk.ijse.gymmembershipmanagementsystem.dao.custom.impl.*;

public class DAOFactory {
    public static DAOFactory daoFactory;

    private DAOFactory() {}

    public static DAOFactory getInstance() {
        return daoFactory == null ? new DAOFactory() : daoFactory;
    }

    public enum DAOType {
        Equipment, MEMBER, TRAINER, QUERY, MEMBERSHIP, SUPPLEMENT, TIME_SLOT, PAYMENT, ORDER, ORDER_SUPPLEMENT, SESSION, SESSION_EQUIPMENT, PRIVATE_SESSION, PASSWORD_RESET, USER
    }

    public SuperDAO getDAO(DAOType daoType) {
        switch (daoType) {
            case Equipment:
                return new EquipmentDAOImpl();
            case MEMBER:
                return new MemberDAOImpl();
            case TRAINER:
                return new TrainerDAOImpl();
            case QUERY:
                return new QueryDAOImpl();
            case MEMBERSHIP:
                return new MembershipDAOImpl();
            case SUPPLEMENT:
                return new SupplementDAOImpl();
            case TIME_SLOT:
                return new TimeSlotDAOImpl();
            case PAYMENT:
                return new PaymentDAOImpl();
            case ORDER:
                return new OrderDAOImpl();
            case ORDER_SUPPLEMENT:
                return new OrderSupplementDAOImpl();
            case SESSION:
                return new SessionDAOImpl();
            case SESSION_EQUIPMENT:
                return new SessionEquipmentDAOImpl();
            case PRIVATE_SESSION:
                return new PrivateSessionDAOImpl();
            case PASSWORD_RESET:
                return new PasswordResetDAOImpl();
            case USER:
                return new UserDAOImpl();
            default:
                return null;
        }
    }
}
