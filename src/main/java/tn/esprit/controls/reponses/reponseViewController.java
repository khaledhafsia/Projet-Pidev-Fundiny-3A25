package tn.esprit.controls.reponses;

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
import tn.esprit.models.Reponse;
import tn.esprit.utils.MyDataBase;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class reponseViewController implements javafx.fxml.Initializable {

    @FXML
    private ListView<Reponse> ReponseListView;

    private final ObservableList<Reponse> RepList = FXCollections.observableArrayList();

    @FXML
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadData();
    }

    @FXML
    private TextField searchField;

    @FXML
    private Button refreshBtn;

    @FXML
    private Button printBtn;

    @FXML
    private Button pdfBtn;
    private void loadData() {
        ReponseListView.setItems(RepList);
        try {
            RepList.addAll(MyDataBase.getInstance().getAllReponse());

            ReponseListView.setCellFactory(param -> new ListCell<>() {
                private final Button editButton = new Button("Edit");

                {
                    editButton.setStyle("-fx-cursor: hand; -fx-base: #00E676;");

                    editButton.setOnAction(event -> handleEditReponse());

                    HBox buttons = new HBox(editButton);
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
                protected void updateItem(Reponse item, boolean empty) {
                    super.updateItem(item, empty);

                    if (empty || item == null) {
                        setText(null);
                        setGraphic(null);
                    } else {
                        setText("ID: " + item.getID_Reponse() + ", Email: " + item.getemail() +
                                ", User ID: " + item.getID_Utilisateur() + ", Objet: " + item.getobjet());
                        setGraphic(getGraphic());
                    }
                }

                private void handleEditReponse() {
                    Reponse selectedReponse = getItem();
                    if (selectedReponse != null) {
                        // Implement the logic for editing a reponse here
                        // You can open another dialog or navigate to another view for editing
                        // Make sure to update the RepList and refresh the view if needed
                    }
                }
            });

        } catch (Exception e) {
            Logger.getLogger(reponseViewController.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    private void showCardView(Reponse selectedReponse) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/reponses/CardViewRep.fxml"));
            Parent root = loader.load();

            // Create a new scene with the content loaded from the FXML
            Scene scene = new Scene(root);

            // Create a new window (stage)
            Stage stage = new Stage();
            stage.setScene(scene);

            // Get the controller from the loader
            CardViewRep cardViewController = loader.getController();

            // Use the setData method to set the data
            cardViewController.setData(selectedReponse);

            // Show the new window (stage)
            stage.show();
        } catch (IOException e) {
            Logger.getLogger(reponseViewController.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    @FXML
    private void getAddReponse(MouseEvent event) {
        System.out.println("Add button clicked!");
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("/reponses/addReponse.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.showAndWait();

            // Refresh the data after adding a new reponse
            RepList.clear();
            RepList.addAll(MyDataBase.getInstance().getAllReponse());

        } catch (IOException e) {
            Logger.getLogger(reponseViewController.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    private void handleRefresh(MouseEvent event) {
        // Refresh the data
        refreshData();

    }

    private void refreshData() {
        RepList.clear();
        RepList.addAll(MyDataBase.getInstance().getAllReponse());
    }
}
