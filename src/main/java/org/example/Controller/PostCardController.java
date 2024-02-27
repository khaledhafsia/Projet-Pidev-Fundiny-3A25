package org.example.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import org.example.Entities.Funder;
import org.example.Entities.Owner;
import org.example.Entities.User;

import java.io.IOException;
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
    @FXML
    private Label capitalLabel;
    @FXML
    private Label participationlabel;
    private User currentUser;

    private AdminController adminController;

    public void initialize(User user, AdminController adminController) {
        this.currentUser = user;
        this.adminController = adminController;
        titleLabel.setText("User Information");
        nameLabel.setText(user.getNom() + " " + user.getPrenom());
        emailLabel.setText(user.getEmail());
        roleLabel.setText(String.valueOf(user.getRole()));

        switch (user.getRole()) {
        case Owner:
            capitalLabel.setText(String.valueOf(((Owner) user).getCapital()));
            participationlabel.setText("");
            break;
        case Funder:
            participationlabel.setText(String.valueOf(((Funder) user).getParticipation()));
            capitalLabel.setText("");
            break;
            case ADMIN:
            participationlabel.setText("");
            capitalLabel.setText("");
                break;
            default:
                break;
        }






    }

    @FXML
    private void handleDeleteAction(ActionEvent event) {
        if (adminController != null && currentUser != null) {
            int id = currentUser.getId();
            logger.info("Deleting user with ID: " + id);

            if (id != 0) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation");
                alert.setHeaderText("Delete User");
                alert.setContentText("Are you sure you want to delete this user?");
                alert.showAndWait().ifPresent(response -> {
                    if (response == ButtonType.OK) {
                        adminController.deleteCurrentUser(id);
                        adminController.initialize();
                    }
                });
            } else {
                logger.warning("Invalid user ID: " + id);
                Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                errorAlert.setContentText("Invalid user ID. Unable to delete the user.");
                errorAlert.showAndWait();
            }
        } else {
            logger.warning("AdminController or currentUser is null");
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setContentText("Unable to delete the user. An error occurred.");
            errorAlert.showAndWait();
        }
        Refresh();
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
            Refresh();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    private void handleBanAction(ActionEvent event) {
    }
    private void Refresh() {

            adminController.Refresh();

    }

}


