/*
 * Copyright (c) 2021.
 * programmed by Rachid Boufous.
 * for FRMFS-ltd organisation
 *
 */

package Controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import dao.Services.parkingService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.Stage;
import model.parking;

import java.net.URL;
import java.util.ResourceBundle;

public class ParkingAdd {
   parkingService PS = new parkingService();
    @FXML
    private JFXTextField VillePField;

    @FXML
    private JFXTextField AdrssField;

    @FXML
    private JFXTextField NBField;

    @FXML
    private JFXButton addNewParking;

    @FXML
    private JFXButton Cancel;

    @FXML
    void Cancel_click(ActionEvent event) {
        CloseForm();

    }

    private void CloseForm() {
        Stage stg  = (Stage) Cancel.getScene().getWindow();
        stg.close();
    }

    @FXML
    void addNewParking_click(ActionEvent event) {
        parking park = newParking();
        PS.persist(park);
    }

    private parking newParking(){
        parking park = new parking();

        park.setVille(VillePField.getText());
        park.setAddress(AdrssField.getText());
        park.setNbplace(Integer.parseInt(NBField.getText()));

        return park;
    }



}
