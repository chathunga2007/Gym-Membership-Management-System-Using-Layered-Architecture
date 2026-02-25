package lk.ijse.gymmembershipmanagementsystem.dao.custom;

import javafx.collections.ObservableList;
import lk.ijse.gymmembershipmanagementsystem.dto.MemberDTO;
import lk.ijse.gymmembershipmanagementsystem.dto.PaymentDTO;

import java.sql.SQLException;
import java.util.List;

public interface PaymentDAO {
    public boolean save(PaymentDTO paymentDTO) throws SQLException;
    public boolean update(PaymentDTO paymentDTO) throws SQLException;
    public boolean delete(String id) throws SQLException;
    public PaymentDTO search(String id) throws SQLException;
    public List<PaymentDTO> getAllPayment() throws SQLException;
    public ObservableList<MemberDTO> loadMemberID()throws SQLException;
}
