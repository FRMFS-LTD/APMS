/*
 * Copyright (c) 2021.
 * programmed by Rachid Boufous.
 * for FRMFS-ltd organisation
 *
 */

package Controller.UserControllers;


import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class AddUserController {

    @FXML
    private JFXTextField firstNameField;

    @FXML
    private JFXTextField LastNameField;

    @FXML
    private JFXTextField CinField;

    @FXML
    private JFXTextField telField;

    @FXML
    private JFXTextField MailField;

    @FXML
    private JFXComboBox<?> isAdminField;

    @FXML
    private JFXTextField UserNameField;

    @FXML
    private JFXTextField pwdField;

    @FXML
    private JFXButton addNewUser;

    @FXML
    private JFXButton Cancel;

    @FXML
    void Cancel_click(ActionEvent event) {

    }

    @FXML
    void addNewUser_click(ActionEvent event) {

    }
}
