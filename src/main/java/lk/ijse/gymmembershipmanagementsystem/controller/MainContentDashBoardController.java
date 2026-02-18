package lk.ijse.gymmembershipmanagementsystem.controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import lk.ijse.gymmembershipmanagementsystem.dao.custom.DashBoardDAO;
import lk.ijse.gymmembershipmanagementsystem.dao.custom.MainContentDashBoardDAO;
import lk.ijse.gymmembershipmanagementsystem.dao.custom.impl.DashBoardDAOImpl;
import lk.ijse.gymmembershipmanagementsystem.dao.custom.impl.MainContentDashBoardDAOImpl;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class MainContentDashBoardController implements Initializable {
    @FXML
    private Label lblDate;
    @FXML
    private Label lblTime;
    @FXML
    private Label lblTotalMembers;
    @FXML
    private Label lblTotalTrainers;
    @FXML
    private Label lblTodaySessions;
    @FXML
    private Label lblIncome;
    @FXML
    private ListView<String> listTodaySessions;
    @FXML
    private LineChart<String, Number> incomeChart;

    MainContentDashBoardDAO  mainContentDashBoardDAO = new MainContentDashBoardDAOImpl();
    DashBoardDAO  dashBoardDAO = new DashBoardDAOImpl();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("Main Content Dash Board is loaded!");
        setDate();
        startClock();
        loadSummaryData();
        loadTodaySessions();
        loadDailyIncomeChart();
    }

    private void setDate() {
        DateTimeFormatter dateFormatter =
                DateTimeFormatter.ofPattern("dd MMM yyyy");

        lblDate.setText(LocalDate.now().format(dateFormatter));
    }

    private void startClock() {
        DateTimeFormatter timeFormatter =
                DateTimeFormatter.ofPattern("hh:mm:ss a");

        Timer timer = new Timer(true);
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> {
                    lblTime.setText(
                            LocalTime.now().format(timeFormatter)
                    );
                });
            }
        }, 0, 1000);
    }

    private void loadSummaryData() {
        try {
            lblTotalMembers.setText(String.valueOf(dashBoardDAO.getTotalMembers()));
            lblTotalTrainers.setText(String.valueOf(dashBoardDAO.getTotalTrainers()));
            lblTodaySessions.setText(String.valueOf(dashBoardDAO.getTodaySessions()));
            lblIncome.setText("Rs. " + dashBoardDAO.getTodayIncome());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadTodaySessions() {
        listTodaySessions.getItems().clear();

        try {
            List<String> sessions = mainContentDashBoardDAO.getTodaySessions();

            if (sessions.isEmpty()) {
                listTodaySessions.getItems().add("No sessions scheduled for today");
            } else {
                listTodaySessions.getItems().addAll(sessions);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadDailyIncomeChart() {
        incomeChart.getData().clear();
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Daily Income");

        try {
            Map<String, Double> incomeData = mainContentDashBoardDAO.getDailyIncomeData();

            if (!incomeData.isEmpty()) {
                for (Map.Entry<String, Double> entry : incomeData.entrySet()) {
                    series.getData().add(new XYChart.Data<>(entry.getKey(), entry.getValue()));
                }
                incomeChart.getData().add(series);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
