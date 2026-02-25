package lk.ijse.gymmembershipmanagementsystem.dao.custom;

public interface PasswordResetDAO {
    public void saveOTP(String email, String otp) throws Exception;
    public boolean verifyOTP(String email, String otp) throws Exception;
    public void clearOTP(String email) throws Exception;
}
