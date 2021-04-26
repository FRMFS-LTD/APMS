/*
 * Copyright (c) 2021.
 * programmed by Fadoua Abdoulmoulah.
 * for FRMFS-ltd organisation
 *
 */

package Controller.ParkingController;

import Controller.TypetarifController.TypeTarifAdd;
import Helpers.AppContext;
import com.github.daytron.simpledialogfx.data.DialogResponse;
import com.github.daytron.simpledialogfx.dialog.Dialog;
import com.github.daytron.simpledialogfx.dialog.DialogType;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import dao.Services.parkingService;
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
import model.parking;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Parking implements Initializable {

    ObservableList<parking> ParkList = FXCollections.observableArrayList();
    parkingService ParkServ = new parkingService();

    @FXML
    private JFXButton RefreshBtn;

    @FXML
    private JFXButton AddInfoParking;

    @FXML
    private TableView<parking> ParkingTable;

    @FXML
    private TableColumn<parking, String> NomCol;


    @FXML
    private TableColumn<parking, String> AdrssCol;

    @FXML
    private TableColumn<parking, String> VilleCol;

    @FXML
    private TableColumn<parking, Integer> NbPCol;

    @FXML
    private TableColumn<parking, Integer> NbPLCol;

    @FXML
    private TableColumn<parking, String> OptionCol;


    @FXML
    private JFXTextField SearchTextField;


    public void initialize(URL url, ResourceBundle RsBdl){

        LoadData();
    }

    private void LoadData() {
        DefineCol();
        FilterSearch();
        refreshDataSet();
    }

    private void DefineCol() {

        NomCol.setCellValueFactory(new PropertyValueFactory<>("NomParking"));
        AdrssCol.setCellValueFactory(new PropertyValueFactory<>("address"));
        VilleCol.setCellValueFactory(new PropertyValueFactory<>("ville"));
        NbPCol.setCellValueFactory(new PropertyValueFactory<>("nbplace"));
        NbPLCol.setCellValueFactory(new PropertyValueFactory<>("nbplacelibre"));

    }

    private void refreshDataSet() {
        ParkList.clear();
        ArrayList<parking> AP = (ArrayList<parking>) ParkServ.findAll();

        for (parking park : AP){
            ParkList.add(park);
        }
        ParkingTable.setItems(ParkList);
    }

    @FXML
    void AddNewParking_click(ActionEvent event) throws IOException {

        Stage prstg = new Stage();

        Parent root = FXMLLoader.load(getClass().getResource("/fxml/ParkingView/ParkingAdd.fxml"));
        Scene scn = new Scene(root);
        prstg.setScene(scn);
        AppContext.UpdateStage(prstg, root, scn);
        AppContext.DragScene(prstg, root);

        prstg.show();
    }

    @FXML
    void RefreshBtn_click(ActionEvent event) {
              refreshDataSet();
    }


    public void CreateIcons() {

        Callback<TableColumn<parking, String>, TableCell<parking, String>> cellFactory = (
                TableColumn<parking, String> param) -> {

            final TableCell<parking, String> cell = new TableCell<parking, String>() {

                @Override
                protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);

                    if (empty) {
                        setGraphic(null);
                        setText(null);
                    } else {

                        FontAwesomeIconView DeleteIco = new FontAwesomeIconView(FontAwesomeIcon.TRASH);
                        FontAwesomeIconView EditIco = new FontAwesomeIconView(FontAwesomeIcon.PENCIL_SQUARE_ALT);

                        StyleIcons(DeleteIco, EditIco);


                        DeleteIco.setOnMouseClicked((MouseEvent event) ->
                        {
                            DeletetypetarifConfirmation();
                        });

                        EditIco.setOnMouseClicked((MouseEvent EditEvent) ->
                        {
                            parking park = ParkingTable.getSelectionModel().getSelectedItem();

                            LoadparkingIntoUpdateForm(parking);

                        });


                        SetIconsToTabViewCell(DeleteIco, EditIco);

                    }

                }


                private void LoadparkingIntoUpdateForm(parking park ) {
                    FXMLLoader loader = new FXMLLoader ();
                    loader.setLocation(getClass().getResource("/fxml/Typetarifview/Add_typetarif.fxml"));
                    try {
                        loader.load();
                    } catch (IOException ex) {
                        Logger.getLogger(TypeTarifAdd.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    ParkingAdd ParkingAdd = loader.getController();
                    ParkingAdd.setUpdate(true);
                    ParkingAdd.initTextFieldForUpdate(park.getId_parking(), park.getNomParking(), park.getAddress(), park.getNbplace, park.getVille);

                    Parent parent = loader.getRoot();
                    Stage stage = new Stage();
                    Scene scene = new Scene(parent);
                    stage.setScene(scene);
                    stage.initStyle(StageStyle.UTILITY);
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
                @FXML

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
        OptionCol.setCellFactory(cellFactory);
    }

    private void DeletetypetarifConfirmation() {
        parking park = ParkingTable.getSelectionModel().getSelectedItem();


        Dialog dialog = new Dialog(
                DialogType.CONFIRMATION,
                "Delete Parking action",
                "Confirm Action",
                "Are you sure you want to delete " + park.getNomParking
        dialog.showAndWait();

        if (dialog.getResponse() == DialogResponse.YES) {
            parkingService.delete(park.getId_parking());
            refreshDataSet();
        }
    }


    public void FilterSearch(){
        FilteredList<parking> filteredData = new FilteredList<>(ParkList, b -> true);
        SearchTextField.textProperty().addListener((observable, oldValue, newValue) -> {

            filteredData.setPredicate(
                    typetarif -> {
                        if(newValue == null || newValue.isEmpty()){
                            return  true;
                        }
                        String lowerCaseFilter = newValue.toLowerCase();

                        if (typetarif.getNomParking().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
                            return true;
                        }

                        if (typetarif.getVille().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
                            return true;
                        }
                        else
                            return false;
                    });
        });

        SortedList<parking> sortedData = new SortedList<>(filteredData);


        sortedData.comparatorProperty().bind(ParkingTable.comparatorProperty());

        ParkingTable.setItems(sortedData);
    }
}
