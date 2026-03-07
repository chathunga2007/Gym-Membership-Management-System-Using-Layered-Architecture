package lk.ijse.gymmembershipmanagementsystem.dao.custom.impl;

import lk.ijse.gymmembershipmanagementsystem.dao.CrudUtil;
import lk.ijse.gymmembershipmanagementsystem.dao.custom.MembershipDAO;
import lk.ijse.gymmembershipmanagementsystem.entity.Membership;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MembershipDAOImpl implements MembershipDAO {

    @Override
    public boolean save(Membership entity) throws SQLException {
        return CrudUtil.execute(
                "INSERT INTO Membership (memberID, membershipType, issuedDate, expiryDate) VALUES (?,?,?,?)",
                entity.getMemberId(), entity.getMembershipType(), entity.getIssuedDate(), entity.getExpiryDate());
    }

    @Override
    public boolean update(Membership entity) throws SQLException {
        return CrudUtil.execute(
                "UPDATE Membership SET memberID=?, membershipType=?, issuedDate=?, expiryDate=? WHERE membershipID=?",
                entity.getMemberId(), entity.getMembershipType(), entity.getIssuedDate(), entity.getExpiryDate(), entity.getMembershipId());
    }

    @Override
    public boolean delete(String id) throws SQLException {
        return CrudUtil.execute("DELETE FROM Membership WHERE membershipID=?", id);
    }

    @Override
    public Membership search(String id) throws SQLException {
        ResultSet rst = CrudUtil.execute("SELECT * FROM Membership WHERE membershipID=?", id);
        if (rst.next()) {
            return new Membership(
                    rst.getInt("membershipID"),
                    rst.getInt("memberID"),
                    rst.getString("membershipType"),
                    rst.getDate("issuedDate").toLocalDate(),
                    rst.getDate("expiryDate").toLocalDate()
            );
        }
        return null;
    }

    @Override
    public List<Membership> getAll() throws SQLException {
        List<Membership> list = new ArrayList<>();
        ResultSet rst = CrudUtil.execute("SELECT * FROM Membership ORDER BY membershipID DESC");
        while (rst.next()) {
            list.add(new Membership(
                    rst.getInt("membershipID"),
                    rst.getInt("memberID"),
                    rst.getString("membershipType"),
                    rst.getDate("issuedDate").toLocalDate(),
                    rst.getDate("expiryDate").toLocalDate()
            ));
        }
        return list;
    }
}