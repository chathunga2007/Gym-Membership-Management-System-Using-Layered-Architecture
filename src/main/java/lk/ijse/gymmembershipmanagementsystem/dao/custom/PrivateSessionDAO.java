package lk.ijse.gymmembershipmanagementsystem.dao.custom;

import javafx.collections.ObservableList;
import lk.ijse.gymmembershipmanagementsystem.dto.MemberDTO;
import lk.ijse.gymmembershipmanagementsystem.dto.PrivateSessionDTO;
import java.sql.SQLException;
import java.util.List;

public interface PrivateSessionDAO {
    public boolean saveSession(PrivateSessionDTO sessionDTO) throws SQLException;
    public boolean update(PrivateSessionDTO privateSessionDTO) throws SQLException;
    public boolean delete(String privateSessionId) throws SQLException;
    public PrivateSessionDTO search(String id) throws SQLException;
    public List<PrivateSessionDTO> getAllPrivateSession() throws SQLException;
    public ObservableList<MemberDTO> loadMemberID()throws SQLException;
}
