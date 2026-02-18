package lk.ijse.gymmembershipmanagementsystem.dao.custom;

public interface UserDAO {
    public boolean checkLogin(String username, String password) throws Exception;
    public boolean emailExists(String email) throws Exception;
    public boolean updatePassword(String email, String newPassword) throws Exception;
}
