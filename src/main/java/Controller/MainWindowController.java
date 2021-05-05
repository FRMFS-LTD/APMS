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
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

import java.io.IOException;

public class MainWindowController {


    @FXML
    private JFXButton statsBtn;

    @FXML
    private JFXButton AbonnCrudBtn;

    @FXML
    private JFXButton typeTarifBtn;

    @FXML
    private JFXButton parkinBtn;

    @FXML
    private JFXButton HomeBtn;

    @FXML
    private JFXButton CarsBtn;

    @FXML
    private JFXButton CustomersBtn;

    @FXML
    private JFXButton UsersCrudBtn;

    @FXML
    private JFXButton parkingLots;

    @FXML
    private StackPane mainPane;


    @FXML
    void UsersCrudBtn_Click(ActionEvent event) {
        System.out.println("hello world");
    }

    @FXML
    private Pane pnlPackages;



    public void HandleCLicks(ActionEvent actionEvent) {
        String ViewPath = "";
        if (actionEvent.getSource() == UsersCrudBtn) {
            ViewPath = "/fxml/UserViews/UserCrud.fxml";

        }

        if (actionEvent.getSource() == AbonnCrudBtn) {
            ViewPath = "/fxml/AbonnementCrud.fxml";
        }

       if(actionEvent.getSource() == typeTarifBtn){
           ViewPath = "/fxml/Typetarifview/TypeTarifView.fxml";
       }

        if(actionEvent.getSource() == CustomersBtn){
            ViewPath = "/fxml/ClientViews/ClientCrud.fxml";
        }

        /*
        if(actionEvent.getSource() == statsBtn){
            ViewPath = "/fxml/Typetarifview/TypeTarifView.fxml.fxml";
        }
         */

        if(actionEvent.getSource() == parkinBtn){
            ViewPath = "/fxml/ParkingView/Parking.fxml";
        }


                /*
        if(actionEvent.getSource() == HomeBtn){
            //ViewPath = /fxml/ParkingView/Parking.fxml";
        }

                 */

        if(actionEvent.getSource() == CarsBtn){
            ViewPath = "/fxml/VehiculeViews/VehiculeCrud.fxml";
        }

        if(actionEvent.getSource() == parkingLots){
            ViewPath = "/fxml/StatViews/stationnement.fxml";
        }
        SlideViewToMainWindow(ViewPath);
    }

    private void SlideViewToMainWindow(String ViewPath) {
        try {

            pnlPackages = FXMLLoader.load(getClass().getResource(ViewPath));
            mainPane.getChildren().add(pnlPackages);
            //p.setStyle("-fx-background-color : white");
            pnlPackages.toFront();




            } catch (IOException e) {
                e.printStackTrace();
            }


    }
}
