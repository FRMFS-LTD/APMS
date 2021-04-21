import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import Helpers.AppContext;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/VehiculeViews/VehiculeCrud.fxml"));
        Scene scene = new Scene(root);

        primaryStage.setScene(scene);
        AppContext.UpdateStage(primaryStage, root, scene);
        AppContext.DragScene(primaryStage,root);

        primaryStage.show();
    }




    public static void main(String[] args) {
        launch(args);
    }





}
