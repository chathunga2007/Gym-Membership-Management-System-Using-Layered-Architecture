package lk.ijse.gymmembershipmanagementsystem.dao.custom;

import lk.ijse.gymmembershipmanagementsystem.dto.OrderSupplementDTO;

import java.util.List;

public interface OrderSupplementDAO {
    public boolean saveOrderSupplement(int orderId, List<OrderSupplementDTO> orderSupplementList) throws Exception;
}
