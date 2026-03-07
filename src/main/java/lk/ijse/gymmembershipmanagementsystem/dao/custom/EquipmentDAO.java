package lk.ijse.gymmembershipmanagementsystem.dao.custom;

import lk.ijse.gymmembershipmanagementsystem.dao.CrudDAO;
import lk.ijse.gymmembershipmanagementsystem.entity.Equipment;
import java.sql.SQLException;

public interface EquipmentDAO extends CrudDAO<Equipment> {
    boolean increaseQty(int equipmentId, int qty) throws SQLException;
    boolean decreaseQty(int equipmentId, int qty) throws SQLException;
}