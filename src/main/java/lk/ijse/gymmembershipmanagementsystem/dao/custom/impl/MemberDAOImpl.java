package lk.ijse.gymmembershipmanagementsystem.dao.custom.impl;

import lk.ijse.gymmembershipmanagementsystem.dao.CrudUtil;
import lk.ijse.gymmembershipmanagementsystem.dao.custom.MemberDAO;
import lk.ijse.gymmembershipmanagementsystem.dto.MemberDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MemberDAOImpl implements MemberDAO {
    @Override
    public boolean save(MemberDTO memDTO) throws SQLException {
        boolean result = CrudUtil.execute(
                "INSERT INTO Member (name, dob, age, email, nic, contact, joinedDate, status) VALUES (?,?, ?, ?, ? ,? ,?, ?)",
                memDTO.getName(),
                memDTO.getDob(),
                memDTO.getAge(),
                memDTO.getEmail(),
                memDTO.getNic(),
                memDTO.getContact(),
                memDTO.getJoinedDate(),
                memDTO.getStatus()
        );
        return result;
    }

    @Override
    public boolean update(MemberDTO memDTO) throws SQLException {
        boolean result = CrudUtil.execute("UPDATE Member SET name = ?, dob = ?, age = ?, email =?, nic = ?, contact = ?, joinedDate = ?, status = ? WHERE MemberID = ?",
                memDTO.getName(),
                memDTO.getDob(),
                memDTO.getAge(),
                memDTO.getEmail(),
                memDTO.getNic(),
                memDTO.getContact(),
                memDTO.getJoinedDate(),
                memDTO.getStatus(),
                memDTO.getMemberID()
        );

        return result;
    }

    @Override
    public boolean delete(String id) throws SQLException {
        boolean result = CrudUtil.execute("DELETE FROM Member WHERE MemberID = ?", id);

        return result;
    }

    @Override
    public MemberDTO search(String id) throws SQLException {
        ResultSet result = CrudUtil.execute("SELECT * FROM Member WHERE memberID = ?", id);

        if (result.next()) {
            int memID = result.getInt("memberID");
            String name = result.getString("name");
            LocalDate dob = LocalDate.parse(result.getString("dob"));
            int age = result.getInt("age");
            String email = result.getString("email");
            String nic = result.getString("nic");
            String contact = result.getString("contact");
            LocalDate joinedDate = LocalDate.parse(result.getString("joinedDate"));
            String status = result.getString("status");
            return new MemberDTO(memID, name, dob, age, email, nic, contact, joinedDate, status);
        }
        return null;
    }

    @Override
    public List<MemberDTO> getAllMember() throws SQLException {
        List<MemberDTO> memberList = new ArrayList();

        ResultSet  result = CrudUtil.execute("SELECT * FROM Member ORDER BY memberID DESC");

        while(result.next()) {
            int memID = result.getInt("memberID");
            String name = result.getString("name");
            LocalDate  dob = LocalDate.parse(result.getString("dob"));
            int age = result.getInt("age");
            String email = result.getString("email");
            String nic = result.getString("nic");
            String contact = result.getString("contact");
            LocalDate joinedDate = LocalDate.parse(result.getString("joinedDate"));
            String status = result.getString("status");

            MemberDTO memberDTO = new MemberDTO(memID, name, dob, age, email, nic, contact, joinedDate, status);

            memberList.add(memberDTO);
        }
        return memberList;
    }
}
