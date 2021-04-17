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
import dao.Services.UserService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.Stage;
import model.utilisateur;

import java.net.URL;
import java.util.ResourceBundle;

public class AddUserController implements Initializable {

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
    private JFXComboBox<String> isAdminField;

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
        CloseForm();
    }

    private void CloseForm() {
        Stage stage = (Stage) Cancel.getScene().getWindow();
        stage.close();
    }

    @FXML
    void addNewUser_click(ActionEvent event) {
        UserService us = new UserService();
        utilisateur new_user = createNewUser();
        us.persist(new_user);
        CloseForm();
    }

    private utilisateur createNewUser() {
        utilisateur new_user = new utilisateur();

        new_user.setNom(firstNameField.getText());
        new_user.setPrenom(LastNameField.getText());
        new_user.setCin(CinField.getText());
        new_user.setTel(telField.getText());
        new_user.setMail(MailField.getText());
        new_user.setUsername(UserNameField.getText());
        new_user.setPassword(pwdField.getText());
        new_user.setIs_admin(Boolean.getBoolean(isAdminField.getSelectionModel().getSelectedItem()));
        return new_user;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        isAdminField.getItems().add(0,"False");
        isAdminField.getItems().add(1,"True");
    }


}
