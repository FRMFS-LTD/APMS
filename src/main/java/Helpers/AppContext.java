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

import animatefx.animation.RotateInDownRight;
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
        scene.setFill(Color.TRANSPARENT);
        primaryStage.setOpacity(0.98);
        // animation using animateFX
        new RotateInDownRight(root).play();
    }


}
