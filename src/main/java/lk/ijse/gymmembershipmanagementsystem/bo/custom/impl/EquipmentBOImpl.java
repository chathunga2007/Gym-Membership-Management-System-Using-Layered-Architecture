package lk.ijse.gymmembershipmanagementsystem.bo.custom.impl;

import lk.ijse.gymmembershipmanagementsystem.bo.custom.EquipmentBO;
import lk.ijse.gymmembershipmanagementsystem.dao.DAOFactory;
import lk.ijse.gymmembershipmanagementsystem.dao.custom.EquipmentDAO;
import lk.ijse.gymmembershipmanagementsystem.dto.EquipmentDTO;
import lk.ijse.gymmembershipmanagementsystem.entity.Equipment;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EquipmentBOImpl implements EquipmentBO {

    EquipmentDAO equipmentDAO = (EquipmentDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.Equipment);

    @Override
    public boolean saveEquipment(EquipmentDTO dto) throws SQLException, ClassNotFoundException {
        return equipmentDAO.save(new Equipment(dto.getName(), dto.getQty(), dto.getAvailability()));
    }

    @Override
    public boolean updateEquipment(EquipmentDTO dto) throws SQLException, ClassNotFoundException {
        return equipmentDAO.update(new Equipment(dto.getEquipmentsId(), dto.getName(), dto.getQty(), dto.getAvailability()));
    }

    @Override
    public boolean deleteEquipment(String id) throws SQLException, ClassNotFoundException {
        return equipmentDAO.delete(id);
    }

    @Override
    public EquipmentDTO searchEquipment(String id) throws SQLException, ClassNotFoundException {
        Equipment e = equipmentDAO.search(id);
        return e == null ? null : new EquipmentDTO(e.getEquipmenstId(), e.getName(), e.getQty(), e.getAvailability());
    }

    @Override
    public List<EquipmentDTO> getAllEquipment() throws SQLException, ClassNotFoundException {
        List<Equipment> entities = equipmentDAO.getAll();
        List<EquipmentDTO> dtos = new ArrayList<>();
        for (Equipment e : entities) {
            dtos.add(new EquipmentDTO(e.getEquipmenstId(), e.getName(), e.getQty(), e.getAvailability()));
        }
        return dtos;
    }
}