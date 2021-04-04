

/*
 * Copyright (c) 2021.
 * programmed by Rachid Boufous.
 * for FRMFS-ltd organisation
 *
 */

package Controller;



import animatefx.animation.FadeOut;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


import com.sun.tools.javac.Main;
import dao.Services.UserService;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
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

    @FXML
    private AnchorPane win_pan;


    String userName;
    String password;

    @FXML
    public void loginClick(ActionEvent event) throws Exception {
        setControllerVal();
    }

    public void setControllerVal() throws Exception {
        userName = usernameField.getText();
        password = passwordField.getText();

        if (!password.isEmpty() || !userName.isEmpty()){
            try{
                UserService u_service = new UserService();

                utilisateur loggedUser = u_service.loggedUser(userName,password).get(0);
                System.out.println("welcome: " +  loggedUser.getNom() + " " + loggedUser.getPrenom());

                //StartMainWindow();


            }catch(IndexOutOfBoundsException e){
                e.printStackTrace();

                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("USER does not exist");
                alert.setHeaderText("invalid Username or Password");
                alert.setContentText("try again!!");

                alert.showAndWait();
            }


        }
        else{
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Empty Fields Error");
            alert.setHeaderText("Username or Password are not supplied");
            alert.setContentText("fill in the fields and try again!!");

            alert.showAndWait();
        }


    }

    private void StartMainWindow() throws  Exception {
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("fxml/MainWindow.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
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


    Double xOffset = 0.0;
    Double yOffset = 0.0;
    /*
    @FXML
    void win_mouse_pressed(MouseEvent event) {

        xOffset = stage.getX() - event.getScreenX();
        yOffset = stage.getY() - event.getScreenY();
    }
    @FXML
    void win_mouse_dragged(MouseEvent event) {

        stage.setX(event.getScreenX() + xOffset);
        stage.setY(event.getScreenY() + yOffset);

    }


     */

}
