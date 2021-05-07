package Controller.AbonnementController;

import com.github.daytron.simpledialogfx.dialog.Dialog;
import com.github.daytron.simpledialogfx.dialog.DialogType;
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
import org.hibernate.HibernateException;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class AddAbonnController implements Initializable {
       // Classe pour ajouter une nouvelle abonnement

    @FXML
    private Label intituleErreur ;

    @FXML
    private Label prixErreur ;

    @FXML
    private Label periodeErreur ;

    @FXML
    private JFXTextField IntituleField;

    @FXML
    private JFXTextField PrixField;

    @FXML
    private JFXTextField PeriodeField;

    @FXML
    private JFXButton addNewAbonn;//button cliquable d'ajout

    @FXML
    private JFXButton Cancel;

    @FXML
    private Label ErrorGlobale ;

    private boolean update ;//pour distinguer l'utilisation de ce formulaire pour modifier ou créer

    private int Abonnid;

    AbonnementService as = new AbonnementService();

    public void setUpdate(boolean b) {
        this.update = b;
    }

    @FXML
    void Cancel_click(ActionEvent event) {

        CloseForm();
    }

    private void CloseForm() { //appeler la fonction de formulaire de fermeture
        Stage stage = (Stage) Cancel.getScene().getWindow();
        stage.close();
    }
    @FXML
    void addNewAbonn_click(ActionEvent event) {
     //cette fonction est pour ajouter ou modifier une abonnement
        try {
            if (this.update == false) {

                abonnement abonn = new abonnement();
                abonnement new_abonn = createOrUpdateNewAbonnement(abonn);
                if(GeneralExeption()) {
                    as.persist(new_abonn);
                   CloseForm();
                }

            } else {

                abonnement AbonnE = as.findbyId(Abonnid);
                abonnement AbonnRe = createOrUpdateNewAbonnement(AbonnE);
                if(GeneralExeption()) {
                    as.update(AbonnRe);
                    CloseForm();
                }
            }


        } catch (HibernateException H) {
            Dialog dialog = new Dialog(
                    DialogType.ERROR,
                    "DATABASE ERROR",
                    H.getMessage());
            dialog.showAndWait();
        } catch (Exception e) {
            Dialog dialog = new Dialog(
                    DialogType.ERROR,
                    e.getCause().toString(),
                    e.getMessage());
            dialog.showAndWait();
        }
    }
       private abonnement createOrUpdateNewAbonnement(abonnement  abonn){
   //creer une nouvelle abonnement

        abonn.setIntitule(IntituleField.getText());
        abonn.setPrix(Float.parseFloat(PrixField.getText()));
        abonn.setPeriode(Integer.parseInt(PeriodeField.getText()));


     return abonn;
 }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

  @FXML
  void intitule_TextChanged(KeyEvent event){
      //verification de la taille d'intitule abonnement par la methode length
        if(IntituleField.getText().length() < 4){
            intituleErreur.setText("l'untitule length must be greater than 4");
            intituleErreur.setTextFill(Color.web("#E53935", 0.8));
        }
        else {
            intituleErreur.setText("Valid Intitule");
            intituleErreur.setTextFill(Color.web("#64DD17",0.8));
        }
}

  @FXML
   void prix_TextChanged(KeyEvent event){

        if(PrixField.getText().length() != 0 && PrixField.getText().length() > 1 ){
            prixErreur.setText("Price can't null");
            prixErreur.setTextFill(Color.web("#E53935",0.8));
        }
        else{
            prixErreur.setText("Valid price");
            prixErreur.setTextFill(Color.web("#64DD17",0.8));
        }
}

@FXML
  void periode_TextChanged (KeyEvent event){

        if(PrixField.getText().length() != 0 && PrixField.getText().length() > 1){
            periodeErreur.setText("Period can't be null");
            periodeErreur.setTextFill(Color.web("#E53935",0.8));
        }
        else{
            periodeErreur.setText("Valid Period");
            periodeErreur.setTextFill(Color.web("#64DD17",0.8));
        }
}
//cela chargera l'utilisateur cliqué pour la mise à jour à partir de l'interface Abonnement crud
    public void initTextFieldForUpdate(int id,String intitule,float prix,int periode){

        Abonnid = id;
        IntituleField.setText(intitule);
        PrixField.setText((String.valueOf(prix)));
        PeriodeField.setText(String.valueOf(periode));

    }
    public boolean GeneralExeption(){
//empêcher l'utilisateur de l'application d'ajouter des données aléatoires

        ErrorGlobale.setTextFill(Color.web("#E53935", 0.8));

        if (IntituleField.getText().isEmpty() || IntituleField.getText().length() < 4){

            return SetErrorMessage("validate Title Field to given conditions") ;
        }
       else if (PrixField.getText().isEmpty() || PrixField.getText().length() < 2){

            return SetErrorMessage("validate price field to given Conditions");
        }
        else if (PeriodeField.getText().isEmpty() || PeriodeField.getText().length() < 2){

            return SetErrorMessage("validate Period field to given Conditions");
        }
        else {
            return true ;
        }

    }
    private boolean SetErrorMessage(String s) {
        //set erreur pour la fonction de l'erreur globale
        ErrorGlobale.setText(s);
        return false;
    }
}
