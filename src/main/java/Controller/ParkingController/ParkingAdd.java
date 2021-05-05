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
import dao.Services.parkingService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.parking;
import org.hibernate.HibernateException;

import java.util.regex.Pattern;


public class ParkingAdd {

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

    @FXML
    private Label VilleError;

    @FXML
    private Label NomError;

    @FXML
    private Label AdrssError;

    @FXML
    private Label NbError;
    private int idp ;
    private boolean TOF ;
    parkingService PS = new parkingService();


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
                PS.persist(new_park);
                CloseForm();
            }
        }
            else {
                parking parkAd = PS.findById(idp);
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


        parkE.setNomParking(NomField.getText());
        parkE.setVille(VillePField.getText());
        parkE.setAddress(AdrssField.getText());
        parkE.setNbplace(Integer.parseInt(NBField.getText()));

        return parkE;
    }

    private boolean GeneralException() {

        GlobalError.setTextFill(Color.web("#E53935", 0.8));

        if(NomField.getText().isEmpty() || NomField.getText().length()<3){

            return SetErrorMessage("validate name of parking field to conditions");

        }
        else if (AdrssField.getText().isEmpty() || AdrssField.getText().length()<3){

            return SetErrorMessage("validate Adrress field to given Conditions");
        }
        else if (NBField.getText().isEmpty() ){

            return SetErrorMessage("validate Number of place field to given Conditions");
        }

        else if (VillePField.getText().isEmpty() || VillePField.getText().length()<3){

            return SetErrorMessage("validate city field to given Conditions");
        }else {
            return true;
        }
    }


    @FXML
    void NomField_TextChanged(KeyEvent event) {
        if(NomField.getText().isEmpty()){
            NomError.setText("Invalid Name (EX:AZER..)");
            NomError.setTextFill(Color.web("#E53935", 0.8));

        }else{
            NomError.setText("Valid name");
            NomError.setTextFill(Color.web("#64DD17", 0.8));

        }
    }

    @FXML
    void VilleField_TextChanged(KeyEvent event) {
        if(VillePField.getText().isEmpty()){
            VilleError.setText("Invalid city (EX:Tanger..)");
            VilleError.setTextFill(Color.web("#E53935", 0.8));

        }else{
            VilleError.setText("Valid city");
            VilleError.setTextFill(Color.web("#64DD17", 0.8));

        }
    }


    @FXML
    void AdrssField_TextChanged(KeyEvent event) {
        if(AdrssField.getText().isEmpty() ){
            AdrssError.setText("invalid Adress (EX:Massira..)");
            AdrssError.setTextFill(Color.web("#E53935", 0.8));

        }else{
            AdrssError.setText("valid adress");
            AdrssError.setTextFill(Color.web("#64DD17", 0.8));

        }
    }


    @FXML
    void NbField_TextChanged(KeyEvent event) {
        if(!(Pattern.matches("[1-9]+",NBField.getText()))){
            NbError.setText("Invalid number of place (EX:50..)");
            NbError.setTextFill(Color.web("#E53935", 0.8));

        }else{
            NbError.setText("Valid number of place");
            NbError.setTextFill(Color.web("#64DD17", 0.8));

        }
    }

    public void initTextFieldForUpdate(int id, String  NomParking, String address, int nbplace, String ville){
        idp = id;
        NomField.setText(NomParking);
        AdrssField.setText(address);
        NBField.setText(String.valueOf(nbplace));
        VillePField.setText(ville);

    }

    private boolean SetErrorMessage(String s) {
        GlobalError.setText(s);
        return false;
    }

    public void setUpdate(boolean b) {
        this.TOF = b;
    }
}
