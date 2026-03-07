package lk.ijse.gymmembershipmanagementsystem.bo.custom.impl;

import lk.ijse.gymmembershipmanagementsystem.bo.custom.UserBO;
import lk.ijse.gymmembershipmanagementsystem.dao.DAOFactory;
import lk.ijse.gymmembershipmanagementsystem.dao.custom.UserDAO;
import java.sql.SQLException;

public class UserBOImpl implements UserBO {

    UserDAO userDAO = (UserDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.USER);

    @Override
    public boolean checkLogin(String username, String password) throws SQLException {
        return userDAO.checkLogin(username, password);
    }

    @Override
    public boolean emailExists(String email) throws SQLException {
        return userDAO.emailExists(email);
    }

    @Override
    public boolean updatePassword(String email, String newPassword) throws SQLException {
        return userDAO.updatePassword(email, newPassword);
    }
}