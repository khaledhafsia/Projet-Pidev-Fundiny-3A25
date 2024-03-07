package org.example.Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.example.Entities.Reponse;
import org.example.utils.MyDataBase;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class reponseViewController implements javafx.fxml.Initializable {

    @FXML
    private TableView<Reponse> ReponseTables;

    @FXML
    private TableColumn<Reponse, Integer> ColIdReponse;

    @FXML
    private TableColumn<Reponse, String> Colemail;

    @FXML
    private TableColumn<Reponse, String> Coluser;

    @FXML
    private TableColumn<Reponse, String> Colobjet;

    @FXML
    private TableColumn<Reponse, Void> Colaction;

    private final ObservableList<Reponse> RepList = FXCollections.observableArrayList();

    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadDate();
    }

    private void loadDate() {
        ReponseTables.setItems(RepList);
        try {
           // RepList.addAll(MyDataBase.getInstance().getAllReponse());

            ColIdReponse.setCellValueFactory(new PropertyValueFactory<>("ID_Reponse"));
            Colemail.setCellValueFactory(new PropertyValueFactory<>("email"));
            Coluser.setCellValueFactory(new PropertyValueFactory<>("ID_Utilisateur"));
            Colobjet.setCellValueFactory(new PropertyValueFactory<>("objet"));

            // Use a Callback to create custom cells with delete and edit buttons
            Colaction.setCellFactory(param -> new TableCell<>() {
                private final Button deleteButton = new Button("Delete");
                private final Button editButton = new Button("Edit");

                {
                    deleteButton.setStyle("-fx-cursor: hand; -fx-base: #ff1744;");
                    editButton.setStyle("-fx-cursor: hand; -fx-base: #00E676;");

                    deleteButton.setOnAction(event -> handleDeleteReponse());
                    editButton.setOnAction(event -> handleEditReponse());

                    HBox buttons = new HBox(deleteButton, editButton);
                    buttons.setStyle("-fx-alignment:center");
                    setGraphic(buttons);
                }

                private void handleDeleteReponse() {
                    Reponse selectedReponse = getTableView().getItems().get(getIndex());
                    if (selectedReponse != null) {
                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                        alert.setTitle("Delete Confirmation");
                        alert.setHeaderText("Are you sure you want to delete this reclamation?");
                        alert.setContentText("This action cannot be undone.");

                        Optional<ButtonType> result = alert.showAndWait();
                        if (result.isPresent() && result.get() == ButtonType.OK) {
                         //   MyDataBase.getInstance().deleteReponse(selectedReponse);
                            RepList.remove(selectedReponse);
                            ReponseTables.refresh(); // Refresh the TableView
                        }
                    }
                }

                private void handleEditReponse() {
                    Reponse selectedReponse = getTableView().getItems().get(getIndex());
                    if (selectedReponse != null) {
                        try {
                            // Load the FXML file for the edit window
                            FXMLLoader loader = new FXMLLoader(getClass().getResource("/addReponse.fxml"));
                            Parent parent = loader.load();

                            // Get the controller from the loader
                            AddReclamationControllers editController = loader.getController();

                            // Pass the selected Reclamation to the controller
                            //editController.setReclamation(selectedReclamation);

                            // Create a new scene with the parent
                            Scene scene = new Scene(parent);

                            // Create a new stage for the edit window
                            Stage stage = new Stage();
                            stage.setScene(scene);
                            stage.initStyle(StageStyle.UTILITY);

                            // Show the edit window
                            stage.show();
                        } catch (IOException e) {
                            Logger.getLogger(reponseViewController.class.getName()).log(Level.SEVERE, null, e);
                        }
                    }
                }
            });

            ReponseTables.setItems(RepList);

        } catch (Exception e) {
            Logger.getLogger(reponseViewController.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    @FXML
    private void getAddReponse(MouseEvent event) {
        System.out.println("Add button clicked!");

        try {
            Parent parent = FXMLLoader.load(getClass().getResource("/addReponse.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initStyle(StageStyle.UTILITY);
            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            currentStage.close();
            stage.show();
        } catch (IOException e) {
            Logger.getLogger(reponseViewController.class.getName()).log(Level.SEVERE, null, e);
        }
    }
}
