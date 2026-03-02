package lk.ijse.gymmembershipmanagementsystem.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.gymmembershipmanagementsystem.bo.BOFactory;
import lk.ijse.gymmembershipmanagementsystem.bo.custom.MemberBO;
import lk.ijse.gymmembershipmanagementsystem.bo.custom.impl.MemberBOImpl;
import lk.ijse.gymmembershipmanagementsystem.dao.custom.MemberDAO;
import lk.ijse.gymmembershipmanagementsystem.dao.custom.SupplementDAO;
import lk.ijse.gymmembershipmanagementsystem.dao.custom.impl.MemberDAOImpl;
import lk.ijse.gymmembershipmanagementsystem.dao.custom.impl.SupplementDAOImpl;
import lk.ijse.gymmembershipmanagementsystem.dto.MemberDTO;
import lk.ijse.gymmembershipmanagementsystem.dto.SupplementDTO;
import lk.ijse.gymmembershipmanagementsystem.dto.OrderDTO;
import lk.ijse.gymmembershipmanagementsystem.dto.OrderSupplementDTO;
import lk.ijse.gymmembershipmanagementsystem.dto.tm.OrderSupplementTM;
import lk.ijse.gymmembershipmanagementsystem.model.OrderModel;

public class OrderController implements Initializable {

    @FXML
    private ComboBox<String> comboMemberId;
    @FXML
    private ComboBox<String> comboSupplementId;
    @FXML
    private Label lblEmailValue;
    @FXML
    private Label lblMemberNameValue;
    @FXML
    private Label lblContactValue;
    @FXML
    private Label lblSupplementNameValue;
    @FXML
    private Label lblSupplementPriceValue;
    @FXML
    private Label lblSupplementQtyValue;
    @FXML
    private TextField orderQtyField;
    @FXML
    private TableColumn<OrderSupplementTM, Integer> colSupplementId;
    @FXML
    private TableColumn<OrderSupplementTM, String> colSupplementName;
    @FXML
    private TableColumn<OrderSupplementTM, Double> colSupplementPrice;
    @FXML
    private TableColumn<OrderSupplementTM, Integer> colOrderQty;
    @FXML
    private TableColumn<OrderSupplementTM, Double> colSupplementTotal;
    @FXML
    private TableView<OrderSupplementTM> tblOrderSupplement;
    @FXML
    private Label lblTotalValue;

    private final MemberBO memberBO = (MemberBO) BOFactory.getInstance().getBO(BOFactory.BOType.MEMBER);
    private final SupplementDAO supplementDAO = new SupplementDAOImpl();
    private OrderModel orderModel = new OrderModel();

    private final ObservableList<OrderSupplementTM> orderSupplementObList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        colSupplementId.setCellValueFactory(new PropertyValueFactory<>("supplementId"));
        colSupplementName.setCellValueFactory(new PropertyValueFactory<>("supplementName"));
        colSupplementPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colOrderQty.setCellValueFactory(new PropertyValueFactory<>("supplementQty"));
        colSupplementTotal.setCellValueFactory(new PropertyValueFactory<>("supplementTotal"));

        loadMemberIds();
        loadSupplementIds();
    }

    private void loadMemberIds() {
        try {
            List<MemberDTO> memberList = memberBO.getAllMember();
            ObservableList<String> obList = FXCollections.observableArrayList();

            for (MemberDTO member : memberList) {
                obList.add(String.valueOf(member.getMemberID()));
            }
            comboMemberId.setItems(obList);

        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Failed to load customers").show();
        }
    }

    private void loadSupplementIds() {
        try {
            List<SupplementDTO> supplementDTOList = supplementDAO.getAllIds();
            ObservableList<String> obList = FXCollections.observableArrayList();

            for (SupplementDTO supplement : supplementDTOList) {
                obList.add(String.valueOf(supplement.getId()));
            }
            comboSupplementId.setItems(obList);

        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Failed to load items").show();
        }
    }

    @FXML
    private void handleSelectMemberId(ActionEvent event) {

        String selectedId = comboMemberId.getValue();
        if (selectedId == null) return;

        try {
            MemberDTO memberDTO = memberBO.searchMember(selectedId);
            if (memberDTO == null) return;

            lblMemberNameValue.setText(memberDTO.getName());
            lblEmailValue.setText(memberDTO.getEmail());
            lblContactValue.setText(String.valueOf(memberDTO.getContact()));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleSelectSupplementId(ActionEvent event) {

        String selectedId = comboSupplementId.getValue();
        if (selectedId == null) return;

        try {
            SupplementDTO supplementDTO = supplementDAO.search(selectedId);
            if (supplementDTO == null) return;

            lblSupplementNameValue.setText(supplementDTO.getName());
            lblSupplementQtyValue.setText(String.valueOf(supplementDTO.getQty()));
            lblSupplementPriceValue.setText(String.valueOf(supplementDTO.getUnitPrice()));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleAddToCart(ActionEvent event) {

        if (comboSupplementId.getValue() == null || orderQtyField.getText().isEmpty()) {
            new Alert(Alert.AlertType.WARNING, "Select supplement & enter qty").show();
            return;
        }

        int availableQty = Integer.parseInt(lblSupplementQtyValue.getText());
        int orderQty = Integer.parseInt(orderQtyField.getText());

        if (orderQty > availableQty) {
            new Alert(Alert.AlertType.ERROR, "Invalid quantity").show();
            return;
        }

        double price = Double.parseDouble(lblSupplementPriceValue.getText());

        OrderSupplementTM tm = new OrderSupplementTM(
                Integer.parseInt(comboSupplementId.getValue()),
                lblSupplementNameValue.getText(),
                price,
                orderQty,
                price * orderQty
        );

        orderSupplementObList.add(tm);
        tblOrderSupplement.setItems(orderSupplementObList);
        calculateTotal();
    }

    private void calculateTotal() {
        double total = 0;
        for (OrderSupplementTM tm : orderSupplementObList) {
            total += tm.getSupplementTotal();
        }
        lblTotalValue.setText(String.valueOf(total));
    }

    @FXML
    private void handlePlaceOrder(ActionEvent event) {

        try {
            String memberId = comboMemberId.getSelectionModel().getSelectedItem();

            List<OrderSupplementDTO> orderSupplement = new ArrayList<>();

            for (OrderSupplementTM orderSupplementTM : orderSupplementObList) {

                OrderSupplementDTO orderSupplementDTO = new OrderSupplementDTO(
                        orderSupplementTM.getSupplementId(),
                        orderSupplementTM.getSupplementQty(),
                        orderSupplementTM.getUnitPrice()
                );

                orderSupplement.add(orderSupplementDTO);
            }

            OrderDTO orderDTO = new OrderDTO(Integer.parseInt(memberId), new Date(), orderSupplement);

            boolean isOrderPlaced = orderModel.placeOrder(orderDTO);

            if(isOrderPlaced) {
                new Alert(Alert.AlertType.INFORMATION, "Order placed successfully!").show();
                reset();
            }

        } catch(Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Something went wrong!").show();
        }
    }

    @FXML
    private void reset() {
        lblMemberNameValue.setText("");
        lblEmailValue.setText("");
        lblContactValue.setText("");
        lblSupplementNameValue.setText("");
        lblSupplementQtyValue.setText("");
        lblSupplementPriceValue.setText("");
        lblTotalValue.setText("");
        comboSupplementId.setValue(null);
        comboMemberId.setValue(null);
        orderQtyField.setText("");
    }
}

