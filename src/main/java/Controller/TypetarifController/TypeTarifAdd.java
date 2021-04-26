/*
 * Copyright (c) 2021.
     * programmed by Fadoua Abdoulmoulah.
 * for FRMFS-ltd organisation
 *
 */

package Controller.TypetarifController;

import com.github.daytron.simpledialogfx.dialog.Dialog;
import com.github.daytron.simpledialogfx.dialog.DialogType;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import dao.Services.typetarifService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.typetarif;




public class TypeTarifAdd {
    typetarifService TTS = new typetarifService();

    @FXML
    private JFXButton addNewTT;

    @FXML
    private JFXButton Cancel;

    @FXML
    private JFXTextField TTField;

    @FXML
    private JFXTextField PrixField;

    @FXML
    private Label GlobalError;

    private int pr;
    private boolean TOF;
    private int typetarifid;

    public void setUpdate(boolean b) {
        this.TOF = b;
    }

    private void CloseForm() {
        Stage stg = (Stage) Cancel.getScene().getWindow();
        stg.close();
    }

    @FXML
    void Cancel_click(ActionEvent event) {

        CloseForm();
    }

    private typetarif createOrupdateNewTT(typetarif TTN) {


        TTN.setTypetarif(TTField.getText());
        TTN.setPrix(Integer.parseInt(PrixField.getText()));

        return TTN ;
    }

    @FXML
    void addNewTypeTarif_click(ActionEvent event) {

        try{


            if(!this.TOF){
                typetarif tp = new typetarif();
                typetarif Tt = newTypetarif(tp);
                if(GeneralException()){
                    TTS.persist(tp);
                    CloseForm();
                }
            }else {
                typetarif TTAD = TTS.findById(pr);
                typetarif TTDA = newTypetarif(TTAD);

                if(GeneralException()){
                    TTS.update(TTDA);
                    CloseForm();
                }
            }
        } catch( Exception e ){
            Dialog dialog = new Dialog(DialogType.ERROR, e.getCause().toString(), e.getMessage());
        }
    }

    private boolean GeneralException() {
        GlobalError.setTextFill(Color.web("#E53935", 0.8));

        if(TTField.getText().isEmpty() || TTField.getText().length()<3){

            return SetErrorMessage("validate rate type  field to conditions");

        }
        else if (PrixField.getText().isEmpty() || PrixField.getText().length()<3){

            return SetErrorMessage("validate price field to given Conditions");

        }else {
            return true;
        }
    }



    private typetarif newTypetarif(typetarif tt) {
        typetarif TT = new typetarif();
        TT.setTypetarif(TTField.getText());
        TT.setPrix(Integer.parseInt(PrixField.getText()));

        return TT;

    }
    private boolean SetErrorMessage(String s) {
        GlobalError.setText(s);
        return false;
    }


    public void initTextFieldForUpdate(int id_typetarif, String  typetarif, float prix){
        typetarifid = id_typetarif;
        TTField.setText(typetarif);
        PrixField.setText(String.valueOf(prix));

    }


}
