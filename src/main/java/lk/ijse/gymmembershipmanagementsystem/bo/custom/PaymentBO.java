package lk.ijse.gymmembershipmanagementsystem.bo.custom;

import javafx.collections.ObservableList;
import lk.ijse.gymmembershipmanagementsystem.bo.SuperBO;
import lk.ijse.gymmembershipmanagementsystem.dto.MemberDTO;
import lk.ijse.gymmembershipmanagementsystem.dto.PaymentDTO;
import java.sql.SQLException;
import java.util.List;

public interface PaymentBO extends SuperBO {
    boolean savePayment(PaymentDTO dto) throws SQLException, ClassNotFoundException;
    boolean updatePayment(PaymentDTO dto) throws SQLException, ClassNotFoundException;
    boolean deletePayment(String id) throws SQLException, ClassNotFoundException;
    PaymentDTO searchPayment(String id) throws SQLException, ClassNotFoundException;
    List<PaymentDTO> getAllPayments() throws SQLException, ClassNotFoundException;
    ObservableList<MemberDTO> loadMemberID() throws SQLException;
}