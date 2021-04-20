/*
 * Copyright (c) 2021.
 * programmed by Fadoua Abdoulmoulah.
 * for FRMFS-ltd organisation
 *
 */

package Controller;

import Helpers.AppContext;
import com.jfoenix.controls.JFXButton;
import dao.Services.parkingService;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.parking;


import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;


public class Parking implements Initializable {

    ObservableList<parking> ParkList = FXCollections.observableArrayList();
    parkingService ParkServ = new parkingService();

    @FXML
    private JFXButton RefreshBtn;

    @FXML
    private JFXButton AddInfoParking;

    @FXML
    private TableView<parking> ParkingTable;

    @FXML
    private TableColumn<parking, String> AdrssCol;

    @FXML
    private TableColumn<parking, String> VilleCol;

    @FXML
    private TableColumn<parking, Integer> NbPCol;

    @FXML
    private TableColumn<parking, Integer> NbPLCol;


    public void initialize(URL url, ResourceBundle RsBdl){

        LoadData();
    }

    private void LoadData() {
        DefineCol();

        refreshDataSet();
    }

    private void DefineCol() {

        AdrssCol.setCellValueFactory(new PropertyValueFactory<>("Adresse"));
        VilleCol.setCellValueFactory(new PropertyValueFactory<>("Ville"));
        NbPCol.setCellValueFactory(new PropertyValueFactory<>("Nombre de place"));
        NbPLCol.setCellValueFactory(new PropertyValueFactory<>("Nombre de place libre"));

    }

    private void refreshDataSet() {
        ParkList.clear();
        ArrayList<parking> AP = (ArrayList<parking>) ParkServ.findAll();

        for (parking park : AP){
            ParkList.add(park);
        }
        ParkingTable.setItems(ParkList);
    }

    @FXML
    void AddNewParking_click(ActionEvent event) throws IOException {

        Stage prstg = new Stage();

        Parent root = FXMLLoader.load(getClass().getResource("/fxml/ParkingAdd.fxml"));
        Scene scn = new Scene(root);
        prstg.setScene(scn);
        AppContext.UpdateStage(prstg, root, scn);
        AppContext.DragScene(prstg, root);

        prstg.show();
    }

    @FXML
    void RefreshBtn_click(ActionEvent event) {
              refreshDataSet();
    }

}
