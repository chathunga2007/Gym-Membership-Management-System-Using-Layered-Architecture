package lk.ijse.gymmembershipmanagementsystem.bo.custom.impl;

import javafx.collections.ObservableList;
import lk.ijse.gymmembershipmanagementsystem.bo.custom.PaymentBO;
import lk.ijse.gymmembershipmanagementsystem.dao.DAOFactory;
import lk.ijse.gymmembershipmanagementsystem.dao.custom.MemberDAO;
import lk.ijse.gymmembershipmanagementsystem.dao.custom.PaymentDAO;
import lk.ijse.gymmembershipmanagementsystem.dto.MemberDTO;
import lk.ijse.gymmembershipmanagementsystem.dto.PaymentDTO;
import lk.ijse.gymmembershipmanagementsystem.entity.Payment;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PaymentBOImpl implements PaymentBO {

    PaymentDAO paymentDAO = (PaymentDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.PAYMENT);
    MemberDAO memberDAO = (MemberDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.MEMBER);

    @Override
    public boolean savePayment(PaymentDTO dto) throws SQLException, ClassNotFoundException {
        return paymentDAO.save(new Payment(dto.getMemberId(), dto.getAmount(), dto.getPaymentDate()));
    }

    @Override
    public boolean updatePayment(PaymentDTO dto) throws SQLException, ClassNotFoundException {
        return paymentDAO.update(new Payment(dto.getPaymentId(), dto.getMemberId(), dto.getAmount(), dto.getPaymentDate()));
    }

    @Override
    public boolean deletePayment(String id) throws SQLException, ClassNotFoundException {
        return paymentDAO.delete(id);
    }

    @Override
    public PaymentDTO searchPayment(String id) throws SQLException, ClassNotFoundException {
        Payment p = paymentDAO.search(id);
        return p == null ? null : new PaymentDTO(p.getPaymentId(), p.getMemberId(), p.getAmount(), p.getPaymentDate());
    }

    @Override
    public List<PaymentDTO> getAllPayments() throws SQLException, ClassNotFoundException {
        List<Payment> entities = paymentDAO.getAll();
        List<PaymentDTO> dtos = new ArrayList<>();
        for (Payment p : entities) {
            dtos.add(new PaymentDTO(p.getPaymentId(), p.getMemberId(), p.getAmount(), p.getPaymentDate()));
        }
        return dtos;
    }

    @Override
    public ObservableList<MemberDTO> loadMemberID() throws SQLException {
        return memberDAO.loadMemberID();
    }
}