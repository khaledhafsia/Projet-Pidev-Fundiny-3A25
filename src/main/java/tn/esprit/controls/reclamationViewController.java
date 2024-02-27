package tn.esprit.controls;

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
import tn.esprit.models.Reclamation;
import tn.esprit.utils.MyDataBase;

import java.io.IOException;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

public class reclamationViewController implements javafx.fxml.Initializable {

    @FXML
    private TableView<Reclamation> reclamationTables;

    @FXML
    private TableColumn<Reclamation, Integer> ColIdReclamation;

    @FXML
    private TableColumn<Reclamation, String> Colemail;

    @FXML
    private TableColumn<Reclamation, String> Coladmin;

    @FXML
    private TableColumn<Reclamation, String> Colprojet;

    @FXML
    private TableColumn<Reclamation, String> Colprobleme;

    @FXML
    private TableColumn<Reclamation, String> Colobjet;

    @FXML
    private TableColumn<Reclamation, Void> Colaction;  // Change TableColumn type to Void

    private final ObservableList<Reclamation> ReclamList = FXCollections.observableArrayList();

    public void initialize(java.net.URL url, java.util.ResourceBundle resourceBundle) {
        loadDate();
    }

    private void loadDate() {
        try {
            ReclamList.addAll(MyDataBase.getInstance().getAllReclamations());

            ColIdReclamation.setCellValueFactory(new PropertyValueFactory<>("ID_Reclamation"));
            Colemail.setCellValueFactory(new PropertyValueFactory<>("email"));
            Coladmin.setCellValueFactory(new PropertyValueFactory<>("ID_Admin"));
            Colprojet.setCellValueFactory(new PropertyValueFactory<>("ID_Projet"));
            Colprobleme.setCellValueFactory(new PropertyValueFactory<>("ID_Type_Reclamation"));
            Colobjet.setCellValueFactory(new PropertyValueFactory<>("objet"));

            // Use a Callback to create custom cells with delete and edit buttons
            Colaction.setCellFactory(param -> new TableCell<>() {
                private final Button deleteButton = new Button("Delete");
                private final Button editButton = new Button("Edit");

                {
                    deleteButton.setStyle("-fx-cursor: hand; -fx-base: #ff1744;");
                    editButton.setStyle("-fx-cursor: hand; -fx-base: #00E676;");

                    deleteButton.setOnAction(event -> handleDeleteReclamation());
                    editButton.setOnAction(event -> handleEditReclamation());

                    HBox buttons = new HBox(deleteButton, editButton);
                    buttons.setStyle("-fx-alignment:center");
                    setGraphic(buttons);
                }

                @Override
                protected void updateItem(Void item, boolean empty) {
                    super.updateItem(item, empty);

                    if (empty) {
                        setGraphic(null); // Set the graphic to null when the cell is empty
                    } else {
                        // Your existing code for setting graphics
                    }
                }

                private void handleDeleteReclamation() {
                    Reclamation selectedReclamation = getTableView().getItems().get(getIndex());
                    if (selectedReclamation != null) {
                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                        alert.setTitle("Delete Confirmation");
                        alert.setHeaderText("Are you sure you want to delete this reclamation?");
                        alert.setContentText("This action cannot be undone.");

                        Optional<ButtonType> result = alert.showAndWait();
                        if (result.isPresent() && result.get() == ButtonType.OK) {
                            MyDataBase.getInstance().deleteReclamation(selectedReclamation);
                            ReclamList.remove(selectedReclamation);
                            reclamationTables.refresh(); // Refresh the TableView
                        }
                    }
                }

                private void handleEditReclamation() {
                    Reclamation selectedReclamation = getTableView().getItems().get(getIndex());
                    if (selectedReclamation != null) {
                        try {
                            // Load the FXML file for the edit window
                            FXMLLoader loader = new FXMLLoader(getClass().getResource("/editReclamation.fxml"));
                            Parent parent = loader.load();

                            // Get the controller from the loader
                            EditReclamationController editController = loader.getController();

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
                            Logger.getLogger(reclamationViewController.class.getName()).log(Level.SEVERE, null, e);
                        }
                    }
                }

            });

            reclamationTables.setItems(ReclamList);

        } catch (Exception e) {
            Logger.getLogger(reclamationViewController.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    @FXML
    private void close(MouseEvent event) {
        // Handle close event
    }

    @FXML
    private void getAddReclamation(MouseEvent event) {
        System.out.println("Add button clicked!");

        try {
            Parent parent = FXMLLoader.load(getClass().getResource("/addReclamation.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initStyle(StageStyle.UTILITY);
            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            currentStage.close();
            stage.show();
        } catch (IOException e) {
            Logger.getLogger(reclamationViewController.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    @FXML
    private void handleRefresh() {
        ReclamList.clear(); // Clear the current data
        loadDate(); // Reload data from the database
    }
}
