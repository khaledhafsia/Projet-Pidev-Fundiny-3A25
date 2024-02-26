package org.example.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import org.example.Entities.User;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Label;
public class PostCardController {
    private static final Logger logger = Logger.getLogger(PostCardController.class.getName());

    @FXML
    private Label titleLabel;

    @FXML
    private Label nameLabel;

    @FXML
    private Label emailLabel;
    @FXML
    private Label idLabel;
    @FXML
    private Label roleLabel;

    private User currentUser;

    private AdminController adminController;

    public void initialize(User user, AdminController controller) {
        this.currentUser = user;
        this.adminController = controller;
        titleLabel.setText("User Information");
        nameLabel.setText(user.getNom() + " " + user.getPrenom());
        emailLabel.setText(user.getEmail());
        idLabel.setText(String.valueOf(user.getId()));
        roleLabel.setText(String.valueOf(user.getRole()));
        logger.info("User ID initialized in PostCardController: " + user.getId());
    }

    @FXML
    private void handleDeleteAction(ActionEvent event) {
        if (adminController != null && currentUser != null) {
            int id = currentUser.getId();
            logger.info("Deleting user with ID: " + id);

            if (id != 0) {
                // Ask for confirmation before deleting the user
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation");
                alert.setHeaderText("Delete User");
                alert.setContentText("Are you sure you want to delete this user?");
                alert.showAndWait().ifPresent(response -> {
                    if (response == ButtonType.OK) {
                        adminController.deleteCurrentUser(id);
                        adminController.Refresh(); // Refresh the UI after deletion
                    }
                });
            } else {
                logger.warning("Invalid user ID: " + id); // Log the specific user ID causing the issue
                // Optionally, display an error message to the user
                Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                errorAlert.setContentText("Invalid user ID. Unable to delete the user.");
                errorAlert.showAndWait();
            }
        } else {
            logger.warning("AdminController or currentUser is null");
            // Optionally, display an error message to the user
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setContentText("Unable to delete the user. An error occurred.");
            errorAlert.showAndWait();
        }
    }
    @FXML
    private void handleUpdateAction(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/UpdateUser.fxml"));
            Parent root = loader.load();

            UpdateUserController controller = loader.getController();
            controller.initData(currentUser);
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleBanAction(ActionEvent event) {
        // Existing handleBanAction code
    }
}