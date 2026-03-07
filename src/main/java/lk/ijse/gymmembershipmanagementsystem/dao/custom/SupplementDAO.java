package lk.ijse.gymmembershipmanagementsystem.dao.custom;

import lk.ijse.gymmembershipmanagementsystem.dao.CrudDAO;
import lk.ijse.gymmembershipmanagementsystem.entity.Supplement;

public interface SupplementDAO extends CrudDAO<Supplement> {
    boolean decreaseQty(int supplementId, int qty) throws Exception;
}
