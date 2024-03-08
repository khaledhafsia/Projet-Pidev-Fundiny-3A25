package org.example.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import org.example.Entities.*;


import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;
import javafx.scene.control.Label;
public class PostCardController {
    private static final Logger logger = Logger.getLogger(PostCardController.class.getName());

    @FXML
    private Label titleLabel;

    @FXML
    private Label nameLabel;

    @FXML
    private Label emailLabel;
    @FXML
    private Label idLabel;
    @FXML
    private Label roleLabel;
    @FXML
    private Label capitalLabel;
    @FXML
    private Label montantlabel;
    private User currentUser;

    private AdminController adminController;

    public void initialize(User user, AdminController adminController) {
        this.currentUser = user;
        this.adminController = adminController;
        titleLabel.setText("User Information");
        nameLabel.setText(user.getNom() + " " + user.getPrenom());
        emailLabel.setText(user.getEmail());
        roleLabel.setText(String.valueOf(user.getRole()));

/*        switch (user.getRole()) {
            case Owner:
                List<Projet> Projet =  ((Owner) user).getProjetsList();
                StringBuilder sb = new StringBuilder();
                for (Projet projects : Projet) {
                    sb.append(projects.getId()).append(", ");
                }
                String projectsIds = sb.toString();
                if (!projectsIds.isEmpty()) {
                    projectsIds = projectsIds.substring(0, projectsIds.length() - 2);
                    capitalLabel.setText(projectsIds);
                } else {
                    capitalLabel.setText("");
                }
                break;
            case Funder:
                List<investissements> investments =  ((Funder) user).getInvestmentsList();
                StringBuilder sb2 = new StringBuilder();
                for (investissements investment : investments) {
                    sb2.append(investment.getInvID()).append(", ");
                }
                String investmentIds = sb2.toString();
                if (!investmentIds.isEmpty()) {
                    investmentIds = investmentIds.substring(0, investmentIds.length() - 2);
                    montantlabel.setText(investmentIds);
                } else {
                    montantlabel.setText("");
                }
                break;
            case ADMIN:
                montantlabel.setText("");
                capitalLabel.setText("");
                break;
            default:
                break; // Add a break statement for the default case
        }



 */


    }

    @FXML
    private void handleDeleteAction(ActionEvent event) {
        if (adminController != null && currentUser != null) {
            int id = currentUser.getId();

            if (id != 0) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation");
                alert.setHeaderText("Delete User");
                alert.setContentText("Are you sure you want to delete this user?");
                alert.showAndWait().ifPresent(response -> {
                    if (response == ButtonType.OK) {
                        adminController.deleteCurrentUser(id);
                        adminController.initialize();
                    }
                });
            } else {
                Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                errorAlert.setContentText("Invalid user ID. Unable to delete the user.");
                errorAlert.showAndWait();
            }
        } else {
            logger.warning("AdminController or currentUser is null");
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setContentText("Unable to delete the user. An error occurred.");
            errorAlert.showAndWait();
        }
        Refresh();
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
            Refresh();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    private void handleBanAction(ActionEvent event) {
        if (currentUser != null && currentUser.getRole() != User.role.ADMIN) {
            adminController.banCurrentUser(currentUser.getId());
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText(null);
            alert.setContentText("Cannot ban an admin user.");
            alert.showAndWait();
        }
    }
    @FXML
    private void handleUnBanAction(ActionEvent event) {
        if (currentUser != null && currentUser.getRole() != User.role.ADMIN) {
            adminController.banCurrentUser(currentUser.getId());
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText(null);
            alert.setContentText("Cannot ban an admin user.");
            alert.showAndWait();
        }
    }

    private void Refresh() {

            adminController.Refresh();

    }

}


