package lk.ijse.gymmembershipmanagementsystem.dao.custom;

import lk.ijse.gymmembershipmanagementsystem.dto.MemberDTO;
import java.sql.SQLException;
import java.util.List;

public interface MemberDAO {
    public boolean save(MemberDTO memDTO) throws SQLException;
    public boolean update(MemberDTO memDTO) throws SQLException;
    public boolean delete(String id) throws SQLException;
    public MemberDTO search(String id) throws SQLException;
    public List<MemberDTO> getAllMember() throws SQLException;
}
