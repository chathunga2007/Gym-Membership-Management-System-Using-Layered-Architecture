package lk.ijse.gymmembershipmanagementsystem.bo.custom.impl;

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
    public List<MemberDTO> getAllMember() throws SQLException, ClassNotFoundException {
        List<Member> member = memberDAO.getAll();
        List<MemberDTO> memberDTOs = new ArrayList<>();
        for (Member m : member) {
            MemberDTO memberDTO = new MemberDTO(m.getMemberID(), m.getName(), m.getDob(), m.getAge(), m.getEmail(), m.getNic(), m.getContact(), m.getJoinedDate(), m.getStatus());
            memberDTOs.add(memberDTO);
        }
        return memberDTOs;
    }

    @Override
    public boolean saveMember(MemberDTO dto) throws SQLException, ClassNotFoundException {
        return memberDAO.save(new Member(dto.getMemberID(), dto.getName(), dto.getDob(), dto.getAge(), dto.getEmail(), dto.getNic(), dto.getContact(), dto.getJoinedDate(), dto.getStatus()));
    }

    @Override
    public boolean updateMember(MemberDTO dto) throws SQLException, ClassNotFoundException {
        return memberDAO.update(new Member(dto.getMemberID(), dto.getName(), dto.getDob(), dto.getAge(), dto.getEmail(), dto.getNic(), dto.getContact(), dto.getJoinedDate(), dto.getStatus()));
    }

    @Override
    public boolean deleteMember(String id) throws SQLException, ClassNotFoundException {
        return memberDAO.delete(id);
    }

    @Override
    public MemberDTO searchMember(String id) throws SQLException, ClassNotFoundException {
        return memberDAO.search(id);
    }
}
