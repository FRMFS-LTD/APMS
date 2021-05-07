/*
 * Copyright (c) 2021.
 * programmed by Rachid Boufous.
 * for FRMFS-ltd organisation
 *
 */

package Controller.UserControllers;

import Helpers.AppContext;

import com.github.daytron.simpledialogfx.data.DialogResponse;
import com.github.daytron.simpledialogfx.dialog.Dialog;
import com.github.daytron.simpledialogfx.dialog.DialogType;


import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import dao.Services.UserService;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
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
import javafx.stage.StageStyle;
import javafx.util.Callback;
import model.utilisateur;
import org.hibernate.HibernateException;
import org.hibernate.exception.ConstraintViolationException;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserCrudController implements Initializable {
    // handle crud mechanism on users table
    @FXML
    private JFXButton AddUserGui;


    @FXML
    private JFXTextField SearchTextField;

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

    // hold the list of users
    ObservableList<utilisateur> UsersList = FXCollections.observableArrayList();
    UserService uService = new UserService();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // on the moment of loading the form
        LoadData();
        CreateIcons();
        FilterSearch();
    }

    public void LoadData() {
        DefineCols();
        refreshDataSet();
    }

    public void refreshDataSet() {
        // get data from database and load it to list
        UsersList.clear();
        ArrayList<utilisateur> e = (ArrayList<utilisateur>) uService.findAll();
        UsersList.addAll(e);
        UsersTable.setItems(UsersList);
    }

    private void DefineCols() {
        // map between the table view and the user model
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

        // open the add user form
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/UserViews/AddUser.fxml"));
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

    public void CreateIcons() {
        // this will allow us to create icons on the table view
        Callback<TableColumn<utilisateur, String>, TableCell<utilisateur, String>> cellFactory = (
                TableColumn<utilisateur, String> param) -> {

            final TableCell<utilisateur, String> cell = new TableCell<utilisateur, String>() {

                @Override
                protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);

                    if (empty) {
                        setGraphic(null);
                        setText(null);
                    } else {
                        // declare a new icons for update and delete inside the table view
                        FontAwesomeIconView DeleteIco = new FontAwesomeIconView(FontAwesomeIcon.TRASH);
                        FontAwesomeIconView EditIco = new FontAwesomeIconView(FontAwesomeIcon.PENCIL_SQUARE_ALT);

                        StyleIcons(DeleteIco, EditIco);


                        // create the event handler for each btn
                        // create the event handler for DeleteBtn

                        DeleteIco.setOnMouseClicked((MouseEvent event) ->
                        {
                            DeleteUserConfirmation();
                        });

                        // create the event handler for EditBtn
                        EditIco.setOnMouseClicked((MouseEvent EditEvent) ->
                        {
                            utilisateur user = UsersTable.getSelectionModel().getSelectedItem();

                            LoadUserIntoUpdateForm(user);

                        });


                        SetIconsToTabViewCell(DeleteIco, EditIco);

                    }

                }

                private void LoadUserIntoUpdateForm(utilisateur user) {
                    // when the user click update on table view, we need to load the current selected
                    // user to the add/update form

                    //load the fxml of the form
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("/fxml/UserViews/addUser.fxml"));
                    try {
                        loader.load();
                    } catch (IOException ex) {
                        Logger.getLogger(AddUserController.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    // access to the related controller of the form
                    // to init the field for update with the cureent selected user
                    AddUserController addUserController = loader.getController();
                    addUserController.setUpdate(true);
                    addUserController.initTextFieldForUpdate(user.getId_user(),
                            user.getNom(), user.getPrenom(), user.getCin(),
                            user.getTel(), user.getMail(), user.getUsername(), user.getPassword(), user.getIs_admin()
                    );
                    // finally load the form filled with user to update info
                    Parent parent = loader.getRoot();
                    Stage stage = new Stage();
                    Scene scene = new Scene(parent);
                    stage.setScene(scene);
                    stage.initStyle(StageStyle.UTILITY);
                    AppContext.UpdateStage(stage, parent, scene);
                    AppContext.DragScene(stage, parent);
                    stage.show();
                }

                private void SetIconsToTabViewCell(FontAwesomeIconView DeleteIco, FontAwesomeIconView EditIco) {
                    // insert the create icons to table view
                    HBox managebtn = new HBox(DeleteIco, EditIco);
                    managebtn.setStyle("-fx-alignment:center");

                    HBox.setMargin(DeleteIco, new Insets(2, 2, 0, 3));
                    HBox.setMargin(EditIco, new Insets(2, 3, 0, 2));

                    setGraphic(managebtn);
                }


                // define the color,size and cursor type for update and delete
                private void StyleIcons(FontAwesomeIconView DeleteIco, FontAwesomeIconView EditIco) {
                    DeleteIco.setGlyphSize(26);
                    DeleteIco.setFill(Color.rgb(251, 62, 56));
                    DeleteIco.setCursor(Cursor.HAND);


                    EditIco.setGlyphSize(26);
                    EditIco.setFill(Color.rgb(66, 66, 66));
                    EditIco.setCursor(Cursor.HAND);
                }
            };

            return cell;
        };
        OptionsCol.setCellFactory(cellFactory);

    }

    private void DeleteUserConfirmation() {
        try {
            // get the selected user to be deleted
            utilisateur user = UsersTable.getSelectionModel().getSelectedItem();


            // create an alert to make the user verify that he really want ot delete this item
            Dialog dialog = new Dialog(
                    DialogType.CONFIRMATION,
                    "Delete User action",
                    "Confirm Action",
                    "Are you sure you want to delete " + user.getNom() + " " + user.getPrenom() + "?");

            dialog.showAndWait();

            if (dialog.getResponse() == DialogResponse.YES) {
                uService.delete(user.getId_user());
                refreshDataSet();
            }
        } catch (ConstraintViolationException e) {
            Dialog dialog = new Dialog(
                    DialogType.ERROR,
                    e.getCause().toString(),
                    e.getMessage());
            dialog.showAndWait();
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


    public void FilterSearch() {
        FilteredList<utilisateur> filteredData = new FilteredList<>(UsersList, b -> true);
        SearchTextField.textProperty().addListener((observable, oldValue, newValue) -> {

            filteredData.setPredicate(
                    utilisateur -> {
                        if (newValue == null || newValue.isEmpty()) {
                            return true;
                        }
                        // Compare first name and last name of every person with filter text.
                        String lowerCaseFilter = newValue.toLowerCase();

                        if (utilisateur.getNom().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                            return true; // Filter matches first name.
                        } else if (utilisateur.getPrenom().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                            return true; // Filter matches last name.
                        } else if (String.valueOf(utilisateur.getCin()).indexOf(lowerCaseFilter) != -1)
                            return true;
                        else
                            return false; // Does not match.
                    });
        });

        SortedList<utilisateur> sortedData = new SortedList<>(filteredData);


        sortedData.comparatorProperty().bind(UsersTable.comparatorProperty());

        UsersTable.setItems(sortedData);
    }

}