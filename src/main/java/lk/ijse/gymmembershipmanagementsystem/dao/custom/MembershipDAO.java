package lk.ijse.gymmembershipmanagementsystem.dao.custom;

import javafx.collections.ObservableList;
import lk.ijse.gymmembershipmanagementsystem.dto.MemberDTO;
import lk.ijse.gymmembershipmanagementsystem.dto.MembershipDTO;
import java.sql.SQLException;
import java.util.List;

public interface MembershipDAO {
    public boolean save(MembershipDTO membershipDTO) throws SQLException;
    public boolean update(MembershipDTO membershipDTO) throws SQLException;
    public boolean delete(String id) throws SQLException;
    public MembershipDTO search(String id) throws SQLException;
    public ObservableList<MemberDTO> loadMemberID()throws SQLException;
    public List<MembershipDTO> getAllMembership() throws SQLException;
}
