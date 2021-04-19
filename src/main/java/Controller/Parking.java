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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;



public class Parking {
    @FXML
    private JFXButton RefreshBtn;

    @FXML
    private JFXButton AddInfoParking;

    @FXML
    private TableView<?> UsersTable;

    @FXML
    private TableColumn<?, ?> AdrssCol;

    @FXML
    private TableColumn<?, ?> VilleCol;

    @FXML
    private TableColumn<?, ?> NbPCol;

    @FXML
    private TableColumn<?, ?> NbPLCol;

    @FXML
    void AddNewParking_click(ActionEvent event) {

    }

    @FXML
    void RefreshBtn_click(ActionEvent event) {

    }

}
