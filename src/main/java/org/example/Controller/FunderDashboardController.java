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

public class FunderDashboardController {

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

    private User getCurrentUser() {
        return currentUser;
    }

    public void initialize(User user,SignInController signInController) {
        this.currentUser = user;
        this.signInController = signInController;
        titleLabel.setText("Welcome to your Porject  Funder Interface");
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

    @FXML
    private void AllProjects(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/AllProjects.fxml"));
            Parent root = loader.load();

            AllProjectsForFunder controller = loader.getController();
            controller.initData(currentUser);

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void ShowInvestments(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/InvesmentsOfFunder.fxml"));
            Parent root = loader.load();

            InvesmentsOfFunderController controller = loader.getController();
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

            AddReclamationControllers controller = loader.getController();
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

            combinedController controller = loader.getController();
            //controller.initData(currentUser);

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void Event (ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ListEvent.fxml"));
            Parent root = loader.load();

            ListEventController controller = loader.getController();
            //controller.initData(currentUser);

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
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
        /*
    @FXML
    private void InvestmentInterface() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/addinvestissement.fxml"));
            Parent root = loader.load();


            Addinvestissement controller = loader.getController();
            controller.initData(currentUser);

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


     */

}

