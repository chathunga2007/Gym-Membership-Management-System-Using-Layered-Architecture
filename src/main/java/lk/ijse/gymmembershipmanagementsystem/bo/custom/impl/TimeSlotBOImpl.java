package lk.ijse.gymmembershipmanagementsystem.bo.custom.impl;

import javafx.collections.ObservableList;
import lk.ijse.gymmembershipmanagementsystem.bo.custom.TimeSlotBO;
import lk.ijse.gymmembershipmanagementsystem.dao.DAOFactory;
import lk.ijse.gymmembershipmanagementsystem.dao.custom.TimeSlotDAO;
import lk.ijse.gymmembershipmanagementsystem.dto.TimeSlotDTO;
import lk.ijse.gymmembershipmanagementsystem.entity.TimeSlot;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TimeSlotBOImpl implements TimeSlotBO {

    TimeSlotDAO timeSlotDAO = (TimeSlotDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.TIME_SLOT);

    @Override
    public boolean saveTimeSlot(TimeSlotDTO dto) throws SQLException, ClassNotFoundException {
        return timeSlotDAO.save(new TimeSlot(dto.getDate(), dto.getTimeIn(), dto.getTimeOut()));
    }

    @Override
    public boolean updateTimeSlot(TimeSlotDTO dto) throws SQLException {
        try {
            return timeSlotDAO.update(new TimeSlot(dto.getSlotId(), dto.getDate(), dto.getTimeIn(), dto.getTimeOut()));
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean deleteTimeSlot(String id) throws SQLException, ClassNotFoundException {
        return timeSlotDAO.delete(id);
    }

    @Override
    public TimeSlotDTO searchTimeSlot(String id) throws SQLException, ClassNotFoundException {
        TimeSlot t = timeSlotDAO.search(id);
        return t == null ? null : new TimeSlotDTO(t.getSlotId(), t.getDate(), t.getTimeIn(), t.getTimeOut());
    }

    @Override
    public List<TimeSlotDTO> getAllTimeSlots() throws SQLException, ClassNotFoundException {
        List<TimeSlot> entities = timeSlotDAO.getAll();
        List<TimeSlotDTO> dtos = new ArrayList<>();
        for (TimeSlot t : entities) {
            dtos.add(new TimeSlotDTO(t.getSlotId(), t.getDate(), t.getTimeIn(), t.getTimeOut()));
        }
        return dtos;
    }

    @Override
    public ObservableList<TimeSlotDTO> loadTimeSlotIDs() throws SQLException {
        return timeSlotDAO.loadTimeSlotIDs();
    }
}
