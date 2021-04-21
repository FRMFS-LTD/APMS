/*
 * Copyright (c) 2021.
 * programmed by Rachid Boufous.
 * for FRMFS-ltd organisation
 *
 */

package Controller.VehiculeControllers;

import Helpers.AppContext;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import dao.Services.VehiculeService;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;
import model.Vehicule;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;


public class VehiculeCrudController implements Initializable {

    @FXML
    private Label titleLab;

    @FXML
    private JFXTextField SearchTextField;

    @FXML
    private TableView<Vehicule> VehiculeTable;

    @FXML
    private TableColumn<Vehicule, Integer> VehiculeIdCol;

    @FXML
    private TableColumn<Vehicule, Integer> platNumberCol;

    @FXML
    private TableColumn<Vehicule, String> subscriptionCol;

    @FXML
    private TableColumn<Vehicule, String> ClientCol;

    @FXML
    private JFXButton AddUserGui;

    @FXML
    private JFXButton RefreshBtn;

    ObservableList<Vehicule> VehiculesList = FXCollections.observableArrayList();
    VehiculeService vService = new VehiculeService();



    @FXML
    void RefreshBtn_click(ActionEvent event) {
        refereshDataSet();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        LoadData();
    }


    public void LoadData(){

        DefineCols();
        refereshDataSet();
    }

    public void DefineCols(){
        VehiculeIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        platNumberCol.setCellValueFactory(new PropertyValueFactory<>("matriucle"));
        subscriptionCol.setCellValueFactory(new PropertyValueFactory<Vehicule, String>("client"));
        ClientCol.setCellValueFactory(new PropertyValueFactory<Vehicule, String>("abonnement"));

        // get the title of the subs
        subscriptionCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Vehicule,String>, ObservableValue<String>>(){
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Vehicule, String> param) {

                //check if the vehicule has an assoicated sub or client so we can show it up here
                // if not we just show instead a message

                if(param.getValue().getAbonnement() ==null){
                    return new SimpleStringProperty("no subscription Associated");
                }else{
                    return new SimpleStringProperty(param.getValue().getAbonnement().getIntitule());
                }


            }
        });

        // get the title of the subs
        ClientCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Vehicule, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Vehicule, String> param) {
                if (param.getValue().getClient() == null){
                    return new SimpleStringProperty("No Client Associated");
                }else{
                    return new SimpleStringProperty(param.getValue().getClient().getNom() + " " + param.getValue().getClient().getPrenom() );
                }

            }
        });

    }

    public void refereshDataSet(){

        VehiculesList.clear();

        ArrayList<Vehicule> VList = (ArrayList<Vehicule>)  vService.findAll();

        for (Vehicule v : VList){
            VehiculesList.add(v);
        }

        VehiculeTable.setItems(VehiculesList);

    }

    @FXML
    void AddVehiculeGui_click(ActionEvent event) throws IOException {

        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/VehiculeViews/AddVehicule.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        AppContext.UpdateStage(primaryStage, root, scene);
        AppContext.DragScene(primaryStage, root);

        primaryStage.show();
    }
}
