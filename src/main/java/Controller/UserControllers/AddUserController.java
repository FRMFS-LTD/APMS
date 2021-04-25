/*
 * Copyright (c) 2021.
 * programmed by Rachid Boufous.
 * for FRMFS-ltd organisation
 *
 */

package Controller.UserControllers;


import com.github.daytron.simpledialogfx.dialog.Dialog;
import com.github.daytron.simpledialogfx.dialog.DialogType;
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


import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

import javafx.scene.control.Label;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.exception.ConstraintViolationException;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;


public class AddUserController implements Initializable {

    @FXML
    private Label titleLab;

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
    private Label GlobalError;


    ArrayList<Boolean> validationList = new ArrayList<Boolean>();

    private boolean update;
    private int userid;
    UserService us = new UserService();

    public void setUpdate(boolean b) {
        this.update = b;
    }


    private void CloseForm() {

        Stage stage = (Stage) Cancel.getScene().getWindow();
        stage.close();
    }


    @FXML
    void addNewUser_click(ActionEvent event) {

        try {
            if (this.update == false) {

                utilisateur user = new utilisateur();
                utilisateur new_user = createOrupdateNewUser(user);
                if (GeneralExeption()) {
                    us.persist(new_user);
                    CloseForm();
                }


            } else {

                utilisateur userE = us.findById(userid);
                utilisateur userRe = createOrupdateNewUser(userE);
                if (GeneralExeption()) {
                    us.update(userRe);
                    CloseForm();
                }


            }

        } catch (HibernateException E) {
            Dialog dialog = new Dialog(
                    DialogType.ERROR,
                    "DATABASE ERROR",
                    E.getMessage());
            dialog.showAndWait();
        } catch (Exception E) {
            Dialog dialog = new Dialog(
                    DialogType.ERROR,
                    E.getCause().toString(),
                    E.getMessage());
            dialog.showAndWait();
        }


    }

    private utilisateur createOrupdateNewUser(utilisateur user) {


        user.setNom(firstNameField.getText());
        user.setPrenom(LastNameField.getText());
        user.setCin(CinField.getText());
        user.setTel(telField.getText());
        user.setMail(MailField.getText());
        user.setUsername(UserNameField.getText());
        user.setPassword(pwdField.getText());
        if (isAdminField.getValue() == "True") {
            user.setIs_admin(TRUE);
        } else {
            user.setIs_admin(FALSE);
        }
        return user;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        isAdminField.getItems().add(0, "False");
        isAdminField.getItems().add(1, "True");
    }

    @FXML
    void CinField_textChanged(KeyEvent event) {


        if (!(Pattern.matches("^\\w\\w\\d*$", CinField.getText()))) {
            IdError.setText("Incorrect Id Format (eg: AA111111)");
            IdError.setTextFill(Color.web("#E53935", 0.8));

        } else {
            IdError.setText("Valid Id Format");
            IdError.setTextFill(Color.web("#64DD17", 0.8));

        }

    }

    @FXML
    void MailField_textChanged(KeyEvent event) {

        boolean flag;
        if (!(Pattern.matches("^([a-zA-Z0-9_\\-\\.]+)@([a-zA-Z0-9_\\-\\.]+)\\.([a-zA-Z]{2,5})$", MailField.getText()))) {
            MailError.setText("Incorrect Mail Format (eg: example@mail.com)");
            MailError.setTextFill(Color.web("#E53935", 0.8));

        } else {
            MailError.setText("Valid Mail Format");
            MailError.setTextFill(Color.web("#64DD17", 0.8));

        }

    }


    @FXML
    void pwdField__textChanged(KeyEvent event) {

        if (pwdField.getText().length() < 8) {
            passwordErrpr.setText("Password length must be greater than 8");
            passwordErrpr.setTextFill(Color.web("#E53935", 0.8));

        } else {
            passwordErrpr.setText("Valid Password");
            passwordErrpr.setTextFill(Color.web("#64DD17", 0.8));

        }


    }

    @FXML
    void telField_textChanged(KeyEvent event) {


        if (!(Pattern.matches("^[+]*[(]{0,1}[0-9]{1,4}[)]{0,1}[-\\s\\./0-9]*$", telField.getText()))) {
            PhoneError.setText("Invalid phone Format (eg: +212 611111111)");
            PhoneError.setTextFill(Color.web("#E53935", 0.8));

        } else {
            PhoneError.setText("Valid Phone");
            PhoneError.setTextFill(Color.web("#64DD17", 0.8));

        }

    }


    @FXML
    void LastNameField_TextChanged(KeyEvent event) {


        if (LastNameField.getText().length() < 3) {

            LastNameError.setText("LastName length must be greater than 3");
            LastNameError.setTextFill(Color.web("#E53935", 0.8));

        } else {
            LastNameError.setText("Valid LastName");
            LastNameError.setTextFill(Color.web("#64DD17", 0.8));

        }

    }


    @FXML
    void UserNameField_TextChanged(KeyEvent event) {

        if (UserNameField.getText().length() < 6) {
            UserNameError.setText("UserName length must be greater than 6");
            UserNameError.setTextFill(Color.web("#E53935", 0.8));

        } else {
            UserNameError.setText("Valid UserName");
            UserNameError.setTextFill(Color.web("#64DD17", 0.8));

        }

    }


    @FXML
    void firstNameField_TextChanged(KeyEvent event) {

        if (firstNameField.getText().length() < 3) {
            firstNameError.setText("LastName length must be greater than 3");
            firstNameError.setTextFill(Color.web("#E53935", 0.8));

        } else {
            firstNameError.setText("Valid LastName");
            firstNameError.setTextFill(Color.web("#64DD17", 0.8));

        }


    }

    public void initTextFieldForUpdate(int id, String firstname, String lastName, String cin, String tel,
                                       String mail, String Userame, String password, boolean is_admin) {
        userid = id;
        firstNameField.setText(firstname);
        LastNameField.setText(lastName);
        CinField.setText(cin);
        telField.setText(tel);
        MailField.setText(mail);
        UserNameField.setText(Userame);
        pwdField.setText(password);
        isAdminField.getSelectionModel().select(is_admin ? 1 : 0);
    }


    @FXML
    void Cancel_click(ActionEvent event) {
        CloseForm();
    }


    public boolean GeneralExeption() {

        GlobalError.setTextFill(Color.web("#E53935", 0.8));

        if (firstNameField.getText().isEmpty() || firstNameField.getText().length() < 3) {

            return SetErrorMessage("validate firstName field to conditions");

        } else if (LastNameField.getText().isEmpty() || LastNameField.getText().length() < 3) {

            return SetErrorMessage("validate LastName field to given Conditions");
        } else if (CinField.getText().isEmpty() || CinField.getText().length() < 4
                || !(Pattern.matches("^\\w\\w\\d*$", CinField.getText()))) {

            return SetErrorMessage("validate Identity field to given Conditions");
        } else if (telField.getText().isEmpty() || telField.getText().length() < 10
                || !(Pattern.matches("^[+]*[(]{0,1}[0-9]{1,4}[)]{0,1}[-\\s\\./0-9]*$", telField.getText()))) {

            return SetErrorMessage("validate Phone field to given Conditions");
        } else if (MailField.getText().isEmpty() || MailField.getText().length() < 8 ||
                !(Pattern.matches("^([a-zA-Z0-9_\\-\\.]+)@([a-zA-Z0-9_\\-\\.]+)\\.([a-zA-Z]{2,5})$", MailField.getText()))) {
            return SetErrorMessage("validate Mail field to given Conditions");
        } else if (isAdminField.getValue() == null) {
            return SetErrorMessage("please define user role");
        } else if (UserNameField.getText().isEmpty() || UserNameField.getText().length() < 8) {
            return SetErrorMessage("validate Mail field to given Conditions");
        } else if (pwdField.getText().isEmpty() || pwdField.getText().length() < 8) {
            return SetErrorMessage("validate Mail field to given Conditions");
        } else {
            return true;
        }

    }

    private boolean SetErrorMessage(String s) {
        GlobalError.setText(s);
        return false;
    }



}



