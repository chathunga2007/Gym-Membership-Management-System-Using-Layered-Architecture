package lk.ijse.gymmembershipmanagementsystem.dao.custom;

import lk.ijse.gymmembershipmanagementsystem.dao.CrudDAO;
import lk.ijse.gymmembershipmanagementsystem.entity.Order;

public interface OrderDAO extends CrudDAO<Order> {
    int getLastOrderId() throws Exception;
}
