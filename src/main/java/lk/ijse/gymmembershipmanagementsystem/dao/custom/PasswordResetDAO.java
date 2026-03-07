package lk.ijse.gymmembershipmanagementsystem.dao.custom;

import lk.ijse.gymmembershipmanagementsystem.dao.SuperDAO;
import java.sql.SQLException;

public interface PasswordResetDAO extends SuperDAO {
    void saveOTP(String email, String otp) throws SQLException;
    boolean verifyOTP(String email, String otp) throws SQLException;
    void clearOTP(String email) throws SQLException;
}