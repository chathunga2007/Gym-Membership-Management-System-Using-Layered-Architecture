package lk.ijse.gymmembershipmanagementsystem.bo.custom;

import lk.ijse.gymmembershipmanagementsystem.bo.SuperBO;
import lk.ijse.gymmembershipmanagementsystem.dto.EquipmentDTO;
import java.sql.SQLException;
import java.util.List;

public interface EquipmentBO extends SuperBO {
    boolean saveEquipment(EquipmentDTO dto) throws SQLException, ClassNotFoundException;
    boolean updateEquipment(EquipmentDTO dto) throws SQLException, ClassNotFoundException;
    boolean deleteEquipment(String id) throws SQLException, ClassNotFoundException;
    EquipmentDTO searchEquipment(String id) throws SQLException, ClassNotFoundException;
    List<EquipmentDTO> getAllEquipment() throws SQLException, ClassNotFoundException;
}