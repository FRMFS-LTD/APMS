
//cette tache fait par :Razzouk Fatima Zohra


package Controller.ClientControllers;


import com.github.daytron.simpledialogfx.dialog.Dialog;
import com.github.daytron.simpledialogfx.dialog.DialogType;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import dao.Services.clientService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.client;
import org.hibernate.HibernateException;


import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Pattern;


public class AddClientController implements Initializable {
    //cette classe permet le mécanisme de création de client


    @FXML
    private JFXButton AddNewClient;

    @FXML
    private JFXButton Cancel;

    @FXML
    private JFXTextField firstname;

    @FXML
    private Label firstnameError;

    @FXML
    private JFXTextField lastname;

    @FXML
    private Label lastnameError;

    @FXML
    private JFXTextField cinclient;
    @FXML
    private Label GlobalError;

    @FXML
    private Label cinError;

    private boolean update;
    private int clientid;

    clientService cs = new clientService();

    public void setUpdate(boolean b) {
        this.update = b;
    }

    private void CloseForm() {

        Stage stage = (Stage) Cancel.getScene().getWindow();
        stage.close();
    }


    @FXML
    void AddNewClient_click(ActionEvent event) {

        try {
            if (this.update == false) {

                client cli = new client();
                client new_client = createOrUpdateNewClient(cli);
                cs.persist(new_client);
                CloseForm();

            } else {

                client cliE = cs.findById(clientid);
                client cliRe = createOrUpdateNewClient(cliE);
                cs.update(cliRe);
                CloseForm();
            }

        } catch (HibernateException Ex ){
            Dialog dialog = new Dialog(
                    DialogType.ERROR,
                    "DATABASE ERROR",
                    Ex.getMessage());
            dialog.showAndWait();
        }
        catch (Exception e){
            Dialog dialog = new Dialog(
                    DialogType.ERROR,
                    e.getCause().toString(),
                    e.getMessage());
            dialog.showAndWait();
        }

    }

    private client createOrUpdateNewClient(client cli) {
        //creation d'un nouveau client
        cli.setNom(lastname.getText());
        cli.setPrenom(firstname.getText());
        cli.setCin(cinclient.getText());
        return cli;
    }

    @FXML
    void cancel_click(ActionEvent event) {
        CloseForm();

    }

    @FXML
    void cinclient_TextChanged(KeyEvent event) {
        if (!(Pattern.matches("^\\w\\w\\d*$", cinclient.getText()))) {
            cinError.setText("Incorrect Id Format (ex: AA111111)");
            cinError.setTextFill(Color.web("#E53935", 0.8));

        } else {
            cinError.setText("Valid Id Format");
            cinError.setTextFill(Color.web("#64DD17", 0.8));

        }

    }

    @FXML
    void firstname_TextChanged(KeyEvent event) {
        if (firstname.getText().length() < 3) {
            firstnameError.setText("FirstName length must be greater than 3");
            firstnameError.setTextFill(Color.web("#E53935", 0.8));

        } else {
            firstnameError.setText("Valid FirstName");
            firstnameError.setTextFill(Color.web("#64DD17", 0.8));

        }

    }

    @FXML
    void lastname_TextChanged(KeyEvent event) {
        if (lastname.getText().length() < 3) {

            lastnameError.setText("LastName length must be greater than 3");
            lastnameError.setTextFill(Color.web("#E53935", 0.8));

        } else {
            lastnameError.setText("Valid LastName");
            lastnameError.setTextFill(Color.web("#64DD17", 0.8));

        }


    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


    }

    public void initTextFieldForUpdate(int id, String prenom, String nom, String cin) {
        // cela chargera le client cliqué pour la mise à jour à partir de l'interface client crud
        clientid = id;
        firstname.setText(prenom);
        lastname.setText(nom);
        cinclient.setText(cin);
    }

    public boolean GeneralExeption() {
        // interdire le client d'ajouter des données aléatoires

        GlobalError.setTextFill(Color.web("#E53935",0.8));

        if (firstname.getText().isEmpty() || firstname.getText().length() < 3) {

            return SetErrorMessage("validate firstName field to conditions");

        } else if (lastname.getText().isEmpty() || lastname.getText().length() < 3) {

            return SetErrorMessage("validate LastName field to given Conditions");
        } else if (cinclient.getText().isEmpty() || cinclient.getText().length() < 4
                || !(Pattern.matches("^\\w\\w\\d*$", cinclient.getText()))) {

            return SetErrorMessage("validate Identity field to given Conditions");
        }else{
            return true;
        }

    }


    private boolean SetErrorMessage(String s) {
        // définit l'erreur pour le message d'erreur global
        GlobalError.setText(s);
        return false;
    } }
