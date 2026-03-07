package lk.ijse.gymmembershipmanagementsystem.bo;

import lk.ijse.gymmembershipmanagementsystem.bo.custom.impl.*;

public class BOFactory {
    public static BOFactory boFactory;

    private BOFactory() {}

    public static BOFactory getInstance() {
        return boFactory == null ? new BOFactory() : boFactory;
    }

    public enum BOType {
        EQUIPMENT, MEMBER, TRAINER, DASHBOARD, MEMBERSHIP, SUPPLEMENT, TIME_SLOT, PAYMENT, ORDER, SESSION, PRIVATE_SESSION, PASSWORD_RESET, USER
    }

    public SuperBO getBO(BOFactory.BOType boType) {
        switch (boType) {
            case EQUIPMENT:
                return new EquipmentBOImpl();
            case MEMBER:
                return new MemberBOImpl();
            case TRAINER:
                return new TrainerBOImpl();
            case DASHBOARD:
                return new DashBoardBOImpl();
            case MEMBERSHIP:
                return new MembershipBOImpl();
            case SUPPLEMENT:
                return new SupplementBOImpl();
            case TIME_SLOT:
                return new TimeSlotBOImpl();
            case PAYMENT:
                return new PaymentBOImpl();
            case ORDER:
                return new OrderBOImpl();
            case SESSION:
                return new SessionBOImpl();
            case PRIVATE_SESSION:
                return new PrivateSessionBOImpl();
            case PASSWORD_RESET:
                return new PasswordResetBOImpl();
            case USER:
                return new UserBOImpl();
            default:
                return null;
        }
    }
}
