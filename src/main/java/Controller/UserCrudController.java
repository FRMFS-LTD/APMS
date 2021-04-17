/*
 * Copyright (c) 2021.
 * programmed by Rachid Boufous.
 * for FRMFS-ltd organisation
 *
 */

package Controller;

import com.jfoenix.controls.JFXTreeTableView;
import dao.Services.UserService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.utilisateur;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class UserCrudController implements Initializable {

    @FXML
    private TableView<utilisateur> UsersTable;

    @FXML
    private TableColumn<utilisateur, String> idCol;

    @FXML
    private TableColumn<utilisateur, String> firstNameCOl;

    @FXML
    private TableColumn<utilisateur, String> LastNameCol;

    @FXML
    private TableColumn<utilisateur, String> userNameCol;

    @FXML
    private TableColumn<utilisateur, String> idNumberCol;

    @FXML
    private TableColumn<utilisateur, String> MailCol;

    @FXML
    private TableColumn<utilisateur, String> phoneCol;

    @FXML
    private TableColumn<utilisateur, String> RoleCol;


    ObservableList<utilisateur> UsersList = FXCollections.observableArrayList();
    UserService uService = new UserService();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }


}
