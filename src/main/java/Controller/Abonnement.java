package Controller;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class Abonnement {

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
    private TableView<?> tableAbonn;

    @FXML
    private TableColumn<?, ?> id_abonn_t;

    @FXML
    private TableColumn<?, ?> Intitule_t;

    @FXML
    private TableColumn<?, ?> prix_t;

    @FXML
    private TableColumn<?, ?> Periode_t;

    @FXML
    void AjouterAbonn() {

    }

    @FXML
    void ModifierAbonn() {

    }

    @FXML
    void SupprimerAbonn() {

    }
}
