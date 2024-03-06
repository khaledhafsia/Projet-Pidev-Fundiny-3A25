package tn.esprit.controls;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import tn.esprit.models.Reponse;
import tn.esprit.utils.MyDataBase;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class reponseViewController implements javafx.fxml.Initializable {

    @FXML
    private ListView<Reponse> ReponseListView;

    private final ObservableList<Reponse> RepList = FXCollections.observableArrayList();

    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadData();
    }

    private void loadData() {
        ReponseListView.setItems(RepList);
        try {
            RepList.addAll(MyDataBase.getInstance().getAllReponse());

            ReponseListView.setCellFactory(param -> new ListCell<>() {
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

                @Override
                protected void updateItem(Reponse item, boolean empty) {
                    super.updateItem(item, empty);

                    if (empty || item == null) {
                        setText(null);
                        setGraphic(null);
                    } else {
                        setText("ID: " + item.getID_Reponse() + ", Email: " + item.getemail() + ", User ID: " + item.getID_Utilisateur() + ", Objet: " + item.getobjet());
                        setGraphic(getGraphic());
                    }
                }

                private void handleDeleteReponse() {
                    Reponse selectedReponse = getItem();
                    if (selectedReponse != null) {
                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                        alert.setTitle("Delete Confirmation");
                        alert.setHeaderText("Are you sure you want to delete this reclamation?");
                        alert.setContentText("This action cannot be undone.");

                        Optional<ButtonType> result = alert.showAndWait();
                        if (result.isPresent() && result.get() == ButtonType.OK) {
                            MyDataBase.getInstance().deleteReponse(selectedReponse);
                            RepList.remove(selectedReponse);
                        }
                    }
                }

                private void handleEditReponse() {
                    Reponse selectedReponse = getItem();
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
