import animatefx.animation.RotateInDownRight;
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


import model.utilisateur;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import static javafx.scene.paint.Color.TRANSPARENT;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("fxml/MainWindow.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);

        UpdateStage(primaryStage, root, scene);

        primaryStage.show();


    }

    public static void UpdateStage(Stage primaryStage, Parent root, Scene scene) {
        //customize this scene
        scene.setFill(TRANSPARENT);
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.setOpacity(0.98);
        // animation using animateFX
        new RotateInDownRight(root).play();
    }

    public static void main(String[] args) {
        launch(args);
    }



    public void createStage2(){
        Stage stg = new Stage();
        AnchorPane an = new AnchorPane();
        Scene scene = new Scene(an);
        scene.setFill(Color.BLACK);
        stg.setScene(scene);
        stg.show();
    }
}
