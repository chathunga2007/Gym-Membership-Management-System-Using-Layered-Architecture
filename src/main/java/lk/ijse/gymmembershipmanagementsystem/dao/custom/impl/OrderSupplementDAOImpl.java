package lk.ijse.gymmembershipmanagementsystem.dao.custom.impl;

import lk.ijse.gymmembershipmanagementsystem.dao.CrudUtil;
import lk.ijse.gymmembershipmanagementsystem.dao.custom.OrderSupplementDAO;
import lk.ijse.gymmembershipmanagementsystem.dao.custom.SupplementDAO;
import lk.ijse.gymmembershipmanagementsystem.dto.OrderSupplementDTO;

import java.util.List;

public class OrderSupplementDAOImpl implements OrderSupplementDAO {
    private SupplementDAO supplementDAO = new SupplementDAOImpl();
    @Override
    public boolean saveOrderSupplement(int orderId, List<OrderSupplementDTO> orderSupplementList) throws Exception {
        for (OrderSupplementDTO orderSupplementDTO : orderSupplementList) {
            if(CrudUtil.execute(
                    "INSERT INTO order_items (order_id, supplement_id, qty, price) VALUES (?,?,?,?)",
                    orderId,
                    orderSupplementDTO.getSupplementId(),
                    orderSupplementDTO.getQty(),
                    orderSupplementDTO.getPrice())) {

                if(!supplementDAO.decreaseSupplementQty(orderSupplementDTO.getSupplementId(), orderSupplementDTO.getQty())) {
                    throw new Exception("Something went wrong when decreasing qty");
                }

            } else {
                throw new Exception("Something went wrong when inserting data into order items");
            }
        }
        return true;
    }
}
