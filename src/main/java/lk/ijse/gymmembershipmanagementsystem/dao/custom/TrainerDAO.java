package lk.ijse.gymmembershipmanagementsystem.dao.custom;

import javafx.collections.ObservableList;
import lk.ijse.gymmembershipmanagementsystem.dao.CrudDAO;
import lk.ijse.gymmembershipmanagementsystem.dto.TrainerDTO;
import lk.ijse.gymmembershipmanagementsystem.entity.Trainer;

import java.sql.SQLException;

public interface TrainerDAO extends CrudDAO<Trainer> {
    int getTotalTrainers() throws Exception;
    ObservableList<TrainerDTO> loadTrainerIDs() throws SQLException;
}
