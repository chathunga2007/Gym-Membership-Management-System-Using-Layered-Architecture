package lk.ijse.gymmembershipmanagementsystem.dao.custom;

import lk.ijse.gymmembershipmanagementsystem.dao.CrudDAO;
import lk.ijse.gymmembershipmanagementsystem.entity.OrderSupplement;
import java.sql.SQLException;

public interface OrderSupplementDAO extends CrudDAO<OrderSupplement> {
    boolean save(OrderSupplement entity) throws SQLException;
}
