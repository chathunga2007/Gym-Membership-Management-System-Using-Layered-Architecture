package lk.ijse.gymmembershipmanagementsystem.controller;

import lk.ijse.gymmembershipmanagementsystem.bo.BOFactory;
import lk.ijse.gymmembershipmanagementsystem.bo.custom.PasswordResetBO;
import lk.ijse.gymmembershipmanagementsystem.bo.custom.UserBO;
import lk.ijse.gymmembershipmanagementsystem.util.EmailUtil;
import lk.ijse.gymmembershipmanagementsystem.util.OTPUtil;

public class PasswordResetController {
    private final PasswordResetBO passwordResetBO = (PasswordResetBO) BOFactory.getInstance().getBO(BOFactory.BOType.PASSWORD_RESET);
    private final UserBO userBO = (UserBO) BOFactory.getInstance().getBO(BOFactory.BOType.USER);

    public void sendOTP(String email) throws Exception {
        if (!userBO.emailExists(email))
            throw new RuntimeException("Email not found!");

        String otp = OTPUtil.generate();
        passwordResetBO.saveOTP(email, otp);
        EmailUtil.sendOTP(email, otp);
    }

    public boolean verifyOTP(String email, String otp) throws Exception {
        return passwordResetBO.verifyOTP(email, otp);
    }

    public void resetPassword(String email, String password) throws Exception {
        userBO.updatePassword(email, password);
        passwordResetBO.clearOTP(email);
    }
}
