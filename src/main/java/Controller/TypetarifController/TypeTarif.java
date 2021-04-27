/*
 * Copyright (c) 2021.
 * programmed by Fadoua Abdoulmoulah.
 * for FRMFS-ltd organisation
 *
 */

package Controller.TypetarifController;


import Helpers.AppContext;
import com.github.daytron.simpledialogfx.data.DialogResponse;
import com.github.daytron.simpledialogfx.dialog.Dialog;
import com.github.daytron.simpledialogfx.dialog.DialogType;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import dao.Services.typetarifService;
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
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import model.typetarif;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;


public class TypeTarif implements Initializable {

    ObservableList<typetarif> TTList = FXCollections.observableArrayList();
    typetarifService TTServ = new typetarifService();

    @FXML
    private TableView<typetarif> TypeTarfiTable;

    @FXML
    private TableColumn<typetarif, String> TypetarifCol;

    @FXML
    private TableColumn<typetarif, String> PrixCol;

    @FXML
    private TableColumn<typetarif, String> AdrssCol11;

    @FXML
    private TableColumn<typetarif, String> OptionCol ;

    @FXML
    private JFXTextField SearchTextField;


    @FXML
    private JFXButton AddInfoTypetarif;

    @FXML
    private JFXButton RefreshBtn;

    @FXML
    private ImageView refreshbtn;

    public void initialize(URL url, ResourceBundle RsBdl) {

        LoadData();
        CreateIcons();
        FilterSearch();
    }

    private void LoadData() {
        DefineCol();
        refreshDataSet();
    }

    private void DefineCol() {

        TypetarifCol.setCellValueFactory(new PropertyValueFactory<>("typetarif"));
        PrixCol.setCellValueFactory(new PropertyValueFactory<>("prix"));
    }

    @FXML
    void AddNewTypeTarif_click(ActionEvent event) throws IOException {

        Stage TTstg = new Stage();

        Parent root = FXMLLoader.load(getClass().getResource("/fxml/Typetarifview/Add_typetarif.fxml"));
        Scene scn = new Scene(root);
        TTstg.setScene(scn);
        AppContext.UpdateStage(TTstg, root, scn);
        AppContext.DragScene(TTstg, root);

        TTstg.show();
    }


    private void refreshDataSet() {
        TTList.clear();
        ArrayList<typetarif> ATT = (ArrayList<typetarif>) TTServ.findAll();
        
            TTList.addAll(ATT);

        TypeTarfiTable.setItems(TTList);
    }

    @FXML
    void RefreshBtn_click(ActionEvent event) {
        refreshDataSet();
    }


    public void CreateIcons() {

        Callback<TableColumn<typetarif, String>, TableCell<typetarif, String>> cellFactory = (
                TableColumn<typetarif, String> param) -> {

            final TableCell<typetarif, String> cell = new TableCell<typetarif, String>() {

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
                            typetarif TT = TypeTarfiTable.getSelectionModel().getSelectedItem();

                            LoadtypetariIntoUpdateForm(TT);

                        });


                        SetIconsToTabViewCell(DeleteIco, EditIco);

                    }

                }


                private void LoadtypetariIntoUpdateForm(typetarif TT) {
                    FXMLLoader loader = new FXMLLoader ();
                    loader.setLocation(getClass().getResource("/fxml/Typetarifview/Add_typetarif.fxml"));
                    try {
                        loader.load();
                    } catch (IOException ex) {
                        Logger.getLogger(TypeTarifAdd.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    TypeTarifAdd TypeTarifAdd = loader.getController();
                    TypeTarifAdd.setUpdate(true);
                    TypeTarifAdd.initTextFieldForUpdate(TT.getId_typetarif(), TT.getTypetarif(), TT.getPrix());

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
        typetarif TT = TypeTarfiTable.getSelectionModel().getSelectedItem();


        Dialog dialog = new Dialog(
                DialogType.CONFIRMATION,
                "Delete User action",
                "Confirm Action",
                "Are you sure you want to delete " + TT.getTypetarif() + "?");

        dialog.showAndWait();

        if (dialog.getResponse() == DialogResponse.YES) {
            TTServ.delete(TT.getId_typetarif());
            refreshDataSet();
        }
    }


    public void FilterSearch(){
        FilteredList<typetarif> filteredData = new FilteredList<>(TTList, b -> true);
        SearchTextField.textProperty().addListener((observable, oldValue, newValue) -> {

            filteredData.setPredicate(
                    typetarif -> {
                        if(newValue == null || newValue.isEmpty()){
                            return  true;
                        }
                        String lowerCaseFilter = newValue.toLowerCase();

                        if (typetarif.getTypetarif().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
                            return true;
                        }

                        else
                            return false;
                    });
        });

        SortedList<typetarif> sortedData = new SortedList<>(filteredData);


        sortedData.comparatorProperty().bind(TypeTarfiTable.comparatorProperty());

        TypeTarfiTable.setItems(sortedData);
    }
}




