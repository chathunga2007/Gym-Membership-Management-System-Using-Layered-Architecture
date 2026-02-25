package lk.ijse.gymmembershipmanagementsystem.dao.custom;

import lk.ijse.gymmembershipmanagementsystem.dto.OrderDTO;

public interface OrderDAO {
    public boolean saveOrder(OrderDTO orderDTO) throws Exception;
}
