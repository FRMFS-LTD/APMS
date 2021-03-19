package sample;

import dao.DbConnection;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.sql.Connection;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();

        testConnetion();
    }

    public static void main(String[] args) {
        launch(args);
    }



    public void testConnetion(){
        Connection con = DbConnection.getConnection();
        if(con == null){
            System.out.println("Connection Failed");
        }
        else{
            System.out.println("Connection Succeeded");

        }
    }
}
