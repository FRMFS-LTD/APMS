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
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.utilisateur;


import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

import javafx.scene.control.Label;


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
    private Label firstNameError;

    @FXML
    private Label LastNameError;

    @FXML
    private Label IdError;

    @FXML
    private Label PhoneError;

    @FXML
    private Label MailError;

    @FXML
    private Label isAdminErrpr;

    @FXML
    private Label UserNameError;

    @FXML
    private Label passwordErrpr;






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




    @FXML
    void CinField_textChanged(KeyEvent event) {

        if(!(Pattern.matches("^\\w\\w\\d*$", CinField.getText()))){
            IdError.setText("Incorrect Id Format (eg: AA111111)");
            IdError.setTextFill(Color.web("#E53935", 0.8));
        }else{
            IdError.setText("Valid Id Format");
            IdError.setTextFill(Color.web("#64DD17", 0.8));
        }
    }

    @FXML
    void MailField_textChanged(KeyEvent event) {


        if(!(Pattern.matches("^([a-zA-Z0-9_\\-\\.]+)@([a-zA-Z0-9_\\-\\.]+)\\.([a-zA-Z]{2,5})$", MailField.getText()))){
            MailError.setText("Incorrect Mail Format (eg: example@mail.com)");
            MailError.setTextFill(Color.web("#E53935", 0.8));
        }else{
            MailError.setText("Valid Mail Format");
            MailError.setTextFill(Color.web("#64DD17", 0.8));
        }
    }



    @FXML
    void pwdField__textChanged(KeyEvent event) {
        if( pwdField.getText().length() < 8 )
        {
            passwordErrpr.setText("Password length must be greater than 8");
            passwordErrpr.setTextFill(Color.web("#E53935", 0.8));
        }else{
            passwordErrpr.setText("Valid Password");
            passwordErrpr.setTextFill(Color.web("#64DD17", 0.8));
        }
    }

    @FXML
    void telField_textChanged(KeyEvent event) {

        if( !(Pattern.matches("^[+]*[(]{0,1}[0-9]{1,4}[)]{0,1}[-\\s\\./0-9]*$", telField.getText())))
        {
            PhoneError.setText("Invalid phone Format (eg: +212 611111111)");
            PhoneError.setTextFill(Color.web("#E53935", 0.8));
        }else{
            PhoneError.setText("Valid Phone");
            PhoneError.setTextFill(Color.web("#64DD17", 0.8));
        }
    }



    @FXML
    void LastNameField_TextChanged(KeyEvent event) {
        if( LastNameField.getText().length() < 3 )
        {
            LastNameError.setText("LastName length must be greater than 3");
            LastNameError.setTextFill(Color.web("#E53935", 0.8));
        }else{
            LastNameError.setText("Valid LastName");
            LastNameError.setTextFill(Color.web("#64DD17", 0.8));
        }
    }



    @FXML
    void UserNameField_TextChanged(KeyEvent event) {
        if( UserNameField.getText().length() < 6 )
        {
            UserNameError.setText("UserName length must be greater than 6");
            UserNameError.setTextFill(Color.web("#E53935", 0.8));
        }else{
            UserNameError.setText("Valid UserName");
            UserNameError.setTextFill(Color.web("#64DD17", 0.8));
        }
    }


    @FXML
    void firstNameField_TextChanged(KeyEvent event) {
        if( firstNameField.getText().length() < 3 )
        {
            firstNameError.setText("LastName length must be greater than 3");
            firstNameError.setTextFill(Color.web("#E53935", 0.8));
        }else{
            firstNameError.setText("Valid LastName");
            firstNameError.setTextFill(Color.web("#64DD17", 0.8));
        }
    }




}
