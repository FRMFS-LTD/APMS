/*
 * Copyright (c) 2021.
 * programmed by Rachid Boufous.
 * for FRMFS-ltd organisation
 *
 */

package Controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

import java.io.IOException;

public class MainWindowController {


    @FXML
    private JFXButton UsersCrudBtn;

    @FXML
    private StackPane mainPane;

    @FXML
    void UsersCrudBtn_Click(ActionEvent event) {
        System.out.println("hello world");
    }

    @FXML
    private Pane pnlPackages;



    public void HandleCLicks(ActionEvent actionEvent) {
        if (actionEvent.getSource() == UsersCrudBtn) {
            try {

                pnlPackages = FXMLLoader.load(getClass().getResource("/fxml/UserCrud.fxml"));
                mainPane.getChildren().add(pnlPackages);
                //p.setStyle("-fx-background-color : white");
                pnlPackages.toFront();
                System.out.println("hello");




            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
