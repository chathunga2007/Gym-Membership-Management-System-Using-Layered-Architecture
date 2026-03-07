package lk.ijse.gymmembershipmanagementsystem.bo.custom.impl;

import lk.ijse.gymmembershipmanagementsystem.bo.custom.SupplementBO;
import lk.ijse.gymmembershipmanagementsystem.dao.DAOFactory;
import lk.ijse.gymmembershipmanagementsystem.dao.custom.SupplementDAO;
import lk.ijse.gymmembershipmanagementsystem.dto.SupplementDTO;
import lk.ijse.gymmembershipmanagementsystem.entity.Supplement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SupplementBOImpl implements SupplementBO {

    SupplementDAO supplementDAO = (SupplementDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.SUPPLEMENT);

    @Override
    public boolean saveSupplement(SupplementDTO dto) throws SQLException, ClassNotFoundException {
        return supplementDAO.save(new Supplement(dto.getName(), dto.getQty(), dto.getUnitPrice()));
    }

    @Override
    public boolean updateSupplement(SupplementDTO dto) throws SQLException, ClassNotFoundException {
        return supplementDAO.update(new Supplement(dto.getId(), dto.getName(), dto.getQty(), dto.getUnitPrice()));
    }

    @Override
    public boolean deleteSupplement(String id) throws SQLException, ClassNotFoundException {
        return supplementDAO.delete(id);
    }

    @Override
    public SupplementDTO searchSupplement(String id) throws SQLException, ClassNotFoundException {
        Supplement s = supplementDAO.search(id);
        return s == null ? null : new SupplementDTO(s.getId(), s.getName(), s.getQty(), s.getUnitPrice());
    }

    @Override
    public List<SupplementDTO> getAllSupplements() throws SQLException, ClassNotFoundException {
        List<Supplement> entities = supplementDAO.getAll();
        List<SupplementDTO> dtos = new ArrayList<>();
        for (Supplement s : entities) {
            dtos.add(new SupplementDTO(s.getId(), s.getName(), s.getQty(), s.getUnitPrice()));
        }
        return dtos;
    }

    @Override
    public boolean decreaseQty(int supplementId, int qty) throws Exception {
        return supplementDAO.decreaseQty(supplementId, qty);
    }
}