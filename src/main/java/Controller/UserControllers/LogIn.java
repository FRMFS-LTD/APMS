

/*
 * Copyright (c) 2021.
 * programmed by Rachid Boufous.
 * for FRMFS-ltd organisation
 *
 */

package Controller.UserControllers;




import com.github.daytron.simpledialogfx.data.DialogStyle;
import com.github.daytron.simpledialogfx.dialog.Dialog;
import com.github.daytron.simpledialogfx.dialog.DialogType;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

import java.io.IOException;


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
import Helpers.AppContext;

public class LogIn {
    // log in mechanism

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
        // used to log the user on by username and password
        userName = usernameField.getText();
        password = passwordField.getText();

        if (!password.isEmpty() || !userName.isEmpty()){
            try{
                UserService u_service = new UserService();

                utilisateur loggedUser = u_service.loggedUser(userName,password).get(0);
                System.out.println("welcome: " +  loggedUser.getNom() + " " + loggedUser.getPrenom());

                StartMainWindow();


            }catch(IndexOutOfBoundsException e){
                e.printStackTrace();

                Dialog dialog = new Dialog(
                        DialogType.WARNING,
                        DialogStyle.UNDECORATED,
                        "invalid Username or Password",
                        "try again!!");
                // throw an error if the list length is zero: no user has been found
                dialog.showAndWait();
            }


        }
        else{
            Dialog dialog = new Dialog(
                    DialogType.WARNING,
                    DialogStyle.UNDECORATED,
                    "Username or Password are not supplied",
                    "fill in the fields and try again!!");
            dialog.showAndWait();
        }


    }

    private void StartMainWindow() throws  Exception {
        // in case of a success log in start the main dash board
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/MainWindow.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);

        // start the main form
        AppContext.UpdateStage(primaryStage,root,scene);
        AppContext.DragScene(primaryStage,root);


        // close the log in form
        Stage stage = (Stage) passwordField.getScene().getWindow();
        AppContext.closeForm(stage);

        primaryStage.show();

    }

    public void Exit_onClick(MouseEvent mouseEvent)  {
        Stage stage = (Stage) closeBtn.getScene().getWindow();

        AppContext.closeForm(stage);
    }


    @FXML
    void pwdResetButton_click(ActionEvent event) throws IOException {

        // this will be used to redirect the user to reset password form
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/UserViews/pwdReset.fxml"));
        Scene scene =  pwdResetButton.getScene();


        // this will allow us to load the reset form via nice animation
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
