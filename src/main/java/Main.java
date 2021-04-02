import animatefx.animation.RotateInDownRight;
import dao.Services.typetarifService;
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
import java.util.ArrayList;


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
        Parent root = FXMLLoader.load(getClass().getResource("fxml/MainWindow.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);

        primaryStage.setTitle("LoginForm");
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.setOpacity(0.95);
        //primaryStage.show();
        new RotateInDownRight(root).play();


        UpdateStage(primaryStage, root, scene);

        primaryStage.show();
        


        //test();
        getDate();
        getDate();
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
    typetarifService SRT = new typetarifService();
        public void test (){

           typetarif tf = new typetarif();
           tf.setTypetarif("by week");
           tf.setPrix(134);

           SRT.persist(tf);

        }

        public void getDate(){

            ArrayList<typetarif> list = new ArrayList<>();
            list = (ArrayList<typetarif>) SRT.findAll();
            for(typetarif t : list){
                System.out.println(t.toString());
            }

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
