package org.example.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import org.example.Entities.User;
import org.example.Services.ServiceUser;
import javafx.event.ActionEvent;
import java.sql.SQLException;
import java.sql.SQLException;


public class UpdateUserController {

    @FXML
    private TextField attributeField;

    private User currentUser;

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
    }
}
