package tn.esprit.controls.reclamations;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import tn.esprit.models.Reclamation;
import tn.esprit.utils.MyDataBase;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class reclamationViewController implements javafx.fxml.Initializable {

    @FXML
    private ListView<Reclamation> ReclamationListView;

    private final ObservableList<Reclamation> RecList = FXCollections.observableArrayList();

    @FXML
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadData();
    }

    private void loadData() {
        ReclamationListView.setItems(RecList);
        try {
            RecList.addAll(MyDataBase.getInstance().getAllReclamations());

            ReclamationListView.setCellFactory(param -> new ListCell<>() {
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

                    // Handle click on list item
                    setOnMouseClicked(event -> {
                        if (event.getClickCount() == 1) {
                            showCardView(getItem());
                        }
                    });
                }

                @Override
                protected void updateItem(Reclamation item, boolean empty) {
                    super.updateItem(item, empty);

                    if (empty || item == null) {
                        setText(null);
                        setGraphic(null);
                    } else {
                        setText("ID: " + item.getID_Reclamation() + ", Email: " + item.getEmail() +
                                ", Admin: " + item.getID_Admin() + ", Projet: " + item.getID_Projet() +
                                ", Probleme: " + item.getID_Type_Reclamation() + ", Objet: " + item.getObjet());
                        setGraphic(getGraphic());
                    }
                }

                private void handleDeleteReclamation() {
                    Reclamation selectedReclamation = getItem();
                    if (selectedReclamation != null) {
                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                        alert.setTitle("Delete Confirmation");
                        alert.setHeaderText("Are you sure you want to delete this reclamation?");
                        alert.setContentText("This action cannot be undone.");

                        Optional<ButtonType> result = alert.showAndWait();
                        if (result.isPresent() && result.get() == ButtonType.OK) {
                            MyDataBase.getInstance().deleteReclamation(selectedReclamation);
                            RecList.remove(selectedReclamation);
                        }
                    }
                }

                private void handleEditReclamation() {
                    // Implement the logic for editing a reclamation here
                    // You can open another dialog or navigate to another view for editing
                    // Make sure to update the RecList and refresh the view if needed
                }
            });

        } catch (Exception e) {
            Logger.getLogger(reclamationViewController.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    // reclamationViewController.java
    private void showCardView(Reclamation selectedReclamation) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/reclamations/CardViewRec.fxml"));
            Parent root = loader.load();

            // Créez une nouvelle scène avec le contenu chargé depuis le FXML
            Scene scene = new Scene(root);

            // Créez une nouvelle fenêtre (stage)
            Stage stage = new Stage();
            stage.setScene(scene);

            // Obtenez le contrôleur du chargeur
            CardViewRec cardViewController = loader.getController();

            // Utilisez la méthode setData pour définir les données
            cardViewController.setData(selectedReclamation);

            // Affichez la nouvelle fenêtre (stage)
            stage.show();
        } catch (IOException e) {
            Logger.getLogger(reclamationViewController.class.getName()).log(Level.SEVERE, null, e);
        }
    }



    @FXML
    private void getAddReclamation(MouseEvent event) {
        System.out.println("Add button clicked!");
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("/reclamations/addReclamation.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.showAndWait();

            // Refresh the data after adding a new reclamation
            RecList.clear();
            RecList.addAll(MyDataBase.getInstance().getAllReclamations());

        } catch (IOException e) {
            Logger.getLogger(reclamationViewController.class.getName()).log(Level.SEVERE, null, e);
        }
    }
}
