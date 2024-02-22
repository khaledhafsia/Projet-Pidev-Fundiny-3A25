package org.example.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import org.example.Entities.User;

public class PostCardController {
   /* @FXML
    private Label titleLabel;


    */
    @FXML
    private Label nameLabel;

    @FXML
    private Label emailLabel;

    @FXML
    private Label roleLabel;

    // You can add more FXML components and corresponding fields here

    public void initialize(User user) {
        //titleLabel.setText("User Information");
        nameLabel.setText(user.getNom() + " " + user.getPrenom());
        emailLabel.setText(user.getEmail());
        //  roleLabel.setText(user.getRole());
    }
}
