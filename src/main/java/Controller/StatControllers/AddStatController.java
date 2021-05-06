package Controller.StatControllers;/*
 * Copyright (c) 2021.
 * programmed by Rachid Boufous.
 * for FRMFS-ltd organisation
 *
 */

import com.github.daytron.simpledialogfx.dialog.Dialog;
import com.github.daytron.simpledialogfx.dialog.DialogType;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import dao.Services.StationnementService;
import dao.Services.VehiculeService;
import dao.Services.parkingService;
import dao.Services.typetarifService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import model.Stationnement;
import model.Vehicule;
import model.parking;
import model.typetarif;
import org.hibernate.HibernateException;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AddStatController implements Initializable {
    @FXML
    private JFXTextField txtIdStat;

    @FXML
    private JFXComboBox<Vehicule> cmbIdveh;

    @FXML
    private JFXComboBox<parking> cmbIdpark;

    @FXML
    private JFXComboBox<typetarif> cmbIdtarif;

    @FXML
    private Label lblMt;

    @FXML
    private Label GlobalError;

    @FXML
    private DatePicker dtpEntree;

    @FXML
    private DatePicker dtpSortie;

    @FXML
    private JFXButton addNewStat;

    @FXML
    private JFXButton Cancel;

    private boolean update;
    private int StationnementId;


    StationnementService StatService = new StationnementService();
    VehiculeService vService = new VehiculeService();
    parkingService pService = new parkingService();
    typetarifService tpService = new typetarifService();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        fillComboBoxs();

    }
    public void setUpdate(boolean update) {
        this.update = update;
    }


    @FXML
    void addNewStat_click(ActionEvent event) {
        try{
            if(!this.update){
                if(GeneralException()){

                    Stationnement new_stat = new Stationnement();
                    Stationnement StatAdd =  AddOrUpdateStationnement(new_stat);

                    StatService.persist(StatAdd);
                    CloseForm();
                }


            }else{

                Stationnement stat = StatService.findById(StationnementId);
                Stationnement Statupdate = AddOrUpdateStationnement(stat);

                if(GeneralException()){
                    StatService.update(Statupdate);
                    CloseForm();
                }

            }
        }catch (HibernateException E ){
            Dialog dialog = new Dialog(
                    DialogType.ERROR,
                    "DATABASE ERROR",
                    E.getMessage());
            dialog.showAndWait();
        }
        catch (Exception E){
            Dialog dialog = new Dialog(
                    DialogType.ERROR,
                    E.getCause().toString(),
                    E.getMessage());
            dialog.showAndWait();
        }
    }

      public void fillComboBoxs(){
        fillcmbIdpark();
        fillcmbIdveh();
        fillcmbIdtarif();
    }

    private void fillcmbIdveh() {

        ObservableList<Vehicule> VehList = FXCollections.observableArrayList();
        ArrayList<Vehicule> ListVehicule = new ArrayList<Vehicule>();
        ListVehicule = (ArrayList<Vehicule>) vService.findAll();

        VehList.addAll(ListVehicule);
        cmbIdveh.setItems(VehList);
    }

    private void fillcmbIdpark() {
        ObservableList<parking> parkList = FXCollections.observableArrayList();
        ArrayList<parking> listParking = new ArrayList<parking>();
        listParking = (ArrayList<parking>) pService.findAll();

        parkList.addAll(listParking);
        cmbIdpark.setItems(parkList);
    }
    private void fillcmbIdtarif() {
        ObservableList<typetarif> tarifList = FXCollections.observableArrayList();
        ArrayList<typetarif> listTarif = new ArrayList<typetarif>();
        listTarif = (ArrayList<typetarif>) tpService.findAll();

        tarifList.addAll(listTarif);
        cmbIdtarif.setItems(tarifList);
    }


    @FXML
    void Cancel_click(ActionEvent event) {
        Stage stage = (Stage) Cancel.getScene().getWindow();
        stage.close();
    }

    private Stationnement AddOrUpdateStationnement(Stationnement new_stationnement ) {

        Vehicule veh = cmbIdveh.getSelectionModel().getSelectedItem();
        parking park= cmbIdpark.getSelectionModel().getSelectedItem();
        typetarif tarif= cmbIdtarif.getSelectionModel().getSelectedItem();

        new_stationnement.setVehicule(veh);
        new_stationnement.setParking(park);
        new_stationnement.setTypetarif(tarif);
        new_stationnement.setDateEntree(dtpEntree.getValue());
        new_stationnement.setDateSortie(dtpSortie.getValue());
        return  new_stationnement;
    }

    public Boolean GeneralException(){
        if(cmbIdveh.getSelectionModel().getSelectedItem() == null
                && cmbIdpark.getSelectionModel().getSelectedItem() != null
                && cmbIdtarif.getSelectionModel().getSelectedItem() != null )
        {
            return SetErrorMessage("il faut selectionner une immatriculation");
        }

        else if(cmbIdveh.getSelectionModel().getSelectedItem() != null
                && cmbIdpark.getSelectionModel().getSelectedItem() == null
                && cmbIdtarif.getSelectionModel().getSelectedItem() != null )
        {
            return SetErrorMessage("il faut selectionner un parking");
        }
        else if(cmbIdveh.getSelectionModel().getSelectedItem() != null
                && cmbIdpark.getSelectionModel().getSelectedItem() != null
                && cmbIdtarif.getSelectionModel().getSelectedItem() == null )
        {
            return SetErrorMessage("il faut selectionner un tarif");
        }
        else{
            GlobalError.setText("");
            return true;
        }
    }

    private boolean SetErrorMessage(String s) {
        GlobalError.setText(s);
        return false;
    }

    private void CloseForm(){

        Stage stage = (Stage) Cancel.getScene().getWindow();
        stage.close();
    }


    public void InitFieldsForUpdate(int id,LocalDate dEntree, LocalDate dSortie, Vehicule veh, parking park, typetarif tarif){
        this.StationnementId = id;
        dtpEntree.setValue(dEntree);
        dtpSortie.setValue(dSortie);
        cmbIdveh.getSelectionModel().select(veh.getId()-1);
        cmbIdpark.getSelectionModel().select(park.getId_parking()-1);
        cmbIdtarif.getSelectionModel().select(tarif.getId_typetarif()-1);
    }
}
