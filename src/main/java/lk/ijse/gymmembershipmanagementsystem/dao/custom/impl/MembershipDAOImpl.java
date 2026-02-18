package lk.ijse.gymmembershipmanagementsystem.dao.custom.impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.gymmembershipmanagementsystem.dao.CrudUtil;
import lk.ijse.gymmembershipmanagementsystem.dao.custom.MembershipDAO;
import lk.ijse.gymmembershipmanagementsystem.dto.MemberDTO;
import lk.ijse.gymmembershipmanagementsystem.dto.MembershipDTO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MembershipDAOImpl implements MembershipDAO {
    @Override
    public boolean save(MembershipDTO membershipDTO) throws SQLException {
        boolean result = CrudUtil.execute(
                "INSERT INTO Membership (memberID, membershipType, issuedDate, expiryDate) VALUES (?,?,?,?)",
                membershipDTO.getMemberId(),
                membershipDTO.getMembershipType(),
                membershipDTO.getIssuedDate(),
                membershipDTO.getExpiryDate()
        );
        return result;
    }

    @Override
    public boolean update(MembershipDTO membershipDTO) throws SQLException {
        boolean result = CrudUtil.execute(
                "UPDATE Membership SET memberID = ?, membershipType = ?, issuedDate = ?, expiryDate = ? WHERE membershipID = ?",
                membershipDTO.getMemberId(),
                membershipDTO.getMembershipType(),
                membershipDTO.getIssuedDate(),
                membershipDTO.getExpiryDate(),
                membershipDTO.getMembershipId()
        );
        return result;
    }

    @Override
    public boolean delete(String id) throws SQLException {
        boolean result = CrudUtil.execute("DELETE FROM Membership WHERE membershipID = ?", id);

        return result;
    }

    @Override
    public MembershipDTO search(String id) throws SQLException {
        ResultSet result = CrudUtil.execute("SELECT * FROM Membership WHERE membershipID = ?", id);

        if (result.next()) {
            int membershipID = result.getInt("membershipID");
            String membershipType = result.getString("membershipType");
            LocalDate issuedDate = LocalDate.parse(result.getString("issuedDate"));
            LocalDate expiryDate = LocalDate.parse(result.getString("expiryDate"));
            int memID = result.getInt("memberID");
            return new MembershipDTO(membershipID, memID, membershipType, issuedDate, expiryDate);
        }
        return null;
    }

    @Override
    public ObservableList<MemberDTO> loadMemberID() throws SQLException {
        ObservableList<MemberDTO> memberDTOS = FXCollections.observableArrayList();

        String sql = "SELECT memberID, name FROM Member";

        ResultSet rs = CrudUtil.execute(sql);

        while (rs.next()) {
            memberDTOS.add(new MemberDTO(
                    rs.getInt("memberID"),
                    rs.getString("name")
            ));
        }

        return memberDTOS;
    }

    @Override
    public List<MembershipDTO> getAllMembership() throws SQLException {
        List<MembershipDTO> membershipList = new ArrayList<>();

        String sql = "SELECT mship.membershipID, mship.memberID, mem.name AS memberName, mship.membershipType, mship.issuedDate, mship.expiryDate FROM Membership mship JOIN Member mem ON mship.memberID = mem.memberID ORDER BY mship.membershipID DESC";

        ResultSet result = CrudUtil.execute(sql);

        while (result.next()) {

            int membershipID = result.getInt("membershipID");
            int memID = result.getInt("memberID");
            String membershipType = result.getString("membershipType");

            LocalDate issuedDate = result.getDate("issuedDate").toLocalDate();
            LocalDate expiryDate = result.getDate("expiryDate").toLocalDate();

            String memberName = result.getString("memberName");

            MembershipDTO membershipDTO = new MembershipDTO(
                    membershipID,
                    memID,
                    membershipType,
                    issuedDate,
                    expiryDate,
                    memberName
            );

            membershipList.add(membershipDTO);
        }

        return membershipList;
    }
}
