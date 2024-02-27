package org.example.Controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Alert;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.example.Entities.User;
import org.example.Services.ServiceUser;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

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
            List<User> userList = userService.getAllUsers();

            logger.log(Level.INFO, "User list size: {0}", userList.size());

            for (User user : userList) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/PostCardTemplate.fxml"));
                VBox postCard = loader.load();
                PostCardController controller = loader.getController();

                controller.initialize(user, this);
                postCardContainer.getChildren().add(postCard);
                logger.info("User ID being initialized: " + user.getId());
            }

            logger.log(Level.INFO, "AdminController initialized successfully.");
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error loading PostCardTemplate.fxml", e);
        } catch (Exception ex) {
            logger.log(Level.SEVERE, "An unexpected error occurred", ex);
        }
    }



    public void deleteCurrentUser(int id) {
        try {
            userService.deleteUser(id);
            initialize();
        } catch (SQLException e) {
            e.printStackTrace();
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setTitle("Error");
            errorAlert.setHeaderText(null);
            errorAlert.setContentText("An error occurred while deleting the user.");
            errorAlert.showAndWait();
        }


    }

    @FXML
    private void handleRefresh(ActionEvent event) {
        Refresh();
    }
    @FXML
    void Refresh() {
        EmptyView();
        initialize();
    }
    @FXML
    void EmptyView() {
        postCardContainer.getChildren().clear();
    }


    @FXML
    private void SignOut() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/SignIn.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) SignOutButton.getScene().getWindow();
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void DashboardAdmin() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/DashboardAdmin.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) SignOutButton.getScene().getWindow();
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
