package lk.ijse.gymmembershipmanagementsystem.bo.custom;

import lk.ijse.gymmembershipmanagementsystem.bo.SuperBO;
import lk.ijse.gymmembershipmanagementsystem.dto.SupplementDTO;
import java.sql.SQLException;
import java.util.List;

public interface SupplementBO extends SuperBO {
    boolean saveSupplement(SupplementDTO dto) throws SQLException, ClassNotFoundException;
    boolean updateSupplement(SupplementDTO dto) throws SQLException, ClassNotFoundException;
    boolean deleteSupplement(String id) throws SQLException, ClassNotFoundException;
    SupplementDTO searchSupplement(String id) throws SQLException, ClassNotFoundException;
    List<SupplementDTO> getAllSupplements() throws SQLException, ClassNotFoundException;
    boolean decreaseQty(int supplementId, int qty) throws Exception;
}