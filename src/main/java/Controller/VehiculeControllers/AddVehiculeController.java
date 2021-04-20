/*
 * Copyright (c) 2021.
 * programmed by Rachid Boufous.
 * for FRMFS-ltd organisation
 *
 */

package Controller.VehiculeControllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;

public class AddVehiculeController {

    @FXML
    private JFXTextField MatriculeField;

    @FXML
    private JFXButton addNewUser;

    @FXML
    private JFXButton Cancel;

    @FXML
    private JFXComboBox<?> ClientComboBOx;

    @FXML
    private JFXComboBox<?> SubComboBox;

    @FXML
    private Label GlobalError;

    @FXML
    private Label platNumberError;

    @FXML
    private Label SubError;

    @FXML
    private Label ClientError;

    @FXML
    void Cancel_click(ActionEvent event) {

    }

    @FXML
    void MatriculeField_TextChanged(KeyEvent event) {

    }

    @FXML
    void addNewVehicule_click(ActionEvent event) {

    }

}
