package Controller.AbonnementController;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import dao.Services.AbonnementService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.Stage;
import model.abonnement;

import java.net.URL;
import java.util.ResourceBundle;

public class AddAbonnController implements Initializable {

    @FXML
    private JFXTextField IdAbonnField;

    @FXML
    private JFXTextField IntituleField;

    @FXML
    private JFXTextField PrixField;

    @FXML
    private JFXTextField PeriodeField;

    @FXML
    private JFXButton addNewAbonn;

    @FXML
    private JFXButton Cancel;

    private boolean update ;
    private int Abonnid;
    AbonnementService as = new AbonnementService();

    public void setUpdate(boolean b) {
        this.update = b;
    }

    @FXML
    void Cancel_click(ActionEvent event) {

        CloseForm();
    }

    private void CloseForm() {
        Stage stage = (Stage) Cancel.getScene().getWindow();
        stage.close();
    }
    @FXML
    void addNewAbonn_click(ActionEvent event) {

            if (this.update == false) {

                abonnement abonn = new abonnement();
                abonnement new_abonn = createOrUpdateNewAbonnement(abonn);
                as.persist(new_abonn);

            } else {

                abonnement AbonnE = as.findbyId(Abonnid);
                abonnement AbonnRe = createOrUpdateNewAbonnement(AbonnE);
                as.update(AbonnRe);

            }
            CloseForm();
        }


 public abonnement createOrUpdateNewAbonnement(abonnement  abonn){


        abonn.setIntitule(IntituleField.getText());
        abonn.setPrix(Float.parseFloat(PrixField.getText()));
        abonn.setPeriode(Integer.parseInt(PeriodeField.getText()));

     return abonn;
 }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
    public void initTextFieldForUpdate(int id,String intitule,float prix,int periode){
        Abonnid = id;
        IntituleField.setText(intitule);
        PrixField.setText((String.valueOf(prix)));
        PeriodeField.setText(String.valueOf(periode));

    }

}
