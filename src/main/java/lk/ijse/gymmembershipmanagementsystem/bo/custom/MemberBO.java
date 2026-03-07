package lk.ijse.gymmembershipmanagementsystem.bo.custom;

import javafx.collections.ObservableList;
import lk.ijse.gymmembershipmanagementsystem.bo.SuperBO;
import lk.ijse.gymmembershipmanagementsystem.dto.MemberDTO;
import java.sql.SQLException;
import java.util.List;

public interface MemberBO extends SuperBO {
    boolean saveMember(MemberDTO dto) throws SQLException, ClassNotFoundException;
    boolean updateMember(MemberDTO dto) throws SQLException, ClassNotFoundException;
    boolean deleteMember(String id) throws SQLException, ClassNotFoundException;
    MemberDTO searchMember(String id) throws SQLException, ClassNotFoundException;
    List<MemberDTO> getAllMembers() throws SQLException, ClassNotFoundException;
    public ObservableList<MemberDTO> loadMemberID()throws SQLException;
}
