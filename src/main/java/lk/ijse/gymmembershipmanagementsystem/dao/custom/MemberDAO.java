package lk.ijse.gymmembershipmanagementsystem.dao.custom;

import javafx.collections.ObservableList;
import lk.ijse.gymmembershipmanagementsystem.dao.CrudDAO;
import lk.ijse.gymmembershipmanagementsystem.dto.MemberDTO;
import lk.ijse.gymmembershipmanagementsystem.entity.Member;
import java.sql.SQLException;

public interface MemberDAO extends CrudDAO<Member> {
    public ObservableList<MemberDTO> loadMemberID()throws SQLException;
    int getTotalActiveMembers() throws SQLException;
}
