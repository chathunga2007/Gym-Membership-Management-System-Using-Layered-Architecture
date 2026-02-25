package lk.ijse.gymmembershipmanagementsystem.dao.custom.impl;

import lk.ijse.gymmembershipmanagementsystem.dao.CrudUtil;
import lk.ijse.gymmembershipmanagementsystem.dao.custom.OrderDAO;
import lk.ijse.gymmembershipmanagementsystem.dto.OrderDTO;

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
}
