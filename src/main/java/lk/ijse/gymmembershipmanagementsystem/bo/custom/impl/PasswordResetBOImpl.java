package lk.ijse.gymmembershipmanagementsystem.bo.custom.impl;

import lk.ijse.gymmembershipmanagementsystem.bo.custom.PasswordResetBO;
import lk.ijse.gymmembershipmanagementsystem.dao.DAOFactory;
import lk.ijse.gymmembershipmanagementsystem.dao.custom.PasswordResetDAO;

public class PasswordResetBOImpl implements PasswordResetBO {

    PasswordResetDAO passwordResetDAO = (PasswordResetDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.PASSWORD_RESET);

    @Override
    public void saveOTP(String email, String otp) throws Exception {
        passwordResetDAO.saveOTP(email, otp);
    }

    @Override
    public boolean verifyOTP(String email, String otp) throws Exception {
        return passwordResetDAO.verifyOTP(email, otp);
    }

    @Override
    public void clearOTP(String email) throws Exception {
        passwordResetDAO.clearOTP(email);
    }
}
