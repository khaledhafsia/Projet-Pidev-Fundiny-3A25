package org.example.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import org.example.Entities.User;
import org.example.Services.ServiceUser;

import java.sql.SQLException;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import org.example.Entities.User;

public class UpdateUserController {
    @FXML
    private TextField modifyTextField;

    private User currentUser;

    public void initData(User user) {
        this.currentUser = user;
        if (currentUser.getRole() == User.role.Funder) {
            modifyTextField.setPromptText("Modify Participation");
        } else  if (currentUser.getRole() == User.role.Owner) {
            modifyTextField.setPromptText("Modify capitale");
        }else
            return;
    }

    @FXML
    private void handleConfirmAction() {
        String value = modifyTextField.getText();
        try {
            ServiceUser serviceUser = new ServiceUser();
            if (currentUser.getRole() == User.role.Funder) {
                serviceUser.updateUserAttribute(currentUser.getId(), "participation", value);
            } else if (currentUser.getRole() == User.role.Owner) {
                serviceUser.updateUserAttribute(currentUser.getId(), "Capitale", value);
            } else
                return;
            // Optionally, show an update success message
        } catch (SQLException e) {
            // Handle the exception appropriately, e.g., show an error message to the user
            e.printStackTrace();
        }
    }
}