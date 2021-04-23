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
import dao.Services.parkingService;
import dao.Services.typetarifService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.Stage;
import model.typetarif;
import org.hibernate.HibernateException;

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

    private int pr;
    private boolean TOF;

    private void CloseForm() {
        Stage stg = (Stage) Cancel.getScene().getWindow();
        stg.close();
    }

    @FXML
    void Cancel_click(ActionEvent event) {

        CloseForm();
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
        } catch (HibernateException eh) {

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

    private typetarif newTypetarif(typetarif tt) {
        typetarif TT = new typetarif();
        TT.setTypetarif(TTField.getText());
        TT.setPrix(Integer.parseInt(PrixField.getText()));

        return TT;

    }


}
