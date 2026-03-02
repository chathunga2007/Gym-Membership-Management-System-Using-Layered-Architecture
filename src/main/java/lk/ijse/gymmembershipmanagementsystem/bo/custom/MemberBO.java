package lk.ijse.gymmembershipmanagementsystem.bo.custom;

import lk.ijse.gymmembershipmanagementsystem.dto.MemberDTO;
import java.sql.SQLException;
import java.util.List;

public interface MemberBO {
    public List<MemberDTO> getAllMember() throws SQLException, ClassNotFoundException;

    public boolean saveMember(MemberDTO dto) throws SQLException, ClassNotFoundException;

    public boolean updateMember(MemberDTO dto) throws SQLException, ClassNotFoundException;

    public boolean deleteMember(String id) throws SQLException, ClassNotFoundException;

    public MemberDTO searchMember(String id) throws SQLException, ClassNotFoundException;
}
