package lk.ijse.gymmembershipmanagementsystem.dao.custom;

import lk.ijse.gymmembershipmanagementsystem.dao.SuperDAO;
import java.sql.SQLException;

public interface UserDAO extends SuperDAO {
    boolean checkLogin(String username, String password) throws SQLException;
    boolean emailExists(String email) throws SQLException;
    boolean updatePassword(String email, String newPassword) throws SQLException;
}