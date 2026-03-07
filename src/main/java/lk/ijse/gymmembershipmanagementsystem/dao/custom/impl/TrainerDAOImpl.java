package lk.ijse.gymmembershipmanagementsystem.dao.custom.impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.gymmembershipmanagementsystem.dao.CrudUtil;
import lk.ijse.gymmembershipmanagementsystem.dao.custom.TrainerDAO;
import lk.ijse.gymmembershipmanagementsystem.dto.TrainerDTO;
import lk.ijse.gymmembershipmanagementsystem.entity.Trainer;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TrainerDAOImpl implements TrainerDAO {
    @Override
    public boolean save(Trainer entity) throws SQLException {
        return CrudUtil.execute(
                "INSERT INTO Trainer (name, age, nic, contact, email) VALUES (?,?,?,?,?)",
                entity.getName(), entity.getAge(), entity.getNic(), entity.getContact(), entity.getEmail());
    }

    @Override
    public boolean update(Trainer entity) throws SQLException {
        return CrudUtil.execute(
                "UPDATE Trainer SET name=?, age=?, nic=?, contact=?, email=? WHERE trainerID=?",
                entity.getName(), entity.getAge(), entity.getNic(), entity.getContact(), entity.getEmail(), entity.getTrainerId());
    }

    @Override
    public boolean delete(String id) throws SQLException {
        return CrudUtil.execute("DELETE FROM Trainer WHERE trainerID=?", id);
    }

    @Override
    public Trainer search(String id) throws SQLException {
        ResultSet rst = CrudUtil.execute("SELECT * FROM Trainer WHERE trainerID=?", id);
        if (rst.next()) {
            return new Trainer(
                    rst.getInt("trainerID"),
                    rst.getString("name"),
                    rst.getInt("age"),
                    rst.getString("nic"),
                    rst.getString("contact"),
                    rst.getString("email")
            );
        }
        return null;
    }

    @Override
    public List<Trainer> getAll() throws SQLException {
        List<Trainer> list = new ArrayList<>();
        ResultSet rst = CrudUtil.execute("SELECT * FROM Trainer ORDER BY trainerID DESC");
        while (rst.next()) {
            list.add(new Trainer(
                    rst.getInt("trainerID"),
                    rst.getString("name"),
                    rst.getInt("age"),
                    rst.getString("nic"),
                    rst.getString("contact"),
                    rst.getString("email")
            ));
        }
        return list;
    }

    @Override
    public int getTotalTrainers() throws SQLException {
        ResultSet rs = CrudUtil.execute("SELECT COUNT(*) FROM Trainer");
        return rs.next() ? rs.getInt(1) : 0;
    }

    @Override
    public ObservableList<TrainerDTO> loadTrainerIDs() throws SQLException {
        ObservableList<TrainerDTO> list = FXCollections.observableArrayList();
        ResultSet rs = CrudUtil.execute("SELECT trainerID, name FROM Trainer");
        while (rs.next()) {
            list.add(new TrainerDTO(
                    rs.getInt("trainerID"),
                    rs.getString("name")
            ));
        }
        return list;
    }
}
