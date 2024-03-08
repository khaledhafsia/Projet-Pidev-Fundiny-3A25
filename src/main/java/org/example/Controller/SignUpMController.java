package org.example.Controller;

import org.example.Entities.Funder;
import org.example.Entities.Owner;
import org.example.Entities.User;
import org.example.Services.ServiceUser;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import org.mindrot.jbcrypt.BCrypt;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
public class SignUpMController implements Initializable {

    @FXML
    private TextField tfNom;
    @FXML
    private TextField tfPrenom;
    @FXML
    private TextField tfEmail;

    @FXML
    private TextField tfPassword;
    @FXML
    private RadioButton rbowner;
    @FXML
    private ToggleGroup user;
    @FXML
    private RadioButton rbfunder;
    @FXML
    private Button sincrire;
    @FXML
    private RadioButton rbadmin;
    @FXML
    private Button clickHereButton;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void SignUp(ActionEvent event) {
        String nom = tfNom.getText();
        String prenom = tfPrenom.getText();
        String email = tfEmail.getText();
        String mdp = tfPassword.getText();

        String hashpwd = BCrypt.hashpw(mdp, BCrypt.gensalt());

        // Validation
        if ((nom.isEmpty()|| prenom.isEmpty() ||email.isEmpty() ||mdp.isEmpty() )){
            showAlert("All fields are required.");
            return;
        }

        if (!email.matches("^\\S+@\\S+\\.\\S+$")){
            showAlert("Email is not in a correct format.");
            return;
        }

        if ((nom.isEmpty()) || (!Character.isUpperCase(nom.charAt(0)) && !nom.matches("^[a-zA-Z0-9]+$"))) {
            showAlert("Name must  contain only letters and digits.");
            return;
        }

        if ((prenom.isEmpty()) || (!Character.isUpperCase(prenom.charAt(0)) && !prenom.matches("^[a-zA-Z0-9]+$"))) {
            showAlert("Surname  contain only letters and digits.");
            return;
        }



        ServiceUser serviceUser = new ServiceUser();
        try {
            User existingUser = ServiceUser.verifyUser(email);
            if (existingUser != null) {
                showAlert("User already exists with this email!");
                return;
            }

            if (rbowner.isSelected()) {
                Owner owner = new Owner(nom, prenom, email, hashpwd, User.role.Owner);
                ServiceUser.insertUser(owner);
                //ServiceUser.insertUser(owner, String.valueOf(owner.getCapital()), "capital");
            } else if (rbfunder.isSelected()) {
                Funder funder = new Funder(nom, prenom, email, hashpwd, User.role.Funder);
                ServiceUser.insertUser(funder);
                // ServiceUser.insertUser(funder, String.valueOf(funder.getInvestmentsList()), "montant");
            } else if (rbadmin.isSelected()) {

                User user = new User( nom, prenom, email, hashpwd, User.role.ADMIN);



            } else {
                showAlert("Please select the type of account to create.");
                return;
            }
            EmailSending.sendVerificationEmail(email);

            showAlert("A verification email has been sent to your email address. Please verify your email before proceeding.", Alert.AlertType.INFORMATION);

            showAlert("Registration successful!", Alert.AlertType.INFORMATION);
        } catch (Exception ex) {
            showAlert(ex.getMessage());
        }


        tfNom.setText("");
        tfPrenom.setText("");
        tfEmail.setText("");
        tfPassword.setText("");;





    }

    @FXML
    private void selection(ActionEvent event) {
    }

    private void showAlert(String content) {
        showAlert(content, Alert.AlertType.ERROR);
    }

    private void showAlert(String content, Alert.AlertType type) {
        Alert alert = new Alert(type, content);
        alert.showAndWait();
    }


    @FXML
    private void AlreadySignedUp() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/SignIn.fxml"));
            Parent root = loader.load();

            Scene scene = new Scene(root);

            Stage stage = (Stage) clickHereButton.getScene().getWindow();
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}