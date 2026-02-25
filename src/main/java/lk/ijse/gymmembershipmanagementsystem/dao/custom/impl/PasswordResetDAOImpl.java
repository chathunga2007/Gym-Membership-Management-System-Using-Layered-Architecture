package lk.ijse.gymmembershipmanagementsystem.dao.custom.impl;

import lk.ijse.gymmembershipmanagementsystem.dao.CrudUtil;
import lk.ijse.gymmembershipmanagementsystem.dao.custom.PasswordResetDAO;

import java.sql.ResultSet;

public class PasswordResetDAOImpl implements PasswordResetDAO {
    @Override
    public void saveOTP(String email, String otp) throws Exception {
        CrudUtil.execute(
                "DELETE FROM password_reset WHERE email=?",
                email
        );

        CrudUtil.execute(
                "INSERT INTO password_reset (email, otp, created_at) VALUES (?, ?, NOW())",
                email,
                otp
        );
    }

    @Override
    public boolean verifyOTP(String email, String otp) throws Exception {
        ResultSet rs = CrudUtil.execute(
                "SELECT * FROM password_reset WHERE email=? AND otp=?",
                email,
                otp
        );
        return rs.next();
    }

    @Override
    public void clearOTP(String email) throws Exception {
        CrudUtil.execute(
                "DELETE FROM password_reset WHERE email=?",
                email
        );
    }
}
