package org.example.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.example.Entities.User;
import org.example.Services.ServiceUser;
import javafx.event.ActionEvent;
import org.mindrot.jbcrypt.BCrypt;
import org.apache.commons.codec.digest.DigestUtils;
import java.sql.SQLException;
import java.sql.SQLException;


public class UpdateUserController {

    @FXML
    private TextField attributeField;
    private AdminController adminController;

    private User currentUser;

        public void initData(User user ){
        this.currentUser = user;
    }

    @FXML
    private void GeneratePassWord(ActionEvent event) {
        String timestamp = String.valueOf(System.currentTimeMillis());
        String attributeValue = DigestUtils.sha256Hex(timestamp);

        ServiceUser serviceUser = new ServiceUser();
        String hashpwd = BCrypt.hashpw(attributeValue, BCrypt.gensalt());
        serviceUser.updateUserAttribute(currentUser.getId(), "password", hashpwd);

        showAlert("Password generated: " + attributeValue, Alert.AlertType.INFORMATION);
    }

    @FXML
    private void handleUpdate(ActionEvent event) {
        String attributeValue = attributeField.getText();
        if (!attributeValue.isEmpty()) {
            ServiceUser serviceUser = new ServiceUser();
            String hashpwd = BCrypt.hashpw(attributeValue, BCrypt.gensalt());
            serviceUser.updateUserAttribute(currentUser.getId(), "password", hashpwd);
        }
        closeWindow();
        Refresh();
    }
    private void showAlert(String content, Alert.AlertType type) {
        Alert alert = new Alert(type, content);
        alert.showAndWait();
    }
    private void Refresh() {

        adminController.Refresh();

    }
    @FXML
    private void handleCancelAction(ActionEvent event) {
        closeWindow();
    }

    private void closeWindow() {
        Stage stage = (Stage) attributeField.getScene().getWindow();
        stage.close();
    }


}

