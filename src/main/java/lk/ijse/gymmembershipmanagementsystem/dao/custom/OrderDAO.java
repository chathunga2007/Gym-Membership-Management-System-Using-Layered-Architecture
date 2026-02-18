package lk.ijse.gymmembershipmanagementsystem.dao.custom;

import lk.ijse.gymmembershipmanagementsystem.dto.OrderDTO;

public interface OrderDAO {
    public boolean placeOrder(OrderDTO orderDTO) throws Exception;
    public void printOrderInvoice(int orderId) throws Exception;
}
