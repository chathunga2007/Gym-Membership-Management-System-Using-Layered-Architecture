package lk.ijse.gymmembershipmanagementsystem.model;

import lk.ijse.gymmembershipmanagementsystem.dao.custom.OrderDAO;
import lk.ijse.gymmembershipmanagementsystem.dao.custom.OrderSupplementDAO;
import lk.ijse.gymmembershipmanagementsystem.dao.custom.impl.OrderDAOImpl;
import lk.ijse.gymmembershipmanagementsystem.dao.custom.impl.OrderSupplementDAOImpl;
import lk.ijse.gymmembershipmanagementsystem.db.DBConnection;
import lk.ijse.gymmembershipmanagementsystem.dto.OrderDTO;
import lk.ijse.gymmembershipmanagementsystem.dao.CrudUtil;
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

public class OrderModel {
    OrderSupplementDAO orderSupplementDAO = new OrderSupplementDAOImpl();
    OrderDAO orderDAO = new OrderDAOImpl();

    public boolean placeOrder(OrderDTO orderDTO) throws Exception {

        Connection conn = DBConnection.getInstance().getConnection();

        try {
            conn.setAutoCommit(false);

            boolean isSavedOrder = orderDAO.saveOrder(orderDTO);

            if (isSavedOrder) {
                int latestOrderId = orderDAO.getLatestOrderId();
                orderSupplementDAO.saveOrderSupplement(latestOrderId, orderDTO.getOrderSupplements());
                printOrderInvoice(latestOrderId);

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

    public void printOrderInvoice(int orderId) throws Exception {
        Connection conn = DBConnection.getInstance().getConnection();

        InputStream reportObject = getClass().getResourceAsStream("/lk/ijse/gymmembershipmanagementsystem/reports/invoice.jrxml");

        JasperReport jasperReport = JasperCompileManager.compileReport(reportObject);

        Map<String,Object> parameters = new HashMap<>();
        parameters.put("ORDER_ID", orderId);

        JasperPrint  jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, conn);

        JasperViewer.viewReport(jasperPrint, false);
    }
}
