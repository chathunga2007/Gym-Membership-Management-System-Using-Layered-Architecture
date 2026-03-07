package lk.ijse.gymmembershipmanagementsystem.dao.custom.impl;

import lk.ijse.gymmembershipmanagementsystem.dao.CrudUtil;
import lk.ijse.gymmembershipmanagementsystem.dao.custom.EquipmentDAO;
import lk.ijse.gymmembershipmanagementsystem.entity.Equipment;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EquipmentDAOImpl implements EquipmentDAO {

    @Override
    public boolean save(Equipment entity) throws SQLException {
        return CrudUtil.execute("INSERT INTO Equipments (name, qty, availability) VALUES (?,?,?)",
                entity.getName(), entity.getQty(), entity.getAvailability());
    }

    @Override
    public boolean update(Equipment entity) throws SQLException {
        return CrudUtil.execute("UPDATE Equipments SET name=?, qty=?, availability=? WHERE equipmentsId=?",
                entity.getName(), entity.getQty(), entity.getAvailability(), entity.getEquipmenstId());
    }

    @Override
    public boolean delete(String id) throws SQLException {
        return CrudUtil.execute("DELETE FROM Equipments WHERE equipmentsId=?", id);
    }

    @Override
    public Equipment search(String id) throws SQLException {
        ResultSet rst = CrudUtil.execute("SELECT * FROM Equipments WHERE equipmentsId=?", id);
        if (rst.next()) {
            return new Equipment(
                    rst.getInt("equipmentsId"),
                    rst.getString("name"),
                    rst.getInt("qty"),
                    rst.getString("availability")
            );
        }
        return null;
    }

    @Override
    public List<Equipment> getAll() throws SQLException {
        List<Equipment> list = new ArrayList<>();
        ResultSet rst = CrudUtil.execute("SELECT * FROM Equipments ORDER BY equipmentsId DESC");
        while (rst.next()) {
            list.add(new Equipment(
                    rst.getInt("equipmentsId"),
                    rst.getString("name"),
                    rst.getInt("qty"),
                    rst.getString("availability")
            ));
        }
        return list;
    }

    @Override
    public boolean increaseQty(int equipmentId, int qty) throws SQLException {
        return CrudUtil.execute(
                "UPDATE Equipments SET qty = qty + ? WHERE equipmentsId = ?",
                qty, equipmentId
        );
    }

    @Override
    public boolean decreaseQty(int equipmentId, int qty) throws SQLException {
        return CrudUtil.execute(
                "UPDATE Equipments SET qty = qty - ? WHERE equipmentsId = ?",
                qty, equipmentId
        );
    }
}