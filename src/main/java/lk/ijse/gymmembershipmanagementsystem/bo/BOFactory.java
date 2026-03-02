package lk.ijse.gymmembershipmanagementsystem.bo;

import lk.ijse.gymmembershipmanagementsystem.bo.custom.impl.MemberBOImpl;

public class BOFactory {
    public static BOFactory boFactory;

    private BOFactory() {}

    public static BOFactory getInstance() {
        return boFactory == null ? new BOFactory() : boFactory;
    }

    public enum BOType {
        MEMBER
    }

    public MemberBOImpl getBO(BOFactory.BOType daoType) {
        switch (daoType) {
            case MEMBER:
                return new MemberBOImpl();
            default:
                return null;
        }
    }
}
