import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import Helpers.AppContext;



public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
<<<<<<< HEAD


        Parent root = FXMLLoader.load(getClass().getResource("/fxml/UserViews/LogIn.fxml"));

=======
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/UserViews/LogIn.fxml"));
        //Parent root = FXMLLoader.load(getClass().getResource("/fxml/ParkingView/Parking.fxml"));
>>>>>>> 312ba82616733ab678b608b6a8a9ca1b3f5d39f3
        Scene scene = new Scene(root);

        primaryStage.setScene(scene);
        AppContext.UpdateStage(primaryStage, root, scene); // round the corners of the start form
        AppContext.DragScene(primaryStage,root); // implement drag func to start form

        primaryStage.show();
    }




    public static void main(String[] args) {
        launch(args);
    }





}
