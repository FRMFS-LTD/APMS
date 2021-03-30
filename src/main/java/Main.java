import animatefx.animation.RotateInDownRight;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


import model.utilisateur;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import static javafx.scene.paint.Color.TRANSPARENT;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("fxml/LogIn.fxml"));
        Scene scene = new Scene(root);
        scene.setFill(TRANSPARENT);
        primaryStage.setScene(scene);
        primaryStage.setTitle("LoginForm");
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.setOpacity(0.95);
        primaryStage.show();
        new RotateInDownRight(root).play();
        //testConnetion();
        // testHiberante();
    }

    public static void main(String[] args) {
        launch(args);
    }




}
