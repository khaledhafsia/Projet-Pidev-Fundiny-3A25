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
    private TextField tfTelephone;
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
            showAlert("Name must start with an uppercase and contain only letters and digits.");
            return;
        }

        if ((nom.isEmpty()) || (!Character.isUpperCase(prenom.charAt(0)) && !nom.matches("^[a-zA-Z0-9]+$"))) {
            showAlert("Surname must start with an uppercase and contain only letters and digits.");
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
                Owner owner = new Owner(nom, prenom, email, hashpwd, User.role.Owner, "1000.0");
                ServiceUser.insertUser(owner, String.valueOf(owner.getCapital()), "capital");
            } else if (rbfunder.isSelected()) {
                Funder funder = new Funder(nom, prenom, email, hashpwd, User.role.Funder, "500.0");
                ServiceUser.insertUser(funder, String.valueOf(funder.getParticipation()), "participation");
            } else if (rbadmin.isSelected()) {
                User user = new User( nom, prenom, email, hashpwd, User.role.ADMIN);



            } else {
                showAlert("Please select the type of account to create.");
                return;
            }

            showAlert("Registration successful!", Alert.AlertType.INFORMATION);
        } catch (Exception ex) {
            showAlert(ex.getMessage());
        }
    }

    @FXML
    private void selection(ActionEvent event) {
        // Method stub for RadioButton selection, leaving this blank as it may not be needed.
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
            // Load the new FXML file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/SignIn.fxml"));
            Parent root = loader.load();

            // Create a new scene with the loaded FXML content
            Scene scene = new Scene(root);

            // Get the stage from the button and set the new scene
            Stage stage = (Stage) clickHereButton.getScene().getWindow();
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}