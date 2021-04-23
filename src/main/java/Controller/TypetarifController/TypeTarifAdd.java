/*
 * Copyright (c) 2021.
     * programmed by Fadoua Abdoulmoulah.
 * for FRMFS-ltd organisation
 *
 */

package Controller.TypetarifController;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import dao.Services.parkingService;
import dao.Services.typetarifService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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
          typetarif TT = newTypetarif();
          TTS.persist(TT);
    }

    private typetarif newTypetarif() {
        typetarif TT = new typetarif();
        TT.setTypetarif(TTField.getText());
        TT.setPrix(Integer.parseInt(PrixField.getText()));

        return TT;

    }


}
