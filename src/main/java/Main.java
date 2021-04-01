import animatefx.animation.RotateInDownRight;
import dao.Services.typetarifService;
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
        Parent root = FXMLLoader.load(getClass().getResource("fxml/LogIn.fxml"));
        Scene scene = new Scene(root);
        scene.setFill(TRANSPARENT);
        primaryStage.setScene(scene);
        primaryStage.setTitle("LoginForm");
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.setOpacity(0.95);
        //primaryStage.show();
        new RotateInDownRight(root).play();


        //test();
        getDate();
        getDate();
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






}
