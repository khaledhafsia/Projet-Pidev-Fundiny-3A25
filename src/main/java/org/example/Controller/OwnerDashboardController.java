package org.example.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.example.Entities.User;

import java.io.IOException;

public class OwnerDashboardController {

    @FXML
    private Label titleLabel;
    @FXML
    private Label nameLabel;

    @FXML
    private Button SignOutButton;
    public void initData(User user) {
        this.currentUser = user;
    }

    private User currentUser;
    private AdminController adminController;

    public void initialize(User user,AdminController adminController) {
        this.currentUser = user;
        this.adminController = adminController;
        titleLabel.setText("Welcome to your Porject  Owner Interface");
        nameLabel.setText(user.getNom() + " " + user.getPrenom());



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




}
