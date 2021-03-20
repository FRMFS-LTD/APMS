import dao.DbConnection;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.awt.*;
import java.sql.Connection;

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
