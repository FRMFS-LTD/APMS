import animatefx.animation.RotateInDownRight;
import dao.Services.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


import model.utilisateur;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import model.*;

import static javafx.scene.paint.Color.TRANSPARENT;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("fxml/LogIn.fxml"));
        Scene scene = new Scene(root);

        primaryStage.setScene(scene);
        UpdateStage(primaryStage, root, scene);

        primaryStage.show();
    }


    public static void UpdateStage(Stage primaryStage, Parent root, Scene scene) {
        //customize this scene
        scene.setFill(TRANSPARENT);
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        scene.setFill(Color.TRANSPARENT);
        primaryStage.setOpacity(0.98);
        // animation using animateFX
        new RotateInDownRight(root).play();
    }

    public static void main(String[] args) {
        launch(args);
    }





}
