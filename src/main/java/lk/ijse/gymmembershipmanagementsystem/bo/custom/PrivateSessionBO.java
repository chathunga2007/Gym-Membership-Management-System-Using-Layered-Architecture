package lk.ijse.gymmembershipmanagementsystem.bo.custom;

import javafx.collections.ObservableList;
import lk.ijse.gymmembershipmanagementsystem.bo.SuperBO;
import lk.ijse.gymmembershipmanagementsystem.dto.MemberDTO;
import lk.ijse.gymmembershipmanagementsystem.dto.PaymentDTO;
import lk.ijse.gymmembershipmanagementsystem.dto.PrivateSessionDTO;
import java.sql.SQLException;
import java.util.List;

public interface PrivateSessionBO extends SuperBO {
    boolean saveWithPayment(PrivateSessionDTO sessionDTO, PaymentDTO paymentDTO) throws Exception;
    boolean updatePrivateSession(PrivateSessionDTO dto) throws SQLException, ClassNotFoundException;
    boolean deletePrivateSession(String id) throws SQLException, ClassNotFoundException;
    PrivateSessionDTO searchPrivateSession(String id) throws SQLException, ClassNotFoundException;
    List<PrivateSessionDTO> getAllPrivateSessions() throws SQLException;
    ObservableList<MemberDTO> loadMemberIDs() throws SQLException;
}