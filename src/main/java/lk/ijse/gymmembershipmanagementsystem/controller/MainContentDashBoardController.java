package lk.ijse.gymmembershipmanagementsystem.controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import lk.ijse.gymmembershipmanagementsystem.bo.BOFactory;
import lk.ijse.gymmembershipmanagementsystem.bo.custom.DashBoardBO;
import lk.ijse.gymmembershipmanagementsystem.dto.DailyIncomeDTO;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

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

    private DashBoardBO dashBoardBO = (DashBoardBO) BOFactory.getInstance().getBO(BOFactory.BOType.DASHBOARD);

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
            lblTotalMembers.setText(String.valueOf(dashBoardBO.getTotalMembers()));
            lblTotalTrainers.setText(String.valueOf(dashBoardBO.getTotalTrainers()));
            lblTodaySessions.setText(String.valueOf(dashBoardBO.getTodaySessions()));
            lblIncome.setText("Rs. " + dashBoardBO.getTodayIncome());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadTodaySessions() {
        listTodaySessions.getItems().clear();

        try {
            List<String> sessions = dashBoardBO.getTodaySessionsList();

            listTodaySessions.getItems().addAll(sessions);

        } catch (SQLException e) {
            e.printStackTrace();
            listTodaySessions.getItems().add("Error loading sessions!");
        }
    }

    private void loadDailyIncomeChart() {

        incomeChart.getData().clear();

        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Daily Income");

        try {
            List<DailyIncomeDTO> data = dashBoardBO.getDailyIncomeData();

            for (DailyIncomeDTO d : data) {
                series.getData().add(
                        new XYChart.Data<>(d.getDate(), d.getIncome())
                );
            }

            if (!data.isEmpty()) {
                incomeChart.getData().add(series);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
