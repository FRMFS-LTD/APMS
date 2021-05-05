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
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.typetarif;
import org.hibernate.HibernateException;

import java.util.regex.Pattern;


public class TypeTarifAdd {

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

    @FXML
    private Label TTError;

    @FXML
    private Label PrixError;

    typetarifService TTSS = new typetarifService();
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

    private typetarif createOrupdateNewTT(typetarif TT) {


        TT.setTypetarif(TTField.getText());
        TT.setPrix(Integer.parseInt(PrixField.getText()));

        return TT ;
    }

    @FXML
    void addNewTypeTarif_click(ActionEvent event) {

        try {

            if (!this.TOF) {
                if (GeneralException()) {

                    typetarif TT = new typetarif();
                    typetarif newTT = newTypetarif(TT);
                    TTSS.persist(newTT);
                    CloseForm();
                }
            } else {
                typetarif TTAd = TTSS.findById(typetarifid);
                typetarif TTDa = newTypetarif(TTAd);

                if (GeneralException()) {
                    TTSS.update((TTDa));
                    CloseForm();
                }
            }
        }
        catch(HibernateException eh ){
                Dialog dialog = new Dialog(
                        DialogType.ERROR, "Database Error", eh.getMessage());
                dialog.showAndWait();
            }


        catch( Exception e ) {
            Dialog dialog = new Dialog(
                    DialogType.ERROR, e.getCause().toString(), e.getMessage());
        }
    }

        private boolean GeneralException() {
        GlobalError.setTextFill(Color.web("#E53935", 0.8));

        if(TTField.getText().isEmpty() || TTField.getText().length()<3 ){

            return SetErrorMessage("validate rate type  field to conditions");

        }
        else if (PrixField.getText().isEmpty()){

            return SetErrorMessage("validate price field to given Conditions");

        }else {
            return true;
        }
    }

    @FXML
    void TTField_TextChanged(KeyEvent event) {
        if(TTField.getText().isEmpty()){
            TTError.setText("Invalid name of price type (EX:Weekly..)");
            TTError.setTextFill(Color.web("#E53935", 0.8));

        }else{
            TTError.setText("Valid name of price type");
            TTError.setTextFill(Color.web("#64DD17", 0.8));

        }
    }

    @FXML
    void PrixField_TextChanged(KeyEvent event) {
        if(!(Pattern.matches("[1-9]+",PrixField.getText()))){
            PrixError.setText("Invalid price (EX:120..)");
            PrixError.setTextFill(Color.web("#E53935", 0.8));

        }else{
            PrixError.setText("Valid Price");
            PrixError.setTextFill(Color.web("#64DD17", 0.8));

        }
    }

    private typetarif newTypetarif(typetarif tt) {

        tt.setTypetarif(TTField.getText());
        tt.setPrix(Integer.parseInt(PrixField.getText()));

        return tt;

    }
    private boolean SetErrorMessage(String s) {
        GlobalError.setText(s);
        return false;
    }


    public void initTextFieldForUpdate(int id, String  typetarif, float prix){
        //typetarifid is the id that call it in the first of this class and it will be giving to point on in the findById
        typetarifid = id;
        TTField.setText(typetarif);
        PrixField.setText(String.valueOf(prix));

    }


}
