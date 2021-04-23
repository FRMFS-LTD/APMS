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
import javafx.stage.Stage;
import model.parking;
import org.hibernate.HibernateException;


public class ParkingAdd {
   parkingService PS = new parkingService();
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

    private int id_parking ;
    private boolean TOF ;
    @FXML
    void Cancel_click(ActionEvent event) {
        CloseForm();

    }

    private void CloseForm() {
        Stage stg  = (Stage) Cancel.getScene().getWindow();
        stg.close();
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

    private boolean GeneralException() {
        return false;
    }


    private parking newParking(parking parkE){
        parking park = new parking();

        park.setVille(VillePField.getText());
        park.setAddress(AdrssField.getText());
        park.setNbplace(Integer.parseInt(NBField.getText()));

        return park;
    }


}
