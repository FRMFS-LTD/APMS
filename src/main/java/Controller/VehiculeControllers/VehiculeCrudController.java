/*
 * Copyright (c) 2021.
 * programmed by Rachid Boufous.
 * for FRMFS-ltd organisation
 *
 */

package Controller.VehiculeControllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;



public class VehiculeCrudController {

    @FXML
    private Label titleLab;

    @FXML
    private JFXTextField SearchTextField;

    @FXML
    private TableView<?> UsersTable;

    @FXML
    private TableColumn<?, ?> VehiculeIdCol;

    @FXML
    private TableColumn<?, ?> platNumberCol;

    @FXML
    private TableColumn<?, ?> subscriptionCol;

    @FXML
    private TableColumn<?, ?> ClientCol;

    @FXML
    private JFXButton AddUserGui;

    @FXML
    private JFXButton RefreshBtn;

    @FXML
    void AddVehiculeGui_click(ActionEvent event) {

    }

    @FXML
    void RefreshBtn_click(ActionEvent event) {

    }

}
