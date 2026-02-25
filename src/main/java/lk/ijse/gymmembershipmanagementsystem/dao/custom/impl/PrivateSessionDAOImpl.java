package lk.ijse.gymmembershipmanagementsystem.dao.custom.impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.gymmembershipmanagementsystem.dao.CrudUtil;
import lk.ijse.gymmembershipmanagementsystem.dao.custom.PrivateSessionDAO;
import lk.ijse.gymmembershipmanagementsystem.dto.MemberDTO;
import lk.ijse.gymmembershipmanagementsystem.dto.PrivateSessionDTO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PrivateSessionDAOImpl implements PrivateSessionDAO {
    @Override
    public boolean saveSession(PrivateSessionDTO sessionDTO) throws SQLException {
        boolean sessionSaved = CrudUtil.execute(
                "INSERT INTO Private_Session (memberID, timeIn, timeOut, extraCharges) VALUES (?, ?, ?, ?)",
                sessionDTO.getMemberId(),
                sessionDTO.getTimeIn(),
                sessionDTO.getTimeOut(),
                sessionDTO.getExtraCharges()
        );
        return sessionSaved;
    }

    @Override
    public boolean update(PrivateSessionDTO privateSessionDTO) throws SQLException {
        boolean result = CrudUtil.execute(
                "UPDATE Private_Session SET timeIn = ?, timeOut = ?, extraCharges = ?, memberID = ? WHERE privateSessionID = ?",
                privateSessionDTO.getTimeIn(),
                privateSessionDTO.getTimeOut(),
                privateSessionDTO.getExtraCharges(),
                privateSessionDTO.getMemberId(),
                privateSessionDTO.getPrivateSessionId()
        );
        return result;
    }

    @Override
    public boolean delete(String privateSessionId) throws SQLException {
        boolean result = CrudUtil.execute("DELETE FROM Private_Session WHERE privateSessionID = ?", privateSessionId);
        return result;
    }

    @Override
    public PrivateSessionDTO search(String id) throws SQLException {
        ResultSet rs = CrudUtil.execute("SELECT * FROM Private_Session WHERE privateSessionID = ?", id);

        if (rs.next()) {
            int privateSessionId = rs.getInt("privateSessionID");
            String timeIn = rs.getString("timeIn");
            String timeOut = rs.getString("timeOut");
            double extraCharges = rs.getDouble("extraCharges");
            int memberId = rs.getInt("memberID");
            return new PrivateSessionDTO(privateSessionId, memberId, timeIn, timeOut, extraCharges);
        }
        return  null;
    }

    @Override
    public List<PrivateSessionDTO> getAllPrivateSession() throws SQLException {
        List<PrivateSessionDTO> privateSessionList = new ArrayList();
        ResultSet  result = CrudUtil.execute("SELECT ps.privateSessionID,\n" +
                "               ps.memberID,\n" +
                "               m.name AS memberName,\n" +
                "               ps.timeIn,\n" +
                "               ps.timeOut,\n" +
                "               ps.extraCharges\n" +
                "        FROM Private_Session ps\n" +
                "        JOIN Member m ON ps.memberID = m.memberID\n" +
                "        ORDER BY ps.privateSessionID DESC");

        while(result.next()) {

            int privateSessionId = result.getInt("privateSessionID");
            String timeIn = result.getString("timeIn");
            String timeOut = result.getString("timeOut");
            double extraCharges = result.getDouble("extraCharges");
            int memberId = result.getInt("memberID");
            String memberName = result.getString("memberName");

            PrivateSessionDTO privateSessionDTO = new PrivateSessionDTO(privateSessionId, memberId, timeIn, timeOut, extraCharges, memberName);

            privateSessionList.add(privateSessionDTO);

        }
        return privateSessionList;
    }

    @Override
    public ObservableList<MemberDTO> loadMemberID() throws SQLException {
        ResultSet rs = CrudUtil.execute("SELECT memberID, name FROM Member");

        ObservableList<MemberDTO> memberDTOS = FXCollections.observableArrayList();

        while (rs.next()) {
            memberDTOS.add(new MemberDTO(
                    rs.getInt("memberID"),
                    rs.getString("name")
            ));
        }
        return memberDTOS;
    }
}
