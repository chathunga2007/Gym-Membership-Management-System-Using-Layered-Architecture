package lk.ijse.gymmembershipmanagementsystem.dao.custom.impl;

import lk.ijse.gymmembershipmanagementsystem.dao.CrudUtil;
import lk.ijse.gymmembershipmanagementsystem.dao.custom.SupplementDAO;
import lk.ijse.gymmembershipmanagementsystem.dto.SupplementDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SupplementDAOImpl implements SupplementDAO {

    @Override
    public boolean save(SupplementDTO supplementDTO) throws SQLException {
        boolean result = CrudUtil.execute("INSERT INTO supplement (id, name, qty, unit_price) VALUES (?,?,?, ?)", supplementDTO.getId(), supplementDTO.getName(), supplementDTO.getQty(), supplementDTO.getUnitPrice());

        return result;
    }

    @Override
    public boolean update(SupplementDTO supplementDTO) throws SQLException {
        boolean result = CrudUtil.execute("UPDATE supplement SET name = ?, qty = ?, unit_price = ? WHERE id = ?", supplementDTO.getName(), supplementDTO.getQty(), supplementDTO.getId(), supplementDTO.getId());

        return result;
    }

    @Override
    public boolean delete(String id) throws SQLException {
        boolean result = CrudUtil.execute("DELETE FROM supplement WHERE id = ?", id);

        return result;
    }

    @Override
    public SupplementDTO search(String id) throws SQLException {
        ResultSet result = CrudUtil.execute("SELECT * FROM supplement WHERE id = ?", id);

        if (result.next()) {
            int itemId = result.getInt("id");
            String itemName = result.getString("name");
            int itemQty = result.getInt("qty");
            double unitPrice = result.getDouble("unit_price");
            return new SupplementDTO(itemId, itemName, itemQty, unitPrice);
        }
        return null;
    }

    @Override
    public List<SupplementDTO> getAllIds() throws SQLException {
        List<SupplementDTO> supplementDTOList = new ArrayList();

        ResultSet  rs = CrudUtil.execute("SELECT * FROM supplement ORDER BY id DESC");

        while(rs.next()) {
            int itemId = rs.getInt("id");
            String itemName = rs.getString("name");
            int itemQty = rs.getInt("qty");
            double unitPrice = rs.getDouble("unit_price");

            SupplementDTO supplementDTO = new SupplementDTO(itemId, itemName, itemQty, unitPrice);

            supplementDTOList.add(supplementDTO);
        }

        return supplementDTOList;
    }

    @Override
    public boolean decreaseSupplementQty(int supplementId, int qty) throws SQLException {
        boolean isUpdated = CrudUtil.execute("UPDATE supplement SET qty=qty-? WHERE id=?",
                qty,
                supplementId);

        return isUpdated;
    }
}
