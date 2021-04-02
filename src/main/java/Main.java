import animatefx.animation.RotateInDownRight;
import Ddao.Services.typetarifService;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.ArrayList;


import Model.*;

import static javafx.scene.paint.Color.TRANSPARENT;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("fxml/abonnement.fxml"));
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

           Typetarif tf = new Typetarif();
           tf.setTypetarif("by week");
           tf.setPrix(134);

           SRT.persist(tf);

        }

        public void getDate(){

            ArrayList<Typetarif> list = new ArrayList<>();
            list = (ArrayList<Typetarif>) SRT.findAll();
            for(Typetarif t : list){
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
