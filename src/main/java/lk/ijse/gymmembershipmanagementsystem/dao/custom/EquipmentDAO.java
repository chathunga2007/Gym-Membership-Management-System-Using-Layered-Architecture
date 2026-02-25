package lk.ijse.gymmembershipmanagementsystem.dao.custom;

import lk.ijse.gymmembershipmanagementsystem.dto.EquipmentDTO;

import java.sql.SQLException;
import java.util.List;

public interface EquipmentDAO {
    public boolean save(EquipmentDTO equipmentDTO) throws SQLException;
    public boolean update(EquipmentDTO equipmentDTO) throws SQLException;
    public boolean delete(String id) throws SQLException;
    public EquipmentDTO search(String id) throws SQLException;
    public List<EquipmentDTO> getAllEquipment() throws SQLException;
}
