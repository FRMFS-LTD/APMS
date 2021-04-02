package Controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ResourceBundle;

public class abonnement  implements Initializable {

    @FXML
    private AnchorPane menu;

    @FXML
    private Button acceuil;

    @FXML
    private Button mouvement;

    @FXML
    private Button tarif;

    @FXML
    private Button vehicule;

    @FXML
    private Button abonnement;

    @FXML
    private Button utilisateur;

    @FXML
    private Button ajouterAbonn;

    @FXML
    private Button ModifierAbonn;

    @FXML
    private Button suppAbonn;

    @FXML
    private TextField tf_id_abonn;

    @FXML
    private TextField tf_Intitule;

    @FXML
    private TextField tf_prix;

    @FXML
    private TextField tf_periode;

    @FXML
    private TableView<model.abonnement> tableAbonn;

    @FXML
    private TableColumn<model.abonnement, Integer> id_abonn_t;

    @FXML
    private TableColumn<model.abonnement, String> Intitule_t;

    @FXML
    private TableColumn<model.abonnement, Float> prix_t;

    @FXML
    private TableColumn<model.abonnement, Integer> Periode_t;

    @FXML
    void AjouterAbonn(ActionEvent event) {


    }

    @FXML
    void ModifierAbonn(ActionEvent event) {

    }

    @FXML
    void SupprimerAbonn(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public Connection getConnection(){
        Connection cnx ;
        try{
            cnx = DriverManager.getConnection("","","");
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return null ;
    }
}
