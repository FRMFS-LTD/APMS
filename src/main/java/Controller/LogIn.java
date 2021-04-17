

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
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;
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
    private StackPane MainContainer;

    @FXML
    private JFXButton pwdResetButton;

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

    public void Exit_onClick(MouseEvent mouseEvent)  {
        Stage stage = (Stage) closeBtn.getScene().getWindow();
        // do what you have to do
        stage.close();
    }


    @FXML
    void pwdResetButton_click(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("/fxml/pwdReset.fxml"));
        Scene scene =  pwdResetButton.getScene();

        root.translateYProperty().set(scene.getHeight());
        MainContainer.getChildren().add(root);

        Timeline tl = new Timeline();
        KeyValue kv = new KeyValue(root.translateYProperty(),0, Interpolator.EASE_IN);
        KeyFrame kf = new KeyFrame(Duration.seconds(1),kv);
        tl.getKeyFrames().add(kf);
        tl.setOnFinished(event1 ->{
            MainContainer.getChildren().remove(win_pan);
        } );
        tl.play();

    }

}
