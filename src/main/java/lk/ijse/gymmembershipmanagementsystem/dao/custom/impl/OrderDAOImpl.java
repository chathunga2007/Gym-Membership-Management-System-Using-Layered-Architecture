package lk.ijse.gymmembershipmanagementsystem.dao.custom.impl;

import lk.ijse.gymmembershipmanagementsystem.dao.CrudUtil;
import lk.ijse.gymmembershipmanagementsystem.dao.custom.OrderDAO;
import lk.ijse.gymmembershipmanagementsystem.entity.Order;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class OrderDAOImpl implements OrderDAO {

    @Override
    public boolean save(Order entity) throws SQLException {
        return CrudUtil.execute("INSERT INTO orders (date, memberID) VALUES (?,?)",
                entity.getOrderDate(), entity.getMemberId());
    }

    @Override
    public int getLastOrderId() throws SQLException {
        ResultSet rs = CrudUtil.execute("SELECT id FROM orders ORDER BY id DESC LIMIT 1");
        return rs.next() ? rs.getInt("id") : -1;
    }

    @Override public boolean update(Order entity) throws SQLException { return false; }
    @Override public boolean delete(String id) throws SQLException { return false; }
    @Override public Order search(String id) throws SQLException { return null; }
    @Override public List<Order> getAll() throws SQLException { return null; }
}