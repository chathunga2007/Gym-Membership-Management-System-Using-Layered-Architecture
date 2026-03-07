package lk.ijse.gymmembershipmanagementsystem.dao.custom.impl;

import lk.ijse.gymmembershipmanagementsystem.dao.CrudUtil;
import lk.ijse.gymmembershipmanagementsystem.dao.custom.OrderSupplementDAO;
import lk.ijse.gymmembershipmanagementsystem.entity.OrderSupplement;
import java.sql.SQLException;
import java.util.List;

public class OrderSupplementDAOImpl implements OrderSupplementDAO {
    @Override
    public boolean save(OrderSupplement entity) throws SQLException {
        return CrudUtil.execute(
                "INSERT INTO order_items (order_id, supplement_id, qty, price) VALUES (?,?,?,?)",
                entity.getOrderId(), entity.getSupplementId(), entity.getQty(), entity.getPrice());
    }

    @Override
    public boolean update(OrderSupplement entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public OrderSupplement search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public List<OrderSupplement> getAll() throws SQLException, ClassNotFoundException {
        return List.of();
    }
}