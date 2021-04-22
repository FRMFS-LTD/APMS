/*
 * Copyright (c) 2021.
 * programmed by Rachid Boufous.
 * for FRMFS-ltd organisation
 *
 */

package Controller.VehiculeControllers;

import Controller.UserControllers.AddUserController;
import Helpers.AppContext;
import com.github.daytron.simpledialogfx.data.DialogResponse;
import com.github.daytron.simpledialogfx.dialog.Dialog;
import com.github.daytron.simpledialogfx.dialog.DialogType;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import dao.Services.VehiculeService;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
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
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import model.Vehicule;
import model.utilisateur;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;


public class VehiculeCrudController implements Initializable {

    @FXML
    private Label titleLab;

    @FXML
    private JFXTextField SearchTextField;

    @FXML
    private TableView<Vehicule> VehiculeTable;

    @FXML
    private TableColumn<Vehicule, Integer> VehiculeIdCol;

    @FXML
    private TableColumn<Vehicule, Integer> platNumberCol;

    @FXML
    private TableColumn<Vehicule, String> subscriptionCol;

    @FXML
    private TableColumn<Vehicule, String> ClientCol;


    @FXML
    private TableColumn<Vehicule, String> OptionsCol;

    @FXML
    private JFXButton AddUserGui;

    @FXML
    private JFXButton RefreshBtn;

    ObservableList<Vehicule> VehiculesList = FXCollections.observableArrayList();
    VehiculeService vService = new VehiculeService();



    @FXML
    void RefreshBtn_click(ActionEvent event) {
        refereshDataSet();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        CreateIcons();
        LoadData();
        FilterSearch();
    }


    public void LoadData(){

        DefineCols();
        refereshDataSet();
    }

    public void DefineCols(){
        VehiculeIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        platNumberCol.setCellValueFactory(new PropertyValueFactory<>("matriucle"));
        subscriptionCol.setCellValueFactory(new PropertyValueFactory<Vehicule, String>("client"));
        ClientCol.setCellValueFactory(new PropertyValueFactory<Vehicule, String>("abonnement"));

        // get the title of the subs
        subscriptionCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Vehicule,String>, ObservableValue<String>>(){
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Vehicule, String> param) {

                //check if the vehicule has an assoicated sub or client so we can show it up here
                // if not we just show instead a message

                if(param.getValue().getAbonnement() ==null){
                    return new SimpleStringProperty("no subscription Associated");
                }else{
                    return new SimpleStringProperty(param.getValue().getAbonnement().getIntitule());
                }


            }
        });

        // get the title of the subs
        ClientCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Vehicule, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Vehicule, String> param) {
                if (param.getValue().getClient() == null){
                    return new SimpleStringProperty("No Client Associated");
                }else{
                    return new SimpleStringProperty(param.getValue().getClient().getNom() + " " + param.getValue().getClient().getPrenom() );
                }

            }
        });

    }

    public void refereshDataSet(){

        VehiculesList.clear();

        ArrayList<Vehicule> VList = (ArrayList<Vehicule>)  vService.findAll();

        VehiculesList.addAll(VList);

        VehiculeTable.setItems(VehiculesList);

    }

    @FXML
    void AddVehiculeGui_click(ActionEvent event) throws IOException {

        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/VehiculeViews/AddVehicule.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        AppContext.UpdateStage(primaryStage, root, scene);
        AppContext.DragScene(primaryStage, root);

        primaryStage.show();
    }

    public void CreateIcons(){

            Callback<TableColumn<Vehicule,String>, TableCell<Vehicule,String>> cellFactory = (
                    TableColumn<Vehicule,String> param) ->{

                final TableCell<Vehicule,String> cell = new TableCell<Vehicule,String>(){

                    @Override
                    protected void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);

                        if(empty){
                            setGraphic(null);
                            setText(null);
                        }else{

                            FontAwesomeIconView DeleteIco = new FontAwesomeIconView(FontAwesomeIcon.TRASH);
                            FontAwesomeIconView EditIco = new FontAwesomeIconView(FontAwesomeIcon.PENCIL_SQUARE_ALT);

                            StyleIcons(DeleteIco, EditIco);


                            // create the event handler for each btn
                            // create the event handler for DeleteBtn
                            DeleteIco.setOnMouseClicked((MouseEvent event)->
                            {
                                DeleteVehiculeConfirmation();
                            });

                            // create the event handler for EditBtn
                            EditIco.setOnMouseClicked((MouseEvent EditEvent)->
                            {
                                Vehicule vehicule = VehiculeTable.getSelectionModel().getSelectedItem();

                                LoadUserIntoUpdateForm(vehicule);

                            });


                            SetIconsToTabViewCell(DeleteIco, EditIco);

                        }

                    }

                    private void LoadUserIntoUpdateForm(Vehicule vehicle) {
                        FXMLLoader loader = new FXMLLoader ();
                        loader.setLocation(getClass().getResource("/fxml/VehiculeViews/addVehicule.fxml"));
                        try {
                            loader.load();
                        } catch (IOException ex) {
                            Logger.getLogger(AddVehiculeController.class.getName()).log(Level.SEVERE, null, ex);
                        }

                        AddVehiculeController addVehiculeController = loader.getController();
                        addVehiculeController.setUpdate(true);

                        addVehiculeController.InitFieldsForUpdate(
                                vehicle.getId(),
                                vehicle.getMatriucle(),
                                vehicle.getClient(),
                                vehicle.getAbonnement()
                        );

                        Parent parent = loader.getRoot();
                        Stage stage = new Stage();
                        Scene scene = new Scene(parent);
                        stage.setScene(scene);
                        stage.initStyle(StageStyle.TRANSPARENT);
                        AppContext.UpdateStage(stage,parent,scene);
                        AppContext.DragScene(stage,parent);
                        stage.show();
                    }

                    private void SetIconsToTabViewCell(FontAwesomeIconView DeleteIco, FontAwesomeIconView EditIco) {
                        HBox managebtn = new HBox(DeleteIco, EditIco);
                        managebtn.setStyle("-fx-alignment:center");

                        HBox.setMargin(DeleteIco, new Insets(2, 2, 0, 3));
                        HBox.setMargin(EditIco, new Insets(2, 3, 0, 2));

                        setGraphic(managebtn);
                    }

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
    private void DeleteVehiculeConfirmation() {
        // get the selected user to be deleted
        Vehicule vehicle = VehiculeTable.getSelectionModel().getSelectedItem();


        // create an alert to make the user verify that he really want ot delete this item
        Dialog dialog = new Dialog(
                DialogType.CONFIRMATION,
                "Delete User action",
                "Confirm Action",
                "Do you want to delete this car with plat: \""+ vehicle.getMatriucle() +"\"?");

        dialog.showAndWait();

        if (dialog.getResponse() == DialogResponse.YES) {
            vService.delete(vehicle.getId());
            refereshDataSet();
        }
    }


    public void FilterSearch(){
        FilteredList<Vehicule> filteredData = new FilteredList<>(VehiculesList, b -> true);
        SearchTextField.textProperty().addListener((observable, oldValue, newValue) -> {

            filteredData.setPredicate(
                    Vehicule -> {
                        if(newValue == null || newValue.isEmpty()){
                            return  true;
                        }
                        // Compare first name and last name of every person with filter text.
                        String lowerCaseFilter = newValue.toLowerCase();

                        if (Vehicule.getMatriucle().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
                            return true; // Filter matches first name.
                        } else if (Vehicule.getAbonnement().getIntitule().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                            return true; // Filter matches last name.
                        }
                        else
                            return false; // Does not match.
                    });
        });

        SortedList<Vehicule> sortedData = new SortedList<>(filteredData);


        sortedData.comparatorProperty().bind(VehiculeTable.comparatorProperty());

        VehiculeTable.setItems(sortedData);
    }
}
