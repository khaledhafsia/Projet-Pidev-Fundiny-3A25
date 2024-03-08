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


    private SignInController signInController;

    public void setSignInController(SignInController signInController) {
        this.signInController = signInController;
    }


    public void setCurrentUser(User user) {
        this.currentUser = user;
    }

//    private User getCurrentUser() {
//        return currentUser;
//    }




    public void initialize(User user,SignInController signInController) {
        this.currentUser = user;
        this.signInController = signInController;



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
    private void ProjetInterface(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/AjouterProjet.fxml"));
            Parent root = loader.load();

//             AjouterProjetController controller = loader.getController();
//             controller.initData(currentUser);

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void ShowProjects(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/AfficherProjetUser.fxml"));
            Parent root = loader.load();

            AfficherProjetUserController controller = loader.getController();
            controller.initData(currentUser);

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void Reclamation (ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/addReclamation.fxml"));
            Parent root = loader.load();

//            AddReclamationControllers controller = loader.getController();
            //controller.initData(currentUser);

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void Blog (ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/combined.fxml"));
            Parent root = loader.load();

//            combinedController controller = loader.getController();
            //controller.initData(currentUser);

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
