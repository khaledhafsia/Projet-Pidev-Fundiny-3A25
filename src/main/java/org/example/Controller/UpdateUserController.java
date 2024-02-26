package org.example.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.example.Entities.User;
import org.example.Services.ServiceUser;
import javafx.event.ActionEvent;
import java.sql.SQLException;
import java.sql.SQLException;


public class UpdateUserController {

    @FXML
    private TextField attributeField;

    private User currentUser;

    // Method to initialize data, called from PostCardController
    public void initData(User user) {
        this.currentUser = user;
    }

    @FXML
    private void handleUpdate(ActionEvent event) {
        String attributeValue = attributeField.getText();
        if (!attributeValue.isEmpty()) {
            ServiceUser serviceUser = new ServiceUser();
            // Check user role to determine which attribute to update
            if (currentUser.getRole() == User.role.Funder) {
                serviceUser.updateUserAttribute(currentUser.getId(), "nom", attributeValue);
            } else if (currentUser.getRole() == User.role.Owner) {
                serviceUser.updateUserAttribute(currentUser.getId(), "nom", attributeValue);
            }

        }
        closeWindow();
    }
    private void showAlert(String content, Alert.AlertType type) {
        Alert alert = new Alert(type, content);
        alert.showAndWait();
    }
    /*
    @FXML
    private void handleSaveAction(ActionEvent event) {
        String newName = newNameTextField.getText();
        if (!newName.isEmpty()) {
            currentUser.setNom(newName);

            closeWindow();
        } else {
        }
    }
    private void closeWindow() {
        Stage stage = (Stage) newNameTextField.getScene().getWindow();
        stage.close();
    }

     */
    @FXML
    private void handleCancelAction(ActionEvent event) {
        closeWindow();
    }

    private void closeWindow() {
        Stage stage = (Stage) attributeField.getScene().getWindow();
        stage.close();
    }
}

