package lk.ijse.gymmembershipmanagementsystem.dao.custom.impl;

import lk.ijse.gymmembershipmanagementsystem.dao.CrudUtil;
import lk.ijse.gymmembershipmanagementsystem.dao.custom.PaymentDAO;
import lk.ijse.gymmembershipmanagementsystem.entity.Payment;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PaymentDAOImpl implements PaymentDAO {

    @Override
    public boolean save(Payment entity) throws SQLException {
        return CrudUtil.execute(
                "INSERT INTO Payment (memberID, date, amount) VALUES (?,?,?)",
                entity.getMemberId(), entity.getPaymentDate(), entity.getAmount());
    }

    @Override
    public boolean update(Payment entity) throws SQLException {
        return CrudUtil.execute(
                "UPDATE Payment SET memberID=?, date=?, amount=? WHERE paymentID=?",
                entity.getMemberId(), entity.getPaymentDate(), entity.getAmount(), entity.getPaymentId());
    }

    @Override
    public boolean delete(String id) throws SQLException {
        return CrudUtil.execute("DELETE FROM Payment WHERE paymentID=?", id);
    }

    @Override
    public Payment search(String id) throws SQLException {
        ResultSet rst = CrudUtil.execute("SELECT * FROM Payment WHERE paymentID=?", id);
        if (rst.next()) {
            return new Payment(
                    rst.getInt("paymentID"),
                    rst.getInt("memberID"),
                    rst.getDouble("amount"),
                    rst.getDate("date").toLocalDate()
            );
        }
        return null;
    }

    @Override
    public List<Payment> getAll() throws SQLException {
        List<Payment> list = new ArrayList<>();
        ResultSet rst = CrudUtil.execute("SELECT * FROM Payment ORDER BY paymentID DESC");
        while (rst.next()) {
            list.add(new Payment(
                    rst.getInt("paymentID"),
                    rst.getInt("memberID"),
                    rst.getDouble("amount"),
                    rst.getDate("date").toLocalDate()
            ));
        }
        return list;
    }
}