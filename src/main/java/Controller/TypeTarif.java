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
import dao.Services.typetarifService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import model.parking;
import model.typetarif;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;


public class TypeTarif implements Initializable {

    ObservableList<typetarif> TTList = FXCollections.observableArrayList();
    typetarifService TTServ = new typetarifService();

    @FXML
    private TableView<typetarif> TypeTarfiTable;

    @FXML
    private TableColumn<typetarif, String> TypetarifCol;

    @FXML
    private TableColumn<typetarif, String> PrixCol;

    @FXML
    private TableColumn<typetarif, String> AdrssCol11;

    @FXML
    private JFXButton AddInfoTypetarif;

    @FXML
    private JFXButton RefreshBtn;

    @FXML
    private ImageView refreshbtn;

    @FXML


    public void initialize(URL url, ResourceBundle RsBdl){

        LoadData();
    }

    private void LoadData() {
        DefineCol();

        refreshDataSet();
    }

    private void DefineCol() {

        TypetarifCol.setCellValueFactory(new PropertyValueFactory<>("typetarif"));
        PrixCol.setCellValueFactory(new PropertyValueFactory<>("prix"));
    }
    @FXML
    void AddNewTypeTarif_click(ActionEvent event) throws IOException {

        Stage TTstg = new Stage();

        Parent root = FXMLLoader.load(getClass().getResource("/fxml/Add_typetarif.fxml"));
        Scene scn = new Scene(root);
        TTstg.setScene(scn);
        AppContext.UpdateStage(TTstg, root, scn);
        AppContext.DragScene(TTstg, root);

        TTstg.show();
    }


    private void refreshDataSet() {
        TTList.clear();
        ArrayList<typetarif> ATT = (ArrayList<typetarif>) TTServ.findAll();

        for (typetarif TT : ATT){
            TTList.add(TT);
        }
       TypeTarfiTable.setItems(TTList);
    }

    @FXML
    void RefreshBtn_click(ActionEvent event) {
          refreshDataSet();
    }


}
