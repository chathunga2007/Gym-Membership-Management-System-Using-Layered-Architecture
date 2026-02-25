package lk.ijse.gymmembershipmanagementsystem.dao.custom;

import lk.ijse.gymmembershipmanagementsystem.dto.SupplementDTO;
import java.sql.SQLException;
import java.util.List;

public interface SupplementDAO {
    public boolean save(SupplementDTO supplementDTO) throws SQLException;
    public boolean update(SupplementDTO supplementDTO) throws SQLException;
    public boolean delete(String id) throws SQLException;
    public SupplementDTO search(String id) throws SQLException;
    public List<SupplementDTO> getAllIds() throws SQLException;
    public boolean decreaseSupplementQty(int supplementId, int qty) throws SQLException;
}
