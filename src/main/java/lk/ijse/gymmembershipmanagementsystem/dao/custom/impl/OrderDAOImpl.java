package lk.ijse.gymmembershipmanagementsystem.dao.custom.impl;

import lk.ijse.gymmembershipmanagementsystem.dao.CrudUtil;
import lk.ijse.gymmembershipmanagementsystem.dao.custom.OrderDAO;
import lk.ijse.gymmembershipmanagementsystem.dao.custom.OrderSupplementDAO;
import lk.ijse.gymmembershipmanagementsystem.db.DBConnection;
import lk.ijse.gymmembershipmanagementsystem.dto.OrderDTO;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

public class OrderDAOImpl implements OrderDAO {
    OrderSupplementDAO  orderSupplementDAO = new OrderSupplementDAOImpl();
    @Override
    public boolean placeOrder(OrderDTO orderDTO) throws Exception {
        Connection conn = DBConnection.getInstance().getConnection();

        try {
            conn.setAutoCommit(false);

            boolean isSavedOrder = CrudUtil.execute(
                    "INSERT INTO orders (date, memberID) VALUES (?,?)",
                    orderDTO.getOrderDate(),
                    orderDTO.getMemberId()
            );

            if (isSavedOrder) {
                ResultSet rs = CrudUtil.execute("SELECT id FROM orders ORDER BY id DESC LIMIT 1");
                if (rs.next()) {
                    int latestOrderId = rs.getInt("id");

                    orderSupplementDAO.saveOrderSupplement(latestOrderId, orderDTO.getOrderSupplements());
                    printOrderInvoice(latestOrderId);

                } else {
                    throw new Exception("Something went wrong when finding order id");
                }

            } else {
                throw new Exception("Something went wrong when inserting order");
            }

            conn.commit();
            return true;

        } catch (Exception e) {
            conn.rollback();
            throw e;
        } finally {
            conn.setAutoCommit(true);
        }
    }

    @Override
    public void printOrderInvoice(int orderId) throws Exception {
        Connection conn = DBConnection.getInstance().getConnection();

        InputStream reportObject = getClass().getResourceAsStream("/lk/ijse/GymMembershipManagementSystem/reports/invoice.jrxml");

        JasperReport jasperReport = JasperCompileManager.compileReport(reportObject);

        Map<String,Object> parameters = new HashMap<>();
        parameters.put("ORDER_ID", orderId);

        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, conn);

        JasperViewer.viewReport(jasperPrint, false);
    }
}
