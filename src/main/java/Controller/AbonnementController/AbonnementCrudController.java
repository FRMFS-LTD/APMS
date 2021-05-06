/*
 * Copyright (c) 2021.
 * programmed by Rachid Boufous.
 * for FRMFS-ltd organisation
 *
 */

package Controller.AbonnementController;

import com.github.daytron.simpledialogfx.data.DialogResponse;
import com.github.daytron.simpledialogfx.dialog.Dialog;
import com.github.daytron.simpledialogfx.dialog.DialogType;
import Helpers.AppContext;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import dao.Services.AbonnementService;
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
import model.abonnement;
import org.hibernate.HibernateException;
import org.hibernate.exception.ConstraintViolationException;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AbonnementCrudController implements Initializable {

    @FXML
    private JFXButton AddAbonn;

    @FXML
    private JFXButton RefreshBtn;

    @FXML
    private JFXTextField SearchTextField;

    @FXML
    private TableView<abonnement> AbonnementTable;

    @FXML
    private TableColumn<abonnement, Integer> idCol;

    @FXML
    private TableColumn<abonnement, String> intituleCol;

    @FXML
    private TableColumn<abonnement, Float> prixCol;

    @FXML
    private TableColumn<abonnement, Integer> periodeCol;

    @FXML
    private TableColumn<abonnement, String> editCol;


    ObservableList<abonnement> AbonnementListe = FXCollections.observableArrayList();
    AbonnementService abonnementliste = new AbonnementService();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        LoadData();
        CreateIcons();
        FilterSearch();
    }

    public void LoadData() {

        DefineCols();
        refreshDataSet();
    }

    @FXML
    void AddAbonn_click(ActionEvent event) throws IOException {
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/AbonnementViews/AddAbonnement.fxml"));
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

    private void refreshDataSet() {
        AbonnementListe.clear();
        ArrayList<abonnement> abonn = (ArrayList<abonnement>) abonnementliste.findAll();
        for (abonnement a : abonn) {
            AbonnementListe.add(a);
        }
        AbonnementTable.setItems(AbonnementListe);

    }


    private void DefineCols() {

        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        intituleCol.setCellValueFactory(new PropertyValueFactory<>("intitule"));
        prixCol.setCellValueFactory(new PropertyValueFactory<>("prix"));
        periodeCol.setCellValueFactory(new PropertyValueFactory<>("periode"));

    }

    public void CreateIcons() {

        Callback<TableColumn<abonnement, String>, TableCell<abonnement, String>> cellFactory =
                (TableColumn<abonnement, String> param) -> {

                    final TableCell<abonnement, String> cell = new TableCell<abonnement, String>() {
                        @Override
                        public void updateItem(String item, boolean empty) {
                            super.updateItem(item, empty);
                            //that cell created only on non-empty rows

                            if (empty) {
                                setGraphic(null);
                                setText(null);

                            } else {

                                FontAwesomeIconView DeleteIco = new FontAwesomeIconView(FontAwesomeIcon.TRASH);
                                FontAwesomeIconView EditIco = new FontAwesomeIconView(FontAwesomeIcon.PENCIL_SQUARE_ALT);

                                StyleIcons(DeleteIco, EditIco);

                                DeleteIco.setOnMouseClicked((MouseEvent event) ->
                                {
                                    DeleteAbonnConfirmation();
                                });

                                // create the event handler for EditBtn
                                EditIco.setOnMouseClicked((MouseEvent EditEvent) ->
                                {
                                    abonnement abonn = AbonnementTable.getSelectionModel().getSelectedItem();

                                    LoadAbonnIntoUpdateForm(abonn);

                                });

                                SetIconsToTabViewCell(DeleteIco, EditIco);
                            }
                        }

                        private void LoadAbonnIntoUpdateForm(abonnement abonnement) {

                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("/fxml/AbonnementViews/AddAbonnement.fxml"));
                    try {
                        loader.load();
                    } catch (IOException ex) {
                        Logger.getLogger(AddAbonnController.class.getName()).log(Level.SEVERE, null, ex);
                    }


                            AddAbonnController addAbonnController = loader.getController();
                            addAbonnController.setUpdate(true);
                            addAbonnController.initTextFieldForUpdate(abonnement.getId(), abonnement.getIntitule(), abonnement.getPrix(), abonnement.getPeriode());
                            Parent parent = loader.getRoot();
                            Stage stage = new Stage();
                            Scene scene = new Scene(parent);
                            stage.setScene(scene);
                            stage.initStyle(StageStyle.UTILITY);
                            AppContext.UpdateStage(stage, parent, scene);
                            AppContext.DragScene(stage, parent);
                            stage.show();
                        }

                        private void StyleIcons(FontAwesomeIconView DeleteIco, FontAwesomeIconView EditIco) {
                            DeleteIco.setGlyphSize(26);
                            DeleteIco.setFill(Color.rgb(251, 62, 56));
                            DeleteIco.setCursor(Cursor.HAND);


                            EditIco.setGlyphSize(26);
                            EditIco.setFill(Color.rgb(66, 66, 66));
                            EditIco.setCursor(Cursor.HAND);
                        }


                        private void SetIconsToTabViewCell(FontAwesomeIconView DeleteIco, FontAwesomeIconView EditIco) {
                            HBox managebtn = new HBox(DeleteIco, EditIco);
                            managebtn.setStyle("-fx-alignment:center");
                            HBox.setMargin(DeleteIco, new Insets(2, 2, 0, 3));
                            HBox.setMargin(EditIco, new Insets(2, 3, 0, 2));

                            setGraphic(managebtn);

                        }

                    };

                    return cell;
                };
        editCol.setCellFactory(cellFactory);

    }

    private void DeleteAbonnConfirmation() {

        try {


            abonnement ab = AbonnementTable.getSelectionModel().getSelectedItem();


            Dialog dialog = new Dialog(

                    DialogType.CONFIRMATION,
                    "Delete Abonnement action",
                    "Confirm Action",
                    "Are you sure you want to delete " + ab.getIntitule() + " " + "?");

            dialog.showAndWait();

            if (dialog.getResponse() == DialogResponse.YES) {
                AbonnementService as = new AbonnementService();
                as.delete(ab.getId());
                refreshDataSet();
            }
        }catch (ConstraintViolationException e) {
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

        FilteredList<abonnement> filteredData = new FilteredList<>(AbonnementListe, b -> true);
        SearchTextField.textProperty().addListener((observable, oldValue, newValue) -> {

            filteredData.setPredicate(
                    abonnement -> {
                        if (newValue == null || newValue.isEmpty()) {
                            return true;
                        }

                        String lowerCaseFilter = newValue.toLowerCase();

                        if (abonnement.getIntitule().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                            return true; // Filter matches Intitule.
                        } else
                            return false; // Does not match.
                    });
        });

        SortedList<abonnement> sortedData = new SortedList<>(filteredData);


        sortedData.comparatorProperty().bind(AbonnementTable.comparatorProperty());

        AbonnementTable.setItems(sortedData);
    }
}




