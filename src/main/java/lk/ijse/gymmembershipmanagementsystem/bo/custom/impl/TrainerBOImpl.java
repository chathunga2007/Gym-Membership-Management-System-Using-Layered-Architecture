package lk.ijse.gymmembershipmanagementsystem.bo.custom.impl;

import javafx.collections.ObservableList;
import lk.ijse.gymmembershipmanagementsystem.bo.custom.TrainerBO;
import lk.ijse.gymmembershipmanagementsystem.dao.DAOFactory;
import lk.ijse.gymmembershipmanagementsystem.dao.custom.TrainerDAO;
import lk.ijse.gymmembershipmanagementsystem.dto.TrainerDTO;
import lk.ijse.gymmembershipmanagementsystem.entity.Trainer;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TrainerBOImpl implements TrainerBO {
    TrainerDAO trainerDAO = (TrainerDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.TRAINER);

    @Override
    public boolean saveTrainer(TrainerDTO dto) throws SQLException, ClassNotFoundException {
        return trainerDAO.save(new Trainer(dto.getName(), dto.getAge(), dto.getNic(), dto.getContact(), dto.getEmail()));
    }

    @Override
    public boolean updateTrainer(TrainerDTO dto) throws SQLException, ClassNotFoundException {
        return trainerDAO.update(new Trainer(dto.getTrainerId(), dto.getName(), dto.getAge(), dto.getNic(), dto.getContact(), dto.getEmail()));
    }

    @Override
    public boolean deleteTrainer(String id) throws SQLException, ClassNotFoundException {
        return trainerDAO.delete(id);
    }

    @Override
    public TrainerDTO searchTrainer(String id) throws SQLException, ClassNotFoundException {
        Trainer t = trainerDAO.search(id);
        return t == null ? null : new TrainerDTO(t.getTrainerId(), t.getName(), t.getAge(), t.getNic(), t.getContact(), t.getEmail());
    }

    @Override
    public List<TrainerDTO> getAllTrainers() throws SQLException, ClassNotFoundException {
        List<Trainer> entities = trainerDAO.getAll();
        List<TrainerDTO> dtos = new ArrayList<>();
        for (Trainer t : entities) {
            dtos.add(new TrainerDTO(t.getTrainerId(), t.getName(), t.getAge(), t.getNic(), t.getContact(), t.getEmail()));
        }
        return dtos;
    }

    @Override
    public int getTotalTrainers() throws Exception {
        return trainerDAO.getTotalTrainers();
    }

    @Override
    public ObservableList<TrainerDTO> loadTrainerIDs() throws SQLException {
        return trainerDAO.loadTrainerIDs();
    }
}
