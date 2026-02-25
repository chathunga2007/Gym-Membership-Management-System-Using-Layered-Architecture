package lk.ijse.gymmembershipmanagementsystem.dao.custom.impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.gymmembershipmanagementsystem.dao.CrudUtil;
import lk.ijse.gymmembershipmanagementsystem.dao.custom.PaymentDAO;
import lk.ijse.gymmembershipmanagementsystem.dto.MemberDTO;
import lk.ijse.gymmembershipmanagementsystem.dto.PaymentDTO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PaymentDAOImpl implements PaymentDAO {
    @Override
    public boolean save(PaymentDTO paymentDTO) throws SQLException {
        boolean result = CrudUtil.execute(
                "INSERT INTO Payment (memberID, date, amount) VALUES (?, ?, ?)",
                paymentDTO.getMemberId(),
                paymentDTO.getPaymentDate(),
                paymentDTO.getAmount()
        );

        return result;
    }

    @Override
    public boolean update(PaymentDTO paymentDTO) throws SQLException {
        boolean result = CrudUtil.execute(
                "UPDATE Payment SET memberID = ?, date = ?, amount = ? WHERE paymentID = ?",
                paymentDTO.getMemberId(),
                paymentDTO.getPaymentDate(),
                paymentDTO.getAmount(),
                paymentDTO.getPaymentId()
        );
        return result;
    }

    @Override
    public boolean delete(String id) throws SQLException {
        boolean result = CrudUtil.execute(
                "DELETE FROM Payment WHERE paymentID = ?",
                id
        );
        return result;
    }

    @Override
    public PaymentDTO search(String id) throws SQLException {
        ResultSet rs = CrudUtil.execute("SELECT * FROM Payment WHERE paymentID = ?", id);

        if (rs.next()) {
            int paymentId = rs.getInt("paymentID");
            int memberId = rs.getInt("memberID");
            LocalDate date = rs.getDate("date").toLocalDate();
            double amount = rs.getDouble("amount");
            return new PaymentDTO(paymentId, memberId, amount, date);
        }
        return  null;
    }

    @Override
    public List<PaymentDTO> getAllPayment() throws SQLException {
        List<PaymentDTO> paymentList = new ArrayList();
        ResultSet  result = CrudUtil.execute("SELECT * FROM Payment ORDER BY paymentID DESC");

        while(result.next()) {

            int payID = result.getInt("paymentID");
            int memberID = result.getInt("memberID");
            LocalDate date = result.getDate("date").toLocalDate();
            double amount = result.getDouble("amount");

            PaymentDTO paymentDTO = new PaymentDTO(payID, memberID, amount, date);

            paymentList.add(paymentDTO);

        }
        return paymentList;
    }

    @Override
    public ObservableList<MemberDTO> loadMemberID() throws SQLException {
        ResultSet rs = CrudUtil.execute("SELECT memberID, name FROM Member");

        ObservableList<MemberDTO> memberDTOS = FXCollections.observableArrayList();

        while (rs.next()) {
            memberDTOS.add(new MemberDTO(
                    rs.getInt("memberID"),
                    rs.getString("name")
            ));
        }
        return memberDTOS;
    }
}
