package org.example.Controller;


import org.example.Entities.User;
import org.example.Services.ServiceUser;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;


//public class SignInController implements Initializable {


public class SignInController{


    @FXML
    private TextField tfEmail;
    @FXML
    private TextField tfMdp;
    @FXML
    private Button mdpoublie;

    FXMLLoader loader;

   // Initializes the controller class.
/*
   @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }



 */

    @FXML
    private void MdpOublie(ActionEvent event) {
    }


    @FXML
    private void SignIn(ActionEvent event) throws IOException {
        String email = tfEmail.getText();
        String password = tfMdp.getText();

        if (!email.matches("^\\S+@\\S+\\.\\S+$")){
            showAlert("Email is not in a correct format.");
            return;
        }

        if (password.isEmpty()) {
            showAlert("Password is required.");
            return;
        }

        ServiceUser serviceUser = new ServiceUser();
        User user = serviceUser.validateUser(email, password);

        if (user != null) {
            openInterfaceBasedOnRole(user.getRole(), event);
        } else {
            showAlert("Invalid email or password.");
        }
    }

    private void openInterfaceBasedOnRole(User.role role, ActionEvent event) throws IOException {

        switch (role) {
            case Owner:
                openOwnerInterface(event);
                break;
            case Funder:
                openFunderInterface(event);
                break;
            case ADMIN:
                openDefaultInterface(event);
                break;
            default:
                openDefaultInterface(event);
                break;
        }
    }

    private void openOwnerInterface(ActionEvent event) throws IOException {
        // Load the FXML file from the correct location
        Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/DashboardOwner.fxml")));
        Scene scene = new Scene(parent);

        // Fetch the stage and set the scene
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    private void openFunderInterface(ActionEvent event) throws IOException {
        // Load the FXML file from the correct location
        Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/DashboardFunder.fxml")));
        Scene scene = new Scene(parent);

        // Fetch the stage and set the scene
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }


    private void openDefaultInterface(ActionEvent event) throws IOException{
        Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/DashboardAdmin.fxml")));
        Scene scene = new Scene(parent);

        // Fetch the stage and set the scene
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR, message);
        alert.showAndWait();
    }





}

