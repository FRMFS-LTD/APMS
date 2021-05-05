
package Controller.StatControllers;

import Helpers.AppContext;
import com.github.daytron.simpledialogfx.data.DialogResponse;
import com.github.daytron.simpledialogfx.dialog.Dialog;
import com.github.daytron.simpledialogfx.dialog.DialogType;
import com.jfoenix.controls.JFXTextField;
import dao.Services.StationnementService;
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
import model.Stationnement;
import org.hibernate.HibernateException;
import org.hibernate.exception.ConstraintViolationException;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class StatCrudController implements Initializable {
    @FXML
    private JFXTextField SearchTextField;

   @FXML
    private TableView<Stationnement> StatTable;

    @FXML
    private TableColumn<Stationnement, Integer> idStatCol;

   @FXML
    private TableColumn<Stationnement, String> immatVehCol;

    @FXML
    private TableColumn<Stationnement,LocalDate> dateEntreeCol;

    @FXML
    private TableColumn<Stationnement, LocalDate> dateSortieCol;

    @FXML
    private TableColumn<Stationnement, String> tarifCol;

    @FXML
    private TableColumn<Stationnement, String> ParkingVilleCol;

    @FXML
    private TableColumn<Stationnement, String> OptionsCol;

    ObservableList<Stationnement> StatList = FXCollections.observableArrayList();
    StationnementService statService = new StationnementService();




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

    private void LoadData() {
        DefineCols();
        refereshDataSet();

    }

    public void refereshDataSet(){
        StatList.clear();
        ArrayList<Stationnement> VList = (ArrayList<Stationnement>)  statService.findAll();
        StatList.addAll(VList);
        StatTable.setItems(StatList);
    }
    @FXML
    void AddStatGui_click(ActionEvent event) throws  IOException{
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/StatViews/AddStationnement.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        AppContext.UpdateStage(primaryStage, root, scene);
        AppContext.DragScene(primaryStage, root);

        primaryStage.show();
    }

    private void DefineCols() {
        idStatCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        immatVehCol.setCellValueFactory(new PropertyValueFactory<Stationnement, String>("vehicule"));
        dateEntreeCol.setCellValueFactory(new PropertyValueFactory<>("dateEntree"));
        dateSortieCol.setCellValueFactory(new PropertyValueFactory<>("dateSortie"));
        tarifCol.setCellValueFactory(new PropertyValueFactory<Stationnement, String>("typetarif"));
        ParkingVilleCol.setCellValueFactory(new PropertyValueFactory<Stationnement, String>("parking"));

        // get the title of the Immatriculation
        immatVehCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Stationnement,String>, ObservableValue<String>>(){
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Stationnement, String> param) {

                //Vérifier si l'immatriculation est renseignée
                // sinom afficher un message

                if(param.getValue().getVehicule() ==null){
                    return new SimpleStringProperty("immatriculation non associée");
                }else{
                    return new SimpleStringProperty(param.getValue().getVehicule().getMatriucle());
                }
            }
        });

        // get the title of the subs
        ParkingVilleCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Stationnement, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Stationnement, String> param) {
                if (param.getValue().getParking() == null){
                    return new SimpleStringProperty("Aucun Parking associée");
                }else{
                    return new SimpleStringProperty(param.getValue().getParking().getVille());
                }

            }});
        tarifCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Stationnement, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Stationnement, String> param) {
                if (param.getValue().getTypetarif() == null){
                    return new SimpleStringProperty("Aucun tarif attribué");
                }else{
                    return new SimpleStringProperty(String.valueOf(param.getValue().getTypetarif().getPrix()));

                }

            }
        });

    }

    public void CreateIcons(){

        Callback<TableColumn<Stationnement,String>, TableCell<Stationnement,String>> cellFactory = (
                TableColumn<Stationnement,String> param) ->{

            final TableCell<Stationnement,String> cell = new TableCell<Stationnement,String>(){

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
                            DeleteStatConfirmation();
                        });

                        // create the event handler for EditBtn
                        EditIco.setOnMouseClicked((MouseEvent EditEvent)->
                        {
                            Stationnement stationnement =StatTable.getSelectionModel().getSelectedItem();

                            LoadUserIntoUpdateForm(stationnement);

                        });


                        SetIconsToTabViewCell(DeleteIco, EditIco);

                    }

                }

                private void LoadUserIntoUpdateForm(Stationnement stationnement) {
                    FXMLLoader loader = new FXMLLoader ();
                    loader.setLocation(getClass().getResource("/fxml/StatViews/AddStationnement.fxml"));
                    try {
                        loader.load();
                    } catch (IOException ex) {
                        Logger.getLogger(AddStatController.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    AddStatController addStatController = loader.getController();
                    addStatController.setUpdate(true);

                   addStatController.InitFieldsForUpdate(
                           stationnement.getId(),
                           stationnement.getDateEntree(),
                           stationnement.getDateSortie(),
                           stationnement.getVehicule(),
                           stationnement.getParking(),
                           stationnement.getTypetarif()
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
    private void DeleteStatConfirmation() {
        try {


            // get the selected user to be deleted
            Stationnement stationnement = StatTable.getSelectionModel().getSelectedItem();


            // create an alert to make the user verify that he really want ot delete this item
            Dialog dialog = new Dialog(
                    DialogType.CONFIRMATION,
                    "Delete User action",
                    "Confirm Action",
                    "Do you want to delete this stationnemnt with N°: \"" + stationnement.getId() + "\"?");

            dialog.showAndWait();

            if (dialog.getResponse() == DialogResponse.YES) {
                statService.delete(stationnement.getId());
                refereshDataSet();
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
    public void FilterSearch(){
        FilteredList<Stationnement> filteredData = new FilteredList<>(StatList, b -> true);
        SearchTextField.textProperty().addListener((observable, oldValue, newValue) -> {

            filteredData.setPredicate(
                    Stationnement -> {
                        if(newValue == null || newValue.isEmpty()){
                            return  true;
                        }
                        // Compare first name and last name of every person with filter text.
                        String lowerCaseFilter = newValue.toLowerCase();

                        if (Stationnement.getVehicule().getMatriucle().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
                            return true; // Filtrer avec la matricule.
                        } else if (Stationnement.getParking().getVille().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                            return true; // Filtrer avec parking.
                        }
                        else
                            return false; // Does not match.
                    });
        });

        SortedList<Stationnement> sortedData = new SortedList<>(filteredData);


        sortedData.comparatorProperty().bind(StatTable.comparatorProperty());

        StatTable.setItems(sortedData);
    }
}
