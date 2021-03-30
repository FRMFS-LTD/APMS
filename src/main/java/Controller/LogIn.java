package Controller;



import animatefx.animation.FadeOut;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


import dao.Services.UserService;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.utilisateur;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;


import java.util.ArrayList;
import java.util.List;

public class LogIn {

    @FXML
    private JFXTextField usernameField;

    @FXML
    private JFXButton loginBtn;

    @FXML
    private JFXPasswordField passwordField;

    @FXML
    private FontAwesomeIconView closeBtn;



    String userName;
    String password;

    @FXML
    public void loginClick(ActionEvent event) {
        System.out.println("hello world");
        setControllerVal();
    }

    public void setControllerVal(){
        userName = usernameField.getText();
        password = passwordField.getText();
        if (!password.isEmpty() || !userName.isEmpty()){
            UserService u_service = new UserService();
            utilisateur loggedUser = u_service.loggedUser(userName,password).get(0);
            System.out.println("welcome: " +  loggedUser.getNom() + " " + loggedUser.getPrenom());
        }
        else{
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Empty Fields Error");
            alert.setHeaderText("Username or Password are not supplied");
            alert.setContentText("fill in the fields and try again!!");

            alert.showAndWait();
        }


    }
/*
    private utilisateur getLoggedUser(String username,String pwd){
        Configuration conf =  new Configuration().configure("utils/hibernate.cfg.xml").addAnnotatedClass(utilisateur.class);
        SessionFactory sf = conf.buildSessionFactory();
        Session session = sf.openSession();
        Transaction tx = session.beginTransaction();

        String sql = "SELECT * FROM utilisateur WHERE username = :username and password = :password";
        SQLQuery query = session.createSQLQuery(sql);

        query.addEntity(utilisateur.class);
        query.setParameter("username", username);
        query.setParameter("password", pwd);

        ArrayList<utilisateur> results = (ArrayList<utilisateur>) query.list();
        tx.commit();



        System.out.println(results.get(0).getPassword());
        return results.get(0);
    }


 */

    public void Exit_onClick(MouseEvent mouseEvent)  {
        Stage stage = (Stage) closeBtn.getScene().getWindow();
        // do what you have to do
        stage.close();
    }
}
