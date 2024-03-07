package org.example.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.example.Entities.article;
import org.example.Services.servicearticle;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

public class AddarticleController {

    private  servicearticle sa = new servicearticle();
    @FXML
    private TextField tfdescription2;

    @FXML
    private Label titre;
    @FXML
    private String image ;

    @FXML
    void btnCREATEClicked(ActionEvent event) throws SQLException {
        String description = tfdescription2.getText();
        if (!description.isEmpty()) {
            sa.add(new article(1, description, image));
            System.out.println("Article added successfully.");
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur de validation");
            alert.setHeaderText("Veuillez corriger les champs invalides.");
            alert.setContentText("Tous les champs sont requis");
            alert.showAndWait();
        }
    }

    @FXML
    void selectImageClicked(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Image");

        // Set initial directory (optional)
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));

        // Filter for image files
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif")
        );

        // Show open file dialog
        File selectedFile = fileChooser.showOpenDialog(tfdescription2.getScene().getWindow());

        // Load and display the selected image
        if (selectedFile != null) {
            image = selectedFile.toURI().toString();
            System.out.println("Image path: " + image);
        }
    }
    @FXML
    void ShowpostClicked(ActionEvent event) {
        try {
            // Load the showarticle.fxml file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/showarticle.fxml"));
            Parent root = loader.load();

            // Create a new scene
            Scene scene = new Scene(root);

            // Get the stage from the event source
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            // Set the new scene
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
           System.out.println("error");
            // Handle exception
        }
    }

}

