package lk.ijse.gymmembershipmanagementsystem.bo.custom;

import lk.ijse.gymmembershipmanagementsystem.bo.SuperBO;
import java.sql.SQLException;

public interface UserBO extends SuperBO {
    boolean checkLogin(String username, String password) throws SQLException;
    boolean emailExists(String email) throws SQLException;
    boolean updatePassword(String email, String newPassword) throws SQLException;
}