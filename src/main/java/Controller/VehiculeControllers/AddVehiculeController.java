/*
 * Copyright (c) 2021.
 * programmed by Rachid Boufous.
 * for FRMFS-ltd organisation
 *
 */

package Controller.VehiculeControllers;

import Helpers.AppContext;
import com.github.daytron.simpledialogfx.dialog.Dialog;
import com.github.daytron.simpledialogfx.dialog.DialogType;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import dao.Services.AbonnementService;
import dao.Services.UserService;
import dao.Services.VehiculeService;
import dao.Services.clientService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.Vehicule;
import model.abonnement;
import model.client;
import model.utilisateur;
import org.hibernate.HibernateException;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class AddVehiculeController implements Initializable {

    @FXML
    private JFXTextField MatriculeField;

    @FXML
    private JFXButton addNewUser;

    @FXML
    private JFXButton Cancel;

    @FXML
    private JFXComboBox<client> ClientComboBOx;

    @FXML
    private JFXComboBox<abonnement> SubComboBox;

    @FXML
    private Label GlobalError;

    @FXML
    private Label platNumberError;

    @FXML
    private Label SubError;

    @FXML
    private Label ClientError;

    private boolean update;
    private int VehicleId;


    VehiculeService vService = new VehiculeService();
    clientService cService = new clientService();
    AbonnementService aService = new AbonnementService();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        fillComboBoxs();

    }


    public void setUpdate(boolean update) {
        this.update = update;
    }

    @FXML
    void addNewVehicule_click(ActionEvent event) {

        try{
            if(!this.update){
                if(GeneralException()){

                    Vehicule new_veh = new Vehicule();
                    Vehicule vAdd =  AddOrUpdateVehicule(new_veh);
                    vService.persist(vAdd);
                    CloseForm();
                }


            }else{

                Vehicule v = vService.findById(VehicleId);
                Vehicule Vupdate = AddOrUpdateVehicule(v);

                if(GeneralException()){
                    vService.update(Vupdate);
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
        fillClientComboBox();

        fillSubComboBox();
    }

    private void fillSubComboBox() {

        ObservableList<abonnement> AList = FXCollections.observableArrayList();
        ArrayList<abonnement> Listabonnement = new ArrayList<abonnement>();
        Listabonnement = (ArrayList<abonnement>) aService.findAll();

        AList.addAll(Listabonnement);

        SubComboBox.setItems(AList);
    }

    private void fillClientComboBox() {
        ObservableList<client> cList = FXCollections.observableArrayList();
        ArrayList<client> listClient = new ArrayList<client>();
        listClient = (ArrayList<client>) cService.findAll();

        cList.addAll(listClient);

        ClientComboBOx.setItems(cList);
    }


    @FXML
    void Cancel_click(ActionEvent event) {
        Stage stage = (Stage) Cancel.getScene().getWindow();
        stage.close();
    }

    @FXML
    void MatriculeField_TextChanged(KeyEvent event) {
        if(!(Pattern.matches("[A-Z]{1,3}[A-Z]{1,2}[0-9]{1,4}",MatriculeField.getText()))){
            platNumberError.setText("Invalid Plat Format (eg: AAAA1234)");
            platNumberError.setTextFill(Color.web("#E53935", 0.8));

        }else{
            platNumberError.setText("Validate Plat Number");
            platNumberError.setTextFill(Color.web("#64DD17", 0.8));

        }
    }



    private Vehicule AddOrUpdateVehicule(Vehicule new_vehicule ) {

        client cl = ClientComboBOx.getSelectionModel().getSelectedItem();
        abonnement ab = SubComboBox.getSelectionModel().getSelectedItem();


        new_vehicule.setMatriucle(MatriculeField.getText());
        new_vehicule.setAbonnement(ab);
        new_vehicule.setClient(cl);

        return  new_vehicule;
    }


    public Boolean GeneralException(){
        GlobalError.setTextFill(Color.web("#E53935", 0.8));

        if(MatriculeField.getText().isEmpty()
                || !(Pattern.matches("[A-Z]{1,3}[A-Z]{1,2}[0-9]{1,4}",MatriculeField.getText())))
        {
            return SetErrorMessage("Validate Plat Number Field");
        }
        else if(ClientComboBOx.getSelectionModel().getSelectedItem() == null
                && SubComboBox.getSelectionModel().getSelectedItem() != null )
        {
            return SetErrorMessage("Can't associate a sub without associating a client");
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


    public void InitFieldsForUpdate(int id,String matricule, client cl,abonnement ab){

        this.VehicleId = id;
        MatriculeField.setText(matricule);
        ClientComboBOx.getSelectionModel().select(cl.getId()-1);
        SubComboBox.getSelectionModel().select(ab.getId_abonnement()-1);

    }
}
