package lk.ijse.gymmembershipmanagementsystem.dao.custom.impl;

import lk.ijse.gymmembershipmanagementsystem.dao.CrudUtil;
import lk.ijse.gymmembershipmanagementsystem.dao.custom.UserDAO;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAOImpl implements UserDAO {

    @Override
    public boolean checkLogin(String username, String password) throws SQLException {
        ResultSet rs = CrudUtil.execute(
                "SELECT * FROM user WHERE userName=? AND password=?",
                username, password);
        return rs.next();
    }

    @Override
    public boolean emailExists(String email) throws SQLException {
        ResultSet rs = CrudUtil.execute(
                "SELECT * FROM user WHERE email=?",
                email);
        return rs.next();
    }

    @Override
    public boolean updatePassword(String email, String newPassword) throws SQLException {
        return CrudUtil.execute(
                "UPDATE user SET password=? WHERE email=?",
                newPassword, email);
    }
}