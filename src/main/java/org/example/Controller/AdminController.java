package org.example.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.example.Entities.User;
import org.example.Services.ServiceUser;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import java.util.logging.Logger;
import java.util.logging.Level;

public class AdminController {

    private static final Logger logger = Logger.getLogger(AdminController.class.getName());

    @FXML
    private VBox postCardContainer;

    private ServiceUser userService;
    @FXML
    private Button RefreshButton;
    @FXML
    private Button SignOutButton;

        public AdminController() {
        userService = new ServiceUser();
    }

    @FXML
    public void initialize() {
        try {
            // Fetch users from database
            List<User> userList = userService.getAllUsers();

            logger.log(Level.INFO, "User list size: {0}", userList.size());

            for (User user : userList) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/PostCardTemplate.fxml"));
                VBox postCard = loader.load();
                PostCardController controller = loader.getController();
                controller.initialize(user);
                postCardContainer.getChildren().add(postCard);
            }

            logger.log(Level.INFO, "AdminController initialized successfully.");
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error loading PostCardTemplate.fxml", e);
        } catch (Exception ex) {
            logger.log(Level.SEVERE, "An unexpected error occurred", ex);
        }
    }
    @FXML
    private void handleRefresh(ActionEvent event) {
        Refresh();
    }
    @FXML
    private void Refresh() {
        postCardContainer.getChildren().clear(); // Clear the current UI elements

        try {
            // Fetch users from database
            List<User> userList = userService.getAllUsers();

            for (User user : userList) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/PostCardTemplate.fxml"));
                VBox postCard = loader.load();
                PostCardController controller = loader.getController();
                controller.initialize(user);
                postCardContainer.getChildren().add(postCard);
            }

            logger.log(Level.INFO, "UI refreshed successfully.");
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error loading PostCardTemplate.fxml", e);
        } catch (Exception ex) {
            logger.log(Level.SEVERE, "An unexpected error occurred", ex);
        }
    }

    @FXML
    private void SignOut() {
        try {
            // Load the new FXML file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/SignIn.fxml"));
            Parent root = loader.load();

            // Create a new scene with the loaded FXML content
            Scene scene = new Scene(root);

            // Get the stage from the button and set the new scene
            Stage stage = (Stage) SignOutButton.getScene().getWindow();
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
