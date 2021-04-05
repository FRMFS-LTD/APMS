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
        scene.setFill(Color.TRANSPARENT);
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        //UpdateStage(primaryStage, root, scene);

        primaryStage.show();



        //testVehicle();

        testStationnement();
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

            clientService cs = new clientService();
            VehiculeService vs = new VehiculeService();
            AbonnementService as = new AbonnementService();

            client cl = new client("jhon","doe","UK7989");
            abonnement ab = new abonnement("weekly",7,35);
            Vehicule vc = new Vehicule( "A4987",cl,ab);

            System.out.println(cl.toString());
            System.out.println(vc.toString());


            cs.persist(cl);
            as.persist(ab);
            vs.persist(vc);





    }

    public void testStationnement() throws ParseException {

        StationnementService ss = new StationnementService();
        parkingService ps = new parkingService();
        typetarifService ts = new typetarifService();

        parking p = new parking("21ST",22,22,"LA");
        typetarif tt = new typetarif("daily",22);

        Vehicule v = new Vehicule("AX4565");



        Date d1 = new SimpleDateFormat("dd/MM/yyyy").parse("11/11/2015");
        Date d2 = new SimpleDateFormat("dd/MM/yyyy").parse("12/11/2015");

        Stationnement st = new Stationnement(d1,d2,p,v,tt);

        ps.persist(p);
        ts.persist(tt);
        ss.persist(st);

    }

}
