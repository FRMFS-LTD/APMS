/*
 * Copyright (c) 2021.
 * programmed by Rachid Boufous.
 * for FRMFS-ltd organisation
 *
 */
package Controller.StatisticsController;

import dao.Services.parkingService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class statisticsController implements Initializable {

    @FXML
    private Label ActiveParkingsN;

    @FXML
    private Label AvplaceN;

    @FXML
    private Label VehiculesN;

    @FXML
    private PieChart CustomersPie;

    parkingService ps = new parkingService();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadData();
    }

    private void loadData(){
        ObservableList<PieChart.Data> chart = FXCollections.observableArrayList();
        chart.add(new PieChart.Data("Subscribed Vehicule:",150));
        chart.add(new PieChart.Data("UnSubscribed Vehicule:",350));
        CustomersPie.setData(chart);

        setParkingNumber();
        setAvplaces();

    }

    public void setParkingNumber(){
        int count  = ps.findParkingCount();
        ActiveParkingsN.setText(String.valueOf(count));
    }

    public void setAvplaces(){
        int count  = ps.AvPlaceNumber();

        AvplaceN.setText(String.valueOf(count));
    }


}
