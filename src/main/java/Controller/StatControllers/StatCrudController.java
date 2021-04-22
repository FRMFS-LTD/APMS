
package Controller.StatControllers;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Stationnement;
import model.Vehicule;
import model.typetarif;

import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

public class StatCrudController implements Initializable {
    @FXML
    private JFXButton AddStatGui;

    @FXML
    private JFXButton RefreshBtn;

    @FXML
    private TableView<Stationnement> StatTable;

    @FXML
    private TableColumn<Stationnement, Integer> idStatCol;

    @FXML
    private TableColumn<Stationnement,Integer> idParkCol;

    @FXML
    private TableColumn<Stationnement, Integer> idVehCol;

    @FXML
    private TableColumn<Stationnement, Integer> idTypetarifCol;

    @FXML
    private TableColumn<Vehicule, String> immatVehCol;

    @FXML
    private TableColumn<Stationnement,Date> dateEntreeCol;

    @FXML
    private TableColumn<Stationnement,Date> dateSortieCol;

    @FXML
    private TableColumn<typetarif, Float> tarifCol;

    @FXML
    private TableColumn<?,?> mtCol;

    @FXML
    void AddStatGui_click(ActionEvent event) {

    }

    @FXML
    void RefreshBtn_click(ActionEvent event) {

    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        LoadData();
    }

    private void LoadData() {
        DefineCols();
        refereshDataSet();
    }

    private void refereshDataSet() {
    }

    private void DefineCols() {
        idStatCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        idParkCol.setCellValueFactory(new PropertyValueFactory<>("parking"));
        idVehCol.setCellValueFactory(new PropertyValueFactory<>("vehicule"));
        immatVehCol.setCellValueFactory(new PropertyValueFactory<>("vehicule"));

    }
}
