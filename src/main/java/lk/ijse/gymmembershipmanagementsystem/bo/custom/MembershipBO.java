package lk.ijse.gymmembershipmanagementsystem.bo.custom;

import javafx.collections.ObservableList;
import lk.ijse.gymmembershipmanagementsystem.bo.SuperBO;
import lk.ijse.gymmembershipmanagementsystem.dto.MemberDTO;
import lk.ijse.gymmembershipmanagementsystem.dto.MembershipDTO;
import java.sql.SQLException;
import java.util.List;

public interface MembershipBO extends SuperBO {
    boolean saveMembership(MembershipDTO dto) throws SQLException, ClassNotFoundException;
    boolean updateMembership(MembershipDTO dto) throws SQLException, ClassNotFoundException;
    boolean deleteMembership(String id) throws SQLException, ClassNotFoundException;
    MembershipDTO searchMembership(String id) throws SQLException, ClassNotFoundException;
    List<MembershipDTO> getAllMembership() throws SQLException;
    public ObservableList<MemberDTO> loadMemberID()throws SQLException;
}
