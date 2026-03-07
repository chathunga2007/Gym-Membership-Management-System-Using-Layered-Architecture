package lk.ijse.gymmembershipmanagementsystem.bo.custom;

import javafx.collections.ObservableList;
import lk.ijse.gymmembershipmanagementsystem.bo.SuperBO;
import lk.ijse.gymmembershipmanagementsystem.dto.TrainerDTO;
import java.sql.SQLException;
import java.util.List;

public interface TrainerBO extends SuperBO {
    boolean saveTrainer(TrainerDTO dto) throws SQLException, ClassNotFoundException;
    boolean updateTrainer(TrainerDTO dto) throws SQLException, ClassNotFoundException;
    boolean deleteTrainer(String id) throws SQLException, ClassNotFoundException;
    TrainerDTO searchTrainer(String id) throws SQLException, ClassNotFoundException;
    List<TrainerDTO> getAllTrainers() throws SQLException, ClassNotFoundException;
    int getTotalTrainers() throws Exception;
    public ObservableList<TrainerDTO> loadTrainerIDs() throws SQLException;
}
