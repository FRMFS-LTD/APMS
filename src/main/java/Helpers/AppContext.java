/*
 * Copyright (c) 2021.
 * programmed by Rachid Boufous.
 * for FRMFS-ltd organisation
 *
 */

package Helpers;/*
 * Copyright (c) 2021.
 * programmed by Rachid Boufous.
 * for FRMFS-ltd organisation
 *
 */

import animatefx.animation.*;
import com.github.daytron.simpledialogfx.data.DialogStyle;
import com.github.daytron.simpledialogfx.dialog.Dialog;
import com.github.daytron.simpledialogfx.dialog.DialogType;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import static javafx.scene.paint.Color.TRANSPARENT;

public class AppContext {

    public static void UpdateStage(Stage primaryStage, Parent root, Scene scene) {
        //customize this scene
        scene.setFill(TRANSPARENT);
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.setOpacity(0.98);
        // animation using animateFX
        new Swing(root).play();
    }

    public static void DragScene(Stage primaryStage, Parent root) {
        final double[] x = new double[1];
        final double[] y = new double[1];
        root.setOnMousePressed(event2 -> {
            x[0] = event2.getSceneX();
            y[0] = event2.getSceneY();
        });
        root.setOnMouseDragged(event3 -> {

            primaryStage.setX(event3.getScreenX() - x[0]);
            primaryStage.setY(event3.getScreenY() - y[0]);

        });
    }

    public static void closeForm(Stage stage){
        stage.close();
    }


    public static void infoDialog(String Header,String Message){
        Dialog dialog = new Dialog(
                DialogType.INFORMATION,
                DialogStyle.UNDECORATED,
                Header,
                Message);
        dialog.showAndWait();
    }
}
