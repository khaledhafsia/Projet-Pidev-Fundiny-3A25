package org.example.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.example.Entities.Funder;
import org.example.Entities.Owner;
import org.example.Entities.User;
import javafx.scene.control.Alert;
import org.example.Services.ServiceUser;
import javafx.scene.control.ButtonType;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PostCardController {
    @FXML
     private Label titleLabel;

    @FXML
    private Label nameLabel;

    @FXML
    private Label emailLabel;

    @FXML
    private Button UpdateButton;
    @FXML
    private Button BanButton;
    @FXML
    private Button DeleteButton;
    private User currentUser;


    public void initialize(User user) {
        this.currentUser = user;
         titleLabel.setText("User Information");
        nameLabel.setText(user.getNom() + " " + user.getPrenom());
        emailLabel.setText(user.getEmail());


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
    private void handleDeleteAction(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirm Deletion");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure you want to delete this user?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            try {
                ServiceUser serviceUser = new ServiceUser();
                serviceUser.deleteUser(currentUser.getId());
                // Optionally, you can update the UI or remove the user from the list
            } catch (SQLException e) {
                e.printStackTrace(); // Handle the exception appropriately

                // Optionally, show an error message to the user
                Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                errorAlert.setTitle("Error");
                errorAlert.setHeaderText(null);
                errorAlert.setContentText("An error occurred while deleting the user.");
                errorAlert.showAndWait();
            }
        }
    }

    @FXML
    private void handleBanAction(ActionEvent event) {

    }

}
    /*


    @FXML
    private void handleDeleteAction(ActionEvent event) {
        try {
            ServiceUser serviceUser = new ServiceUser();
            serviceUser.deleteUser(currentUser.getId());

        } catch (SQLException e) {
            e.printStackTrace();

        }
    }



    /*
    @FXML
    private void handleUpdateAction(ActionEvent event) {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/UpdateUser.fxml"));
            Parent root = loader.load();

            UpdateUserController updateUserController = loader.getController();
            updateUserController.initData(currentUser);

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Update User");
            stage.show();

        } catch (IOException e) {
            e.printStackTrace(); // Handle the exception appropriately (e.g., show an error message to the user)
        }
    }


 */







