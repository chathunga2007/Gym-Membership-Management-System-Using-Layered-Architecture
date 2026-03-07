package lk.ijse.gymmembershipmanagementsystem.bo.custom;

import lk.ijse.gymmembershipmanagementsystem.bo.SuperBO;
import lk.ijse.gymmembershipmanagementsystem.dto.OrderDTO;
import java.sql.SQLException;

public interface OrderBO extends SuperBO {
    boolean placeOrder(OrderDTO orderDTO) throws SQLException, Exception;
}