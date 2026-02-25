package lk.ijse.gymmembershipmanagementsystem.dao.custom.impl;

import lk.ijse.gymmembershipmanagementsystem.dao.CrudUtil;
import lk.ijse.gymmembershipmanagementsystem.dao.custom.EquipmentDAO;
import lk.ijse.gymmembershipmanagementsystem.dto.EquipmentDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EquipmentDAOImpl implements EquipmentDAO {
    @Override
    public boolean save(EquipmentDTO equipmentDTO) throws SQLException {
        boolean result = CrudUtil.execute(
                "INSERT INTO Equipments (name, qty, availability) VALUES (?, ?, ?)",
                equipmentDTO.getName(),
                equipmentDTO.getQty(),
                equipmentDTO.getAvailability()
        );
        return result;
    }

    @Override
    public boolean update(EquipmentDTO equipmentDTO) throws SQLException {
        boolean result = CrudUtil.execute("UPDATE Equipments SET name = ?, qty = ?, availability = ? WHERE equipmentsId = ?",
                equipmentDTO.getName(),
                equipmentDTO.getQty(),
                equipmentDTO.getAvailability(),
                equipmentDTO.getEquipmentsId()
        );

        return result;
    }

    @Override
    public boolean delete(String id) throws SQLException {
        boolean result = CrudUtil.execute("DELETE FROM Equipments WHERE equipmentsId = ?", id);

        return result;
    }

    @Override
    public EquipmentDTO search(String id) throws SQLException {
        ResultSet result = CrudUtil.execute("SELECT * FROM Equipments WHERE equipmentsId = ?", id);

        if (result.next()) {
            int equipmentsID = result.getInt("equipmentsId");
            String name = result.getString("name");
            int  qty = result.getInt("qty");
            String availability = result.getString("availability");
            return new EquipmentDTO(equipmentsID, name, qty, availability);
        }
        return null;
    }

    @Override
    public List<EquipmentDTO> getAllEquipment() throws SQLException {
        List<EquipmentDTO> equipmentList = new ArrayList();

        ResultSet  result = CrudUtil.execute("SELECT * FROM Equipments ORDER BY equipmentsId DESC");

        while(result.next()) {
            int equipmentsID = result.getInt("equipmentsID");
            String name = result.getString("name");
            int qty = result.getInt("qty");
            String  availability = result.getString("availability");


            EquipmentDTO equipmentDTO = new EquipmentDTO(equipmentsID, name, qty, availability);

            equipmentList.add(equipmentDTO);
        }

        return equipmentList;
    }
}
