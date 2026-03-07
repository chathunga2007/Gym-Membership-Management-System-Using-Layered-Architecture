package lk.ijse.gymmembershipmanagementsystem.bo.custom;

import lk.ijse.gymmembershipmanagementsystem.bo.SuperBO;

public interface PasswordResetBO extends SuperBO {
    void saveOTP(String email, String otp) throws Exception;
    boolean verifyOTP(String email, String otp) throws Exception;
    void clearOTP(String email) throws Exception;
}
