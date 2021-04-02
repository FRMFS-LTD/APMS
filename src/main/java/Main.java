import animatefx.animation.RotateInDownRight;
import dao.Services.AbonnementService;
import dao.Services.VehiculeService;
import dao.Services.clientService;
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
        Parent root = FXMLLoader.load(getClass().getResource("fxml/LogIn.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        scene.setFill(Color.TRANSPARENT);
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        //UpdateStage(primaryStage, root, scene);

        primaryStage.show();

        //test();
        //getDate();
        //getDate();

        testVehicle();

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

    public void testVehicle(){
        VehiculeService vs = new VehiculeService();


        // ajouter
        //abonnement ab = new abonnement(2,"weekly",7,30);
        client cl = new client(2,"jhon","DOE","UDX55");

        Vehicule v1 = new Vehicule();
        v1.setMatriucle("MFZ154");
        v1.setClient(cl);
        //v1.setAbonnement(ab);
        //v1.setClient(cl);

        vs.persist(v1);
        System.out.println(v1.toString());
    }

}
