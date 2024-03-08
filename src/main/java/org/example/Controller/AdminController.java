package org.example.Controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.example.Entities.User;
import org.example.Services.ServiceUser;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AdminController {

    private static final Logger logger = Logger.getLogger(AdminController.class.getName());

    @FXML
    private VBox postCardContainer;

    private ServiceUser userService;


    @FXML
    private Button SignOutButton;
    @FXML
    private TextField searchField;
    @FXML
    private TextField searchTextField;
    @FXML
    private ChoiceBox<User.role> roleFilterChoiceBox;

    public AdminController() {
        userService = new ServiceUser();
    }
    @FXML
    public void initialize() {

        try {
            List<User> userList = userService.getAllUsers();

            roleFilterChoiceBox.getItems().addAll(User.role.values());
            roleFilterChoiceBox.setValue(User.role.ADMIN);


            for (User user : userList) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/PostCardTemplate.fxml"));
                VBox postCard = loader.load();
                PostCardController controller = loader.getController();

                controller.initialize(user, this);
                postCardContainer.getChildren().add(postCard);
            }

        } catch (IOException e) {
        } catch (Exception ex) {
        }
    }



    public void deleteCurrentUser(int id) {
        try {
            userService.deleteUser(id);
            initialize();
        } catch (SQLException e) {
            e.printStackTrace();
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setTitle("Error");
            errorAlert.setHeaderText(null);
            errorAlert.setContentText("An error occurred while deleting the user.");
            errorAlert.showAndWait();
        }


    }

    @FXML
    private void handleRefresh(ActionEvent event) {
        Refresh();
    }
    @FXML
    void Refresh() {
        EmptyView();
        initialize();
    }
    @FXML
    void EmptyView() {
        postCardContainer.getChildren().clear();
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
    private void DashboardAdmin() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/DashboardAdmin.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) SignOutButton.getScene().getWindow();
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void banCurrentUser(int id) {
        userService.banUser(id);
        initialize();
    }
    public void UnbanCurrentUser(int id) {
        userService.unbanUser(id);
        initialize();
    }


    @FXML
    void handleSearch(ActionEvent event) {
        String searchText = searchTextField.getText();
        if (!searchText.isEmpty()) {
            List<User> searchedUsers = userService.searchUsersByName(searchText);

            if (!searchedUsers.isEmpty()) {
                postCardContainer.getChildren().clear(); // Clear existing user cards
                for (User user : searchedUsers) {
                    try {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/PostCardTemplate.fxml"));
                        VBox postCard = loader.load();
                        PostCardController controller = loader.getController();
                        controller.initialize(user, this);
                        postCardContainer.getChildren().add(postCard);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Search Results");
                alert.setHeaderText(null);
                alert.setContentText("No users found matching the search criteria.");
                alert.showAndWait();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Empty Search Field");
            alert.setHeaderText(null);
            alert.setContentText("Please enter a name to search for.");
            alert.showAndWait();
        }
    }
    @FXML
    void handleSearch2(ActionEvent event) {
        String searchText = searchTextField.getText();
        if (!searchText.isEmpty()) {
            List<User> searchedUsers = userService.searchUsersByPrenom(searchText);
            if (!searchedUsers.isEmpty()) {
                postCardContainer.getChildren().clear(); // Clear existing user cards
                for (User user : searchedUsers) {
                    try {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/PostCardTemplate.fxml"));
                        VBox postCard = loader.load();
                        PostCardController controller = loader.getController();
                        controller.initialize(user, this);
                        postCardContainer.getChildren().add(postCard);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Search Results");
                alert.setHeaderText(null);
                alert.setContentText("No users found matching the search criteria.");
                alert.showAndWait();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Empty Search Field");
            alert.setHeaderText(null);
            alert.setContentText("Please enter a name to search for.");
            alert.showAndWait();
        }
    }
    @FXML
    void filterUsers() {
        User.role selectedRole = roleFilterChoiceBox.getValue();
        if (userService != null) {
        List<User> filteredUsers = userService.getUsersByRole(selectedRole);

        postCardContainer.getChildren().clear();

        for (User user : filteredUsers) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/PostCardTemplate.fxml"));
                VBox postCard = loader.load();
                PostCardController controller = loader.getController();
                controller.initialize(user, this);
                postCardContainer.getChildren().add(postCard);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        } else {
            System.out.println("userService is null. Cannot fetch users.");
        }}
    @FXML
    private void BlogInterface(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/combined.fxml"));
            Parent root = loader.load();

            combinedController controller = loader.getController();
         //   controller.initData(currentUser);

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void Reclamation(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/addReponse.fxml"));
            Parent root = loader.load();

            addReponseController controller = loader.getController();
            //   controller.initData(currentUser);

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
            //   controller.initData(currentUser);

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
