package lk.ijse.gymmembershipmanagementsystem.bo.custom.impl;

import lk.ijse.gymmembershipmanagementsystem.bo.custom.OrderBO;
import lk.ijse.gymmembershipmanagementsystem.dao.DAOFactory;
import lk.ijse.gymmembershipmanagementsystem.dao.custom.OrderDAO;
import lk.ijse.gymmembershipmanagementsystem.dao.custom.OrderSupplementDAO;
import lk.ijse.gymmembershipmanagementsystem.dao.custom.SupplementDAO;
import lk.ijse.gymmembershipmanagementsystem.db.DBConnection;
import lk.ijse.gymmembershipmanagementsystem.dto.OrderDTO;
import lk.ijse.gymmembershipmanagementsystem.dto.OrderSupplementDTO;
import lk.ijse.gymmembershipmanagementsystem.entity.Order;
import lk.ijse.gymmembershipmanagementsystem.entity.OrderSupplement;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer;
import java.io.InputStream;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

public class OrderBOImpl implements OrderBO {

    OrderDAO orderDAO = (OrderDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.ORDER);
    OrderSupplementDAO orderSupplementDAO = (OrderSupplementDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.ORDER_SUPPLEMENT);
    SupplementDAO supplementDAO = (SupplementDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.SUPPLEMENT);

    @Override
    public boolean placeOrder(OrderDTO dto) throws Exception {
        Connection connection = DBConnection.getInstance().getConnection();
        try {
            connection.setAutoCommit(false);

            boolean orderSaved = orderDAO.save(new Order(dto.getMemberId(), dto.getOrderDate()));
            if (!orderSaved) throw new Exception("Order save failed");

            int orderId = orderDAO.getLastOrderId();
            if (orderId == -1) throw new Exception("Failed to get order ID");

            for (OrderSupplementDTO detail : dto.getOrderSupplements()) {
                boolean itemSaved = orderSupplementDAO.save(
                        new OrderSupplement(orderId, detail.getSupplementId(), detail.getQty(), detail.getPrice())
                );
                if (!itemSaved) throw new Exception("Order item save failed");

                boolean qtyDecreased = supplementDAO.decreaseQty(detail.getSupplementId(), detail.getQty());
                if (!qtyDecreased) throw new Exception("Qty decrease failed");
            }

            printOrderInvoice(orderId);

            connection.commit();
            return true;

        } catch (Exception e) {
            connection.rollback();
            throw e;
        } finally {
            connection.setAutoCommit(true);
        }
    }

    private void printOrderInvoice(int orderId) throws Exception {
        Connection conn = DBConnection.getInstance().getConnection();
        InputStream report = getClass().getResourceAsStream("/lk/ijse/gymmembershipmanagementsystem/reports/invoice.jrxml");

        JasperReport jasperReport = JasperCompileManager.compileReport(report);
        Map<String, Object> params = new HashMap<>();
        params.put("ORDER_ID", orderId);

        JasperPrint print = JasperFillManager.fillReport(jasperReport, params, conn);
        JasperViewer.viewReport(print, false);
    }
}