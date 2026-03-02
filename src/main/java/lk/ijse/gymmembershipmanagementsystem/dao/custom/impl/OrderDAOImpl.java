package lk.ijse.gymmembershipmanagementsystem.dao.custom.impl;

import lk.ijse.gymmembershipmanagementsystem.dao.CrudUtil;
import lk.ijse.gymmembershipmanagementsystem.dao.custom.OrderDAO;
import lk.ijse.gymmembershipmanagementsystem.dto.OrderDTO;

import java.sql.ResultSet;

public class OrderDAOImpl implements OrderDAO {
    @Override
    public boolean saveOrder(OrderDTO orderDTO) throws Exception {
        boolean isSavedOrder = CrudUtil.execute(
                "INSERT INTO orders (date, memberID) VALUES (?,?)",
                orderDTO.getOrderDate(),
                orderDTO.getMemberId()
        );
        return isSavedOrder;
    }

    @Override
    public int getLatestOrderId() throws Exception {

        ResultSet rs = CrudUtil.execute(
                "SELECT id FROM orders ORDER BY id DESC LIMIT 1"
        );

        if (rs.next()) {
            return rs.getInt("id");
        }

        throw new Exception("No Order ID found");
    }
}
