/*
 * Copyright (c) 2021.
 * programmed by Rachid Boufous.
 * for FRMFS-ltd organisation
 *
 */

package Controller.UserControllers;

import Helpers.AppContext;

import com.github.daytron.simpledialogfx.data.DialogResponse;
import com.github.daytron.simpledialogfx.data.DialogStyle;
import com.github.daytron.simpledialogfx.dialog.Dialog;
import com.github.daytron.simpledialogfx.dialog.DialogType;


import com.jfoenix.controls.JFXButton;
import dao.Services.UserService;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Callback;
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
    private TableColumn<utilisateur, String> OptionsCol;


    @FXML
    private JFXButton RefreshBtn;


    ObservableList<utilisateur> UsersList = FXCollections.observableArrayList();
    UserService uService = new UserService();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        LoadData();
        CreateIcons();

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

   public void CreateIcons(){

        Callback<TableColumn<utilisateur,String>,TableCell<utilisateur,String>> cellFactory = (
                TableColumn<utilisateur,String> param) ->{

            final TableCell<utilisateur,String> cell = new TableCell<utilisateur,String>(){

                @Override
                protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);

                    if(empty){
                        setGraphic(null);
                        setText(null);
                    }else{

                        FontAwesomeIconView  DeleteIco = new FontAwesomeIconView(FontAwesomeIcon.TRASH);
                        FontAwesomeIconView EditIco = new FontAwesomeIconView(FontAwesomeIcon.PENCIL_SQUARE_ALT);

                        DeleteIco.setGlyphSize(26);
                        DeleteIco.setFill(Color.rgb(251, 62, 56));
                        DeleteIco.setCursor(Cursor.HAND);


                        EditIco.setGlyphSize(26);
                        EditIco.setFill(Color.rgb(66, 66, 66));
                        EditIco.setCursor(Cursor.HAND);



                        // create the event handler for each btn
                        DeleteIco.setOnMouseClicked((MouseEvent event)->{

                            // get the selected user to be deleted
                            utilisateur user = UsersTable.getSelectionModel().getSelectedItem();
                            System.out.println(user.toString());

                            // create an alert to make the user verify that he really want ot delete this item
                            Dialog dialog = new Dialog(
                                    DialogType.CONFIRMATION,
                                    "Delete User action",
                                    "Confirm Action",
                                    "Are you sure you want to delete " + user.getNom() + " " + user.getPrenom() + "?");

                            dialog.showAndWait();

                            if (dialog.getResponse() == DialogResponse.YES) {
                                System.out.println("test delete");
                                uService.delete(user.getId_user());
                                refreshDataSet();
                            }
                        });



                        HBox managebtn = new HBox(DeleteIco, EditIco);
                        managebtn.setStyle("-fx-alignment:center");

                        HBox.setMargin(DeleteIco, new Insets(2, 2, 0, 3));
                        HBox.setMargin(EditIco, new Insets(2, 3, 0, 2));

                        setGraphic(managebtn);

                    }

                }
            };

            return cell;
        };
       OptionsCol.setCellFactory(cellFactory);

       }

    }