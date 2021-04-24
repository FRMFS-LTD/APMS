/*
 * Copyright (c) 2021.
 * programmed by Fadoua Abdoulmoulah.
 * for FRMFS-ltd organisation
 *
 */

package Controller.ParkingController;

import com.github.daytron.simpledialogfx.dialog.Dialog;
import com.github.daytron.simpledialogfx.dialog.DialogType;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import dao.Services.UserService;
import dao.Services.parkingService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.parking;
import model.utilisateur;
import org.hibernate.HibernateException;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;


public class ParkingAdd {
   parkingService PS = new parkingService();

   @FXML
   private JFXTextField NomField;
    @FXML
    private JFXTextField VillePField;

    @FXML
    private JFXTextField AdrssField;

    @FXML
    private JFXTextField NBField;

    @FXML
    private JFXButton addNewParking;

    @FXML
    private JFXButton Cancel;

    @FXML
    private Label GlobalError;

    private int id_parking ;
    private boolean TOF ;
    parkingService us = new parkingService();


    @FXML
    void Cancel_click(ActionEvent event) {
        CloseForm();

    }

    private void CloseForm() {
        Stage stg  = (Stage) Cancel.getScene().getWindow();
        stg.close();
    }



    private parking createOrupdateNewParking(parking park) {


        park.setNomParking(NomField.getText());
        park.setVille(VillePField.getText());
        park.setAddress(AdrssField.getText());
        park.setNbplace(Integer.parseInt(NBField.getText()));

        return park ;
    }
    @FXML
    void addNewParking_click(ActionEvent event) {

        try{
        if(!this.TOF) {
            parking park = new parking();
            parking new_park = newParking(park);

            if (GeneralException()) {
                PS.persist(park);
                CloseForm();
            }
        }
            else {
                parking parkAd = PS.findById(id_parking);
                parking parkDA = newParking(parkAd);


                if(GeneralException()){
                    PS.update(parkDA);
                    CloseForm();
                }
            }

        }

        catch(HibernateException eh ){
            Dialog dialog = new Dialog(
                    DialogType.ERROR, "Database Error", eh.getMessage());
            dialog.showAndWait();
        }
        catch( Exception e ){
            Dialog dialog = new Dialog(DialogType.ERROR,e.getCause().toString(),e.getMessage());
        }

    }
    private parking newParking(parking parkE){
        parking park = new parking();

        //park.getNomParking();
        park.setVille(VillePField.getText());
        park.setAddress(AdrssField.getText());
        park.setNbplace(Integer.parseInt(NBField.getText()));

        return park;
    }

    private boolean GeneralException() {

        GlobalError.setTextFill(Color.web("#E53935", 0.8));

        if(NomField.getText().isEmpty() || NomField.getText().length()<3){

            return SetErrorMessage("validate nom de parking field to conditions");

        }
        else if (AdrssField.getText().isEmpty() || AdrssField.getText().length()<3){

            return SetErrorMessage("validate Adress field to given Conditions");
        }
        else if (NBField.getText().isEmpty() || NBField.getText().length()<3){

            return SetErrorMessage("validate Nombre de place field to given Conditions");
        }

        else if (VillePField.getText().isEmpty() || VillePField.getText().length()<3){

            return SetErrorMessage("validate ville field to given Conditions");
        }else {
            return true;
        }
    }



    private boolean SetErrorMessage(String s) {
        GlobalError.setText(s);
        return false;
    }

}
