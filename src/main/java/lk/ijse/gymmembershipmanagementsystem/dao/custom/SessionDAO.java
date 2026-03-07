package lk.ijse.gymmembershipmanagementsystem.dao.custom;

import lk.ijse.gymmembershipmanagementsystem.dao.CrudDAO;
import lk.ijse.gymmembershipmanagementsystem.entity.Session;

public interface SessionDAO extends CrudDAO<Session> {
    int getLastSessionId() throws Exception;
}