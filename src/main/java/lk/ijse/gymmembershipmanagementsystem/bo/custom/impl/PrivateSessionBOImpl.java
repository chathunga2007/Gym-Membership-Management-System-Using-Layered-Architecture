package lk.ijse.gymmembershipmanagementsystem.bo.custom.impl;

import javafx.collections.ObservableList;
import lk.ijse.gymmembershipmanagementsystem.bo.custom.PrivateSessionBO;
import lk.ijse.gymmembershipmanagementsystem.dao.DAOFactory;
import lk.ijse.gymmembershipmanagementsystem.dao.custom.MemberDAO;
import lk.ijse.gymmembershipmanagementsystem.dao.custom.PaymentDAO;
import lk.ijse.gymmembershipmanagementsystem.dao.custom.PrivateSessionDAO;
import lk.ijse.gymmembershipmanagementsystem.dao.custom.QueryDAO;
import lk.ijse.gymmembershipmanagementsystem.db.DBConnection;
import lk.ijse.gymmembershipmanagementsystem.dto.MemberDTO;
import lk.ijse.gymmembershipmanagementsystem.dto.PaymentDTO;
import lk.ijse.gymmembershipmanagementsystem.dto.PrivateSessionDTO;
import lk.ijse.gymmembershipmanagementsystem.entity.Payment;
import lk.ijse.gymmembershipmanagementsystem.entity.PrivateSession;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class PrivateSessionBOImpl implements PrivateSessionBO {

    PrivateSessionDAO privateSessionDAO = (PrivateSessionDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.PRIVATE_SESSION);
    PaymentDAO paymentDAO = (PaymentDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.PAYMENT);
    QueryDAO queryDAO = (QueryDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.QUERY);
    MemberDAO memberDAO = (MemberDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.MEMBER);

    @Override
    public boolean saveWithPayment(PrivateSessionDTO sessionDTO, PaymentDTO paymentDTO) throws Exception {
        Connection connection = DBConnection.getInstance().getConnection();
        try {
            connection.setAutoCommit(false);

            boolean sessionSaved = privateSessionDAO.save(new PrivateSession(
                    sessionDTO.getMemberId(), sessionDTO.getTimeIn(), sessionDTO.getTimeOut(), sessionDTO.getExtraCharges()));

            if (!sessionSaved) throw new Exception("Private Session save failed");

            boolean paymentSaved = paymentDAO.save(new Payment(
                    paymentDTO.getMemberId(), paymentDTO.getAmount(), paymentDTO.getPaymentDate()));

            if (!paymentSaved) throw new Exception("Payment save failed");

            connection.commit();
            return true;

        } catch (Exception e) {
            connection.rollback();
            throw e;
        } finally {
            connection.setAutoCommit(true);
        }
    }

    @Override
    public boolean updatePrivateSession(PrivateSessionDTO dto) throws SQLException, ClassNotFoundException {
        return privateSessionDAO. update(new PrivateSession(
                dto.getPrivateSessionId(), dto.getMemberId(), dto.getTimeIn(), dto.getTimeOut(), dto.getExtraCharges()));
    }

    @Override
    public boolean deletePrivateSession(String id) throws SQLException, ClassNotFoundException {
        return privateSessionDAO.delete(id);
    }

    @Override
    public PrivateSessionDTO searchPrivateSession(String id) throws SQLException, ClassNotFoundException {
        PrivateSession ps = privateSessionDAO.search(id);
        return ps == null ? null : new PrivateSessionDTO(
                ps.getPrivateSessionId(), ps.getMemberId(), ps.getTimeIn(), ps.getTimeOut(), ps.getExtraCharges());
    }

    @Override
    public List<PrivateSessionDTO> getAllPrivateSessions() throws SQLException {
        return queryDAO.getAllPrivateSessions();
    }

    @Override
    public ObservableList<MemberDTO> loadMemberIDs() throws SQLException {
        return memberDAO.loadMemberID();
    }
}