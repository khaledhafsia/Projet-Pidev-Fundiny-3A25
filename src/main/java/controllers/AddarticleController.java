package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import tn.esprit.models.article;
import tn.esprit.services.servicearticle;

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
        if (tfdescription2!=null) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/showarticle.fxml"));
            try {
                Parent root = loader.load();
                sa.add(new article(1,tfdescription2.getText(), image));
                System.out.println("Article added successfully.");


                titre.getScene().setRoot(root);


            } catch (IOException e) {

                System.out.println("Error loading showarticle.fxml: " + e.getMessage());
            }
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
}
