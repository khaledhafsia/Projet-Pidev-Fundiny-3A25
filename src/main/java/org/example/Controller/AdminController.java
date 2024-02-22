package org.example.Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.VBox;
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

            logger.log(Level.INFO, "AdminController initialized successfully."); // Add this line
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error loading PostCardTemplate.fxml", e);
        } catch (Exception ex) {
            logger.log(Level.SEVERE, "An unexpected error occurred", ex);
        }
    }
}
