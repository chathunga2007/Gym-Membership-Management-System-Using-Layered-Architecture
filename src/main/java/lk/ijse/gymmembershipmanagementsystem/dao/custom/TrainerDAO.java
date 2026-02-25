package lk.ijse.gymmembershipmanagementsystem.dao.custom;

import lk.ijse.gymmembershipmanagementsystem.dto.TrainerDTO;

import java.sql.SQLException;
import java.util.List;

public interface TrainerDAO {
    public boolean save(TrainerDTO trainerDTO) throws SQLException;
    public boolean update(TrainerDTO trainerDTO) throws SQLException;
    public boolean delete(String id) throws SQLException;
    public TrainerDTO search(String id) throws SQLException;
    public List<TrainerDTO> getAllTrainer() throws SQLException;
}
