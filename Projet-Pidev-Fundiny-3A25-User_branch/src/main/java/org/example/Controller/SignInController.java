package org.example.Controller;

import javafx.event.ActionEvent;
import javafx.scene.layout.VBox;
import org.example.Entities.User;
import org.example.Services.ServiceUser;

import org.example.Controller.CaptchaVerificationController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.example.utils.MyDataBase;


import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Objects;
import java.util.ResourceBundle;

public class SignInController{
    @FXML
    private VBox captchaVerificationForm;
    @FXML
    private TextField tfEmail;
    @FXML
    private TextField tfMdp;
    @FXML
    private Button mdpoublie;
    @FXML
    private Button clickHereButton;
    @FXML
    private Button signInButton;

    private CaptchaVerificationController captchaVerificationController;

    private FXMLLoader loader;

    private int currentUserId;
    private User currentUser;

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    private void SignIn(ActionEvent event) throws IOException, SQLException {
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

//            if (user.isBanState()) {
//                showAlert("You are banned!");
//                System.out.println("Ban state: " + user.isBanState());
//            }
            if (user != null && user.getRole() != null)  {
                currentUser = user;
                MyDataBase.getInstance().setIdenvoi(currentUser.getId());
                openInterfaceBasedOnRole(user.getRole(), event, user);
//                FunderDashboardController funderDashboardController = loader.getController();
//                funderDashboardController.setCurrentUser(currentUser);
            }

            } else {
            showAlert("Invalid email or password.");
        }
    }
    private SignInController signInController;
    public void setSignInController(SignInController signInController) {
        this.signInController = signInController;
    }

    public User getCurrentUser() {
        return currentUser;
    }
    private void openInterfaceBasedOnRole(User.role role, ActionEvent event, User user) throws IOException {
        switch (role) {
            case Owner:
                openOwnerInterface(event, user);
                break;
            case Funder:
                openFunderInterface(event, user);
                break;
            case ADMIN:
                openDefaultInterface(event);
                break;
            default:
                openDefaultInterface(event);
                break;
        }
    }
    private void openFunderInterface(ActionEvent event, User user) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/DashboardFunder.fxml"));
        Parent parent = loader.load();
        Scene scene = new Scene(parent);

        FunderDashboardController controller = loader.getController();
        controller.initData(user);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    private void openOwnerInterface(ActionEvent event, User user) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/DashboardOwner.fxml"));
        Parent parent = loader.load();
        Scene scene = new Scene(parent);

        OwnerDashboardController controller = loader.getController();
        controller.initData(user);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }


    private void openDefaultInterface(ActionEvent event) throws IOException{
        Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/DashboardAdmin.fxml")));
        Scene scene = new Scene(parent);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }



    /*

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
            loadCaptchaVerification();
            captchaVerificationForm.setVisible(true);

        } else {
            showAlert("Invalid email or password.");
        }

    }

*/
    public User getUserForCaptchaVerification() {
        return currentUser;
    }

    private void loadCaptchaVerification() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Captcha.fxml"));
        Parent root = loader.load();
        captchaVerificationController = loader.getController();
        captchaVerificationController.setSignInController(this);
        captchaVerificationForm.getChildren().add(root);
    }



    public void proceedAfterCaptchaVerification(User user) throws IOException {
        captchaVerificationForm.setVisible(false);
        openInterfaceBasedOnRole(user.getRole(), null, user);
    }







/*
   private void showAlert(String message) {
       Alert alert = new Alert(Alert.AlertType.ERROR, message);
       alert.showAndWait();
   }

*/
    @FXML
    private void AlreadySignedUp() {
        try {
            loader = new FXMLLoader(getClass().getResource("/SignUp.fxml"));
            Parent root = loader.load();

            Scene scene = new Scene(root);

            Stage stage = (Stage) clickHereButton.getScene().getWindow();
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void MdpOublie(ActionEvent event) {
        String email = tfEmail.getText();
        ServiceUser serviceUser = new ServiceUser();
        String password = serviceUser.fetchUserPasswordByMail(email);
        String text="";
        if (password != null) {
            try {
                EmailSending.sendEmail(email, password,text);
                showAlert("Password recovery email sent successfully.");
            } catch (RuntimeException e) {
                e.printStackTrace();
                showAlert("Failed to send password recovery email: " + e.getMessage());
            }
        } else {
            showAlert("No user found with the provided email.");
        }
    }


}
