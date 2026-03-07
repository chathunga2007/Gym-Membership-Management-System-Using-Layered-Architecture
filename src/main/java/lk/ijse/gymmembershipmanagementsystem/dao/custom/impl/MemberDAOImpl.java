package lk.ijse.gymmembershipmanagementsystem.dao.custom.impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.gymmembershipmanagementsystem.dao.CrudUtil;
import lk.ijse.gymmembershipmanagementsystem.dao.custom.MemberDAO;
import lk.ijse.gymmembershipmanagementsystem.dto.MemberDTO;
import lk.ijse.gymmembershipmanagementsystem.entity.Member;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MemberDAOImpl implements MemberDAO {
    @Override
    public boolean save(Member entity) throws SQLException {
        return CrudUtil.execute(
                "INSERT INTO Member (name, dob, age, email, nic, contact, joinedDate, status) VALUES (?,?,?,?,?,?,?,?)",
                entity.getName(), entity.getDob(), entity.getAge(), entity.getEmail(),
                entity.getNic(), entity.getContact(), entity.getJoinedDate(), entity.getStatus()
        );
    }

    @Override
    public boolean update(Member entity) throws SQLException {
        return CrudUtil.execute(
                "UPDATE Member SET name=?, dob=?, age=?, email=?, nic=?, contact=?, joinedDate=?, status=? WHERE MemberID=?",
                entity.getName(), entity.getDob(), entity.getAge(), entity.getEmail(),
                entity.getNic(), entity.getContact(), entity.getJoinedDate(), entity.getStatus(),
                entity.getMemberID()
        );
    }

    @Override
    public boolean delete(String id) throws SQLException {
        return CrudUtil.execute("DELETE FROM Member WHERE MemberID=?", id);
    }

    @Override
    public Member search(String id) throws SQLException {
        ResultSet rst = CrudUtil.execute("SELECT * FROM Member WHERE MemberID=?", id);
        if (rst.next()) {
            return new Member(
                    rst.getInt("MemberID"),
                    rst.getString("name"),
                    rst.getDate("dob").toLocalDate(),
                    rst.getInt("age"),
                    rst.getString("email"),
                    rst.getString("nic"),
                    rst.getString("contact"),
                    rst.getDate("joinedDate").toLocalDate(),
                    rst.getString("status")
            );
        }
        return null;
    }

    @Override
    public List<Member> getAll() throws SQLException {
        List<Member> list = new ArrayList<>();
        ResultSet rst = CrudUtil.execute("SELECT * FROM Member ORDER BY MemberID DESC");
        while (rst.next()) {
            list.add(new Member(
                    rst.getInt("MemberID"),
                    rst.getString("name"),
                    rst.getDate("dob").toLocalDate(),
                    rst.getInt("age"),
                    rst.getString("email"),
                    rst.getString("nic"),
                    rst.getString("contact"),
                    rst.getDate("joinedDate").toLocalDate(),
                    rst.getString("status")
            ));
        }
        return list;
    }

    @Override
    public ObservableList<MemberDTO> loadMemberID() throws SQLException {
        ResultSet rs = CrudUtil.execute("SELECT memberID, name FROM Member");

        ObservableList<MemberDTO> member = FXCollections.observableArrayList();

        while (rs.next()) {
            member.add(new MemberDTO(
                    rs.getInt("memberID"),
                    rs.getString("name")
            ));
        }
        return member;
    }

    @Override
    public int getTotalActiveMembers() throws SQLException {
        ResultSet rs = CrudUtil.execute("SELECT COUNT(*) FROM Member WHERE status='ACTIVE'");
        return rs.next() ? rs.getInt(1) : 0;
    }
}
