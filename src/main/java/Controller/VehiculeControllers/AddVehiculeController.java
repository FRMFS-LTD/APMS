/*
 * Copyright (c) 2021.
 * programmed by Rachid Boufous.
 * for FRMFS-ltd organisation
 *
 */

package Controller.VehiculeControllers;

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
import model.Vehicule;
import model.abonnement;
import model.client;
import model.utilisateur;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

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


    VehiculeService vService = new VehiculeService();
    clientService cService = new clientService();
    AbonnementService aService = new AbonnementService();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        fillComboBoxs();

    }


    public void fillComboBoxs(){
        fillClientComboBox();

        fillSubComboBox();
    }

    private void fillSubComboBox() {

        ObservableList<abonnement> AList = FXCollections.observableArrayList();
        ArrayList<abonnement> Listabonnement = new ArrayList<abonnement>();
        Listabonnement = (ArrayList<abonnement>) aService.findAll();

        for(abonnement a: Listabonnement){
            AList.add(a);
        }
        SubComboBox.setItems(AList);
    }

    private void fillClientComboBox() {
        ObservableList<client> cList = FXCollections.observableArrayList();
        ArrayList<client> listClient = new ArrayList<client>();
        listClient = (ArrayList<client>) cService.findAll();

        for(client c : listClient){
            cList.add(c);
        }
        ClientComboBOx.setItems(cList);
    }


    @FXML
    void Cancel_click(ActionEvent event) {

    }

    @FXML
    void MatriculeField_TextChanged(KeyEvent event) {

    }

    @FXML
    void addNewVehicule_click(ActionEvent event) {
        client cl = ClientComboBOx.getSelectionModel().getSelectedItem();
        abonnement ab = SubComboBox.getSelectionModel().getSelectedItem();

        if(cl != null && ab != null){
            System.out.println(cl.getId());
            System.out.println(ab.getId_abonnement());
        }else{
            System.out.println("no item is selected");
        }
    }



}
