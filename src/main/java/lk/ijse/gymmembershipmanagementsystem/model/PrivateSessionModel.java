package lk.ijse.gymmembershipmanagementsystem.model;

import lk.ijse.gymmembershipmanagementsystem.dao.custom.PaymentDAO;
import lk.ijse.gymmembershipmanagementsystem.dao.custom.PrivateSessionDAO;
import lk.ijse.gymmembershipmanagementsystem.dao.custom.impl.PaymentDAOImpl;
import lk.ijse.gymmembershipmanagementsystem.dao.custom.impl.PrivateSessionDAOImpl;
import lk.ijse.gymmembershipmanagementsystem.db.DBConnection;
import lk.ijse.gymmembershipmanagementsystem.dto.PaymentDTO;
import lk.ijse.gymmembershipmanagementsystem.dto.PrivateSessionDTO;
import java.sql.Connection;
import java.sql.SQLException;

public class PrivateSessionModel {

    PrivateSessionDAO privateSessionDAO = new PrivateSessionDAOImpl();
    PaymentDAO paymentDAO = new PaymentDAOImpl();

    public boolean saveWithPayment(PrivateSessionDTO sessionDTO, PaymentDTO paymentDTO) throws SQLException {

        Connection con = DBConnection.getInstance().getConnection();
        con.setAutoCommit(false);

        try {
            boolean sessionSaved = privateSessionDAO.saveSession(sessionDTO);

            if (!sessionSaved) {
                con.rollback();
                return false;
            }

            boolean paymentSaved = paymentDAO.save(paymentDTO);

            if (paymentSaved) {
                con.commit();
                return true;
            } else {
                con.rollback();
                return false;
            }

        } catch (Exception e) {
            con.rollback();
            throw e;
        } finally {
            con.setAutoCommit(true);
        }
    }
}
