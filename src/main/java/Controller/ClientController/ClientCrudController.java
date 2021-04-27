
package Controller.ClientController;
import Helpers.AppContext;
import com.github.daytron.simpledialogfx.data.DialogResponse;
import com.github.daytron.simpledialogfx.dialog.Dialog;
import com.github.daytron.simpledialogfx.dialog.DialogType;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import dao.Services.clientService;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import model.client;


import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClientCrudController implements Initializable {


        @FXML
        private JFXTextField SearchTextField;

        @FXML
        private JFXButton AddClient;

        @FXML
        private JFXButton Refreshbutton;

    ObservableList<client> ClientsList = FXCollections.observableArrayList();
    clientService cservice  = new clientService();

        @FXML
        private TableView<client> ClientTable;

        @FXML
        private TableColumn<client, String> idCol;

        @FXML
        private TableColumn<client, String> firstcol;

        @FXML
        private TableColumn<client, String> lastcol;

        @FXML
        private TableColumn<client, String> cincol;

        @FXML
        private TableColumn<client, String> namecol;






    @FXML
        void AddClient_click(ActionEvent event) throws IOException {


        Stage primaryStage = new Stage();
        Parent root= FXMLLoader.load(getClass().getResource("/fxml/AddClient.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        AppContext.UpdateStage(primaryStage, root, scene);
        AppContext.DragScene(primaryStage, root);

        primaryStage.show();

        }

        @FXML
        void Refreshbutton_click(ActionEvent event) {
            refreshDataSet();


        }

    private void refreshDataSet() {
        ClientsList.clear();

        ArrayList<client> cl = (ArrayList<client>) cservice.findAll();

        for (client c: cl) {

            ClientsList.add(c);
        }
        ClientTable.setItems(ClientsList);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        LoadData();
        CreateIcons();
        FilterSearch();

    }

    private void FilterSearch() {
        FilteredList<client> filteredData = new FilteredList<>(ClientsList, b -> true);
        SearchTextField.textProperty().addListener((observable, oldValue, newValue) -> {

            filteredData.setPredicate(
                    client -> {
                        if(newValue == null || newValue.isEmpty()){
                            return  true;
                        }
                        String lowerCaseFilter = newValue.toLowerCase();

                        if (client.getNom().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
                            return true;
                        } else if (client.getPrenom().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                            return true;
                        }
                        else if (String.valueOf(client.getCin()).indexOf(lowerCaseFilter)!=-1)
                            return true;
                        else
                            return false;
                    });
        });
        SortedList<client> sortedData = new SortedList<>(filteredData);


        sortedData.comparatorProperty().bind(ClientTable.comparatorProperty());

        ClientTable.setItems(sortedData);

    }

    private void CreateIcons() {
        Callback<TableColumn<client,String>, TableCell<client,String>> cellFactory = (
                TableColumn<client,String> param) ->{

            final TableCell<client,String> cell = new TableCell<client,String>(){

                @Override
                protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);

                    if(empty){
                        setGraphic(null);
                        setText(null);
                    }else{

                        FontAwesomeIconView DeleteIco = new FontAwesomeIconView(FontAwesomeIcon.TRASH);
                        FontAwesomeIconView EditIco = new FontAwesomeIconView(FontAwesomeIcon.PENCIL_SQUARE_ALT);

                        StyleIcons(DeleteIco, EditIco);


                        DeleteIco.setOnMouseClicked((MouseEvent event)->
                        {
                            DeleteClientConfirmation();
                        });

                        EditIco.setOnMouseClicked((MouseEvent EditEvent)->
                        {
                            client cli = ClientTable.getSelectionModel().getSelectedItem();

                            LoadClientIntoUpdateForm(cli);

                        });


                        SetIconsToTabViewCell(DeleteIco, EditIco);

                    }

                }

                private void LoadClientIntoUpdateForm(client cli) {
                    FXMLLoader loader = new FXMLLoader ();
                    loader.setLocation(getClass().getResource("/fxml/AddClient.fxml"));
                    try {
                        loader.load();
                    } catch (IOException ex) {
                        Logger.getLogger(AddClientController.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    AddClientController addClientController = loader.getController();
                    addClientController.setUpdate(true);
                    addClientController.initTextFieldForUpdate(cli.getId(),cli.getPrenom(),cli.getNom(),cli.getCin()

                    );

                    Parent parent = loader.getRoot();
                    Stage stage = new Stage();
                    Scene scene = new Scene(parent);
                    stage.setScene(scene);
                    stage.initStyle(StageStyle.UTILITY);
                    AppContext.UpdateStage(stage,parent,scene);
                    AppContext.DragScene(stage,parent);
                    stage.show();
                }

                private void SetIconsToTabViewCell(FontAwesomeIconView DeleteIco, FontAwesomeIconView EditIco) {
                    HBox managebtn = new HBox(DeleteIco, EditIco);
                    managebtn.setStyle("-fx-alignment:center");

                    HBox.setMargin(DeleteIco, new Insets(2, 2, 0, 3));
                    HBox.setMargin(EditIco, new Insets(2, 3, 0, 2));

                    setGraphic(managebtn);
                }

                private void StyleIcons(FontAwesomeIconView DeleteIco, FontAwesomeIconView EditIco) {
                    DeleteIco.setGlyphSize(26);
                    DeleteIco.setFill(Color.rgb(251, 62, 56));
                    DeleteIco.setCursor(Cursor.HAND);


                    EditIco.setGlyphSize(26);
                    EditIco.setFill(Color.rgb(66, 66, 66));
                    EditIco.setCursor(Cursor.HAND);
                }
            };

            return cell;
        };
        namecol.setCellFactory(cellFactory);

    }

    private void DeleteClientConfirmation() {
        client cli = ClientTable.getSelectionModel().getSelectedItem();


        Dialog dialog = new Dialog(
                DialogType.CONFIRMATION,
                "Delete Client action",
                "Confirm Action",
                "Are you sure you want to delete " + cli.getNom() + " " + cli.getPrenom() + "?");

        dialog.showAndWait();

        if (dialog.getResponse() == DialogResponse.YES) {
            cservice.delete(cli.getId());
            refreshDataSet();
        }
    }



    private void LoadData() {
        DefineCols();

        refreshDataSet();
    }

    private void DefineCols() {
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        firstcol.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        lastcol.setCellValueFactory(new PropertyValueFactory<>("nom"));
        cincol.setCellValueFactory(new PropertyValueFactory<>("cin"));

    }
}

