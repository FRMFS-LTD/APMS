package Controller.AbonnementController;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import dao.Services.AbonnementService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.abonnement;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class AddAbonnController implements Initializable {

    @FXML
    private Label IntituleError;

    @FXML
    private Label PrixError;

    @FXML
    private Label PeriodeError;


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

    @FXML
    void IntituleField_TextChanged(KeyEvent event) {

        if( IntituleField.getText().length() < 10 )
        {
            IntituleField.setText("La taille d'intitule doit etre inferieur à 10");
            IntituleError.setTextFill(Color.web("#E53935", 0.8));

        }else{
            IntituleError.setText("Intutule validé");
            IntituleError.setTextFill(Color.web("#64DD17", 0.8));

        }

    }

    @FXML
    void PrixField_textChanged(KeyEvent event) {


        if( !(Pattern.matches("^[+]*[(]{0,1}[0-9]{1,4}[)]{0,1}[-\\s\\./0-9]*$", PrixField.getText())))
        {
            PrixError.setText("Le prix doit pas contient des lettres !");
            PrixError.setTextFill(Color.web("#E53935", 0.8));

        }else{
            PrixError.setText("prix acceptable");
            PrixError.setTextFill(Color.web("#64DD17", 0.8));

        }

    }

    @FXML
    void PeriodeField_textChanged(KeyEvent event) {


        if( !(Pattern.matches("^[+]*[(]{0,1}[0-9]{1,4}[)]{0,1}[-\\s\\./0-9]*$", PeriodeField.getText())) && PeriodeField.getText().length() < 3)
        {
            PeriodeField.setText("Le prix doit pas contient des lettres et il ne depase pas 2 chiffre !");
            PrixError.setTextFill(Color.web("#E53935", 0.8));

        }else{
            PeriodeError.setText("Periode acceptable");
            PeriodeError.setTextFill(Color.web("#64DD17", 0.8));

        }

    }

    public void initTextFieldForUpdate(int id,String intitule,float prix,int periode){
        Abonnid = id;
        IntituleField.setText(intitule);
        PrixField.setText((String.valueOf(prix)));
        PeriodeField.setText(String.valueOf(periode));

    }

}
