package lk.ijse.gymmembershipmanagementsystem.bo.custom.impl;

import javafx.collections.ObservableList;
import lk.ijse.gymmembershipmanagementsystem.bo.custom.MembershipBO;
import lk.ijse.gymmembershipmanagementsystem.dao.DAOFactory;
import lk.ijse.gymmembershipmanagementsystem.dao.custom.MemberDAO;
import lk.ijse.gymmembershipmanagementsystem.dao.custom.MembershipDAO;
import lk.ijse.gymmembershipmanagementsystem.dao.custom.QueryDAO;
import lk.ijse.gymmembershipmanagementsystem.dto.MemberDTO;
import lk.ijse.gymmembershipmanagementsystem.dto.MembershipDTO;
import lk.ijse.gymmembershipmanagementsystem.entity.Membership;
import java.sql.SQLException;
import java.util.List;

public class MembershipBOImpl implements MembershipBO {
    MembershipDAO membershipDAO = (MembershipDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.MEMBERSHIP);
    QueryDAO queryDAO = (QueryDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.QUERY);
    MemberDAO memberDAO = (MemberDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.MEMBER);

    @Override
    public boolean saveMembership(MembershipDTO dto) throws SQLException, ClassNotFoundException {
        return membershipDAO.save(new Membership(dto.getMemberId(), dto.getMembershipType(), dto.getIssuedDate(), dto.getExpiryDate()));
    }

    @Override
    public boolean updateMembership(MembershipDTO dto) throws SQLException, ClassNotFoundException {
        return membershipDAO.update(new Membership(dto.getMembershipId(), dto.getMemberId(), dto.getMembershipType(), dto.getIssuedDate(), dto.getExpiryDate()));
    }

    @Override
    public boolean deleteMembership(String id) throws SQLException, ClassNotFoundException {
        return membershipDAO.delete(id);
    }

    @Override
    public MembershipDTO searchMembership(String id) throws SQLException, ClassNotFoundException {
        Membership m = membershipDAO.search(id);
        return m == null ? null : new MembershipDTO(m.getMembershipId(), m.getMemberId(), m.getMembershipType(), m.getIssuedDate(), m.getExpiryDate());
    }

    @Override
    public List<MembershipDTO> getAllMembership() throws SQLException {
        return queryDAO.getAllMembershipWithMemberName();   // JOIN query → QueryDAO
    }

    @Override
    public ObservableList<MemberDTO> loadMemberID()throws SQLException {
        return memberDAO.loadMemberID();
    }
}
