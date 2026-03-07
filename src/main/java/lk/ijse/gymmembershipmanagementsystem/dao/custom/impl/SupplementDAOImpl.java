package lk.ijse.gymmembershipmanagementsystem.dao.custom.impl;

import lk.ijse.gymmembershipmanagementsystem.dao.CrudUtil;
import lk.ijse.gymmembershipmanagementsystem.dao.custom.SupplementDAO;
import lk.ijse.gymmembershipmanagementsystem.entity.Supplement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SupplementDAOImpl implements SupplementDAO {
    @Override
    public boolean save(Supplement entity) throws SQLException {
        return CrudUtil.execute(
                "INSERT INTO supplement (name, qty, unit_price) VALUES (?,?,?)",
                entity.getName(), entity.getQty(), entity.getUnitPrice());
    }

    @Override
    public boolean update(Supplement entity) throws SQLException {
        return CrudUtil.execute(
                "UPDATE supplement SET name=?, qty=?, unit_price=? WHERE id=?",
                entity.getName(), entity.getQty(), entity.getUnitPrice(), entity.getId());
    }

    @Override
    public boolean delete(String id) throws SQLException {
        return CrudUtil.execute("DELETE FROM supplement WHERE id=?", id);
    }

    @Override
    public Supplement search(String id) throws SQLException {
        ResultSet rst = CrudUtil.execute("SELECT * FROM supplement WHERE id=?", id);
        if (rst.next()) {
            return new Supplement(
                    rst.getInt("id"),
                    rst.getString("name"),
                    rst.getInt("qty"),
                    rst.getDouble("unit_price")
            );
        }
        return null;
    }

    @Override
    public List<Supplement> getAll() throws SQLException {
        List<Supplement> list = new ArrayList<>();
        ResultSet rst = CrudUtil.execute("SELECT * FROM supplement ORDER BY id DESC");
        while (rst.next()) {
            list.add(new Supplement(
                    rst.getInt("id"),
                    rst.getString("name"),
                    rst.getInt("qty"),
                    rst.getDouble("unit_price")
            ));
        }
        return list;
    }

    @Override
    public boolean decreaseQty(int supplementId, int qty) throws SQLException {
        return CrudUtil.execute(
                "UPDATE supplement SET qty = qty - ? WHERE id = ?",
                qty, supplementId);
    }
}
