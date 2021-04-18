/*
 * Copyright (c) 2021.
 * programmed by Rachid Boufous.
 * for FRMFS-ltd organisation
 *
 */

package Controller.UserControllers;

import Helpers.AppContext;
import com.jfoenix.controls.JFXButton;
import dao.Services.UserService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.utilisateur;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class UserCrudController implements Initializable {

    @FXML
    private JFXButton AddUserGui;


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


    @FXML
    private JFXButton RefreshBtn;


    ObservableList<utilisateur> UsersList = FXCollections.observableArrayList();
    UserService uService = new UserService();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        LoadData();

    }

    public void LoadData() {



        DefineCols();

        refreshDataSet();
    }

    private void refreshDataSet() {
        UsersList.clear();
        ArrayList<utilisateur> e = (ArrayList<utilisateur>) uService.findAll();
        for (utilisateur u : e){
            UsersList.add(u);
        }
        UsersTable.setItems(UsersList);
    }

    private void DefineCols() {
        idCol.setCellValueFactory(new PropertyValueFactory<>("id_user"));
        firstNameCOl.setCellValueFactory(new PropertyValueFactory<>("nom"));
        LastNameCol.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        userNameCol.setCellValueFactory(new PropertyValueFactory<>("username"));
        idNumberCol.setCellValueFactory(new PropertyValueFactory<>("cin"));
        MailCol.setCellValueFactory(new PropertyValueFactory<>("mail"));
        phoneCol.setCellValueFactory(new PropertyValueFactory<>("tel"));
        RoleCol.setCellValueFactory(new PropertyValueFactory<>("is_admin"));
    }


    @FXML
    void AddUserGui_click(ActionEvent event) throws IOException {

        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/AddUser.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        AppContext.UpdateStage(primaryStage, root, scene);
        AppContext.DragScene(primaryStage, root);

        primaryStage.show();
    }






    @FXML
    void RefreshBtn_click(ActionEvent event) {
        refreshDataSet();

    }


}