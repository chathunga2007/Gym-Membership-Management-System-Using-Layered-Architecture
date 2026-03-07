package lk.ijse.gymmembershipmanagementsystem.bo.custom.impl;

import javafx.collections.ObservableList;
import lk.ijse.gymmembershipmanagementsystem.bo.custom.MemberBO;
import lk.ijse.gymmembershipmanagementsystem.dao.DAOFactory;
import lk.ijse.gymmembershipmanagementsystem.dao.custom.MemberDAO;
import lk.ijse.gymmembershipmanagementsystem.dto.MemberDTO;
import lk.ijse.gymmembershipmanagementsystem.entity.Member;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MemberBOImpl implements MemberBO {

    MemberDAO memberDAO = (MemberDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.MEMBER);

    @Override
    public boolean saveMember(MemberDTO dto) throws SQLException, ClassNotFoundException {
        return memberDAO.save(new Member(dto.getName(), dto.getDob(), dto.getAge(),
                dto.getEmail(), dto.getNic(), dto.getContact(), dto.getJoinedDate(), dto.getStatus()));
    }

    @Override
    public boolean updateMember(MemberDTO dto) throws SQLException, ClassNotFoundException {
        return memberDAO.update(new Member(dto.getMemberID(), dto.getName(), dto.getDob(),
                dto.getAge(), dto.getEmail(), dto.getNic(), dto.getContact(),
                dto.getJoinedDate(), dto.getStatus()));
    }

    @Override
    public boolean deleteMember(String id) throws SQLException, ClassNotFoundException {
        return memberDAO.delete(id);
    }

    @Override
    public MemberDTO searchMember(String id) throws SQLException, ClassNotFoundException {
        Member m = memberDAO.search(id);
        return m == null ? null : new MemberDTO(m.getMemberID(), m.getName(), m.getDob(),
                m.getAge(), m.getEmail(), m.getNic(), m.getContact(), m.getJoinedDate(), m.getStatus());
    }

    @Override
    public List<MemberDTO> getAllMembers() throws SQLException, ClassNotFoundException {
        List<Member> entities = memberDAO.getAll();
        List<MemberDTO> dtos = new ArrayList<>();
        for (Member m : entities) {
            dtos.add(new MemberDTO(m.getMemberID(), m.getName(), m.getDob(), m.getAge(),
                    m.getEmail(), m.getNic(), m.getContact(), m.getJoinedDate(), m.getStatus()));
        }
        return dtos;
    }

    @Override
    public ObservableList<MemberDTO> loadMemberID() throws SQLException {
        return memberDAO.loadMemberID();
    }
}
