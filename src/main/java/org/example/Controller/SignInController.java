package org.example.Controller;


import org.example.Entities.User;
import org.example.Entities.Owner;
import org.example.Entities.Owner;
import org.example.Services.ServiceUser;
import org.example.Services.ServiceOwner;
import org.example.Services.ServiceFunder;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.mindrot.jbcrypt.BCrypt;
public class SignInController {
}
/*
    public class SignInController implements Initializable {

}

    @FXML
    private TextField tfEmail;
    @FXML
    private TextField tfMdp;
    @FXML
    private Button mdpoublie;

    FXMLLoader loader;

   // Initializes the controller class.

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void MdpOublie(ActionEvent event) {
    }

    @FXML
    private void SignIn(ActionEvent event) {
        String email = tfEmail.getText();
        String password = tfMdp.getText();

        if (!email.matches("^\\S+@\\S+\\.\\S+$")) {
            // afficher un message d'erreur et sortir de la méthode
            Alert alert = new Alert(Alert.AlertType.ERROR, "L'adresse email n'est pas au format correct.");
            alert.showAndWait();
        } else if (password.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Le mot de passe est obligatoire.");
            alert.showAndWait();
        } else {
            //Si ne l'est pas
            User user = new User();
            Owner patient = new Owner();
            Funder medecin = new Funder();
            ServiceOwner ps = new ServiceOwner();
            patient = ps.Verifier(email);
            Session s = new Session();
            ServiceFunder sm = new ServiceFunder();
            medecin = sm.Verifier(email);
            String role = "";
            //On verifie s'il  s'agie d'un patient ou  un medecin
            if (patient.getEmail() != null) {
                //if (BCrypt.checkpw(password, patient.getPassword())) {
                if ( patient.getPassword())) {
                    try {
                        user = (User) patient;
                        for (int i = 0; i < user.getRole().length; i++) {
                            role += user.getRole()[i];
                        }
                        s = new Session(user.getId(), user.getNom(), role);
                        loader = new FXMLLoader(getClass().getResource("/com/changemakers/atpeace/gui/MenuFrontInterface.fxml"));
                        Parent root = loader.load();
                        Scene scene = new Scene(root);
                        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        stage.setScene(scene);
                        stage.show();
                    } catch (IOException ex) {
                        Logger.getLogger(SignInController.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
            } else if (medecin.getEmail() != null) {
                if (BCrypt.checkpw(password, medecin.getPassword())) {
                    try {
                        user = (User) medecin;
                        for (int i = 0; i < user.getRole().length; i++) {
                            role += user.getRole()[i];
                        }
                        s = new Session(user.getId(), user.getNom(), role);
                        loader = new FXMLLoader(getClass().getResource("/com/changemakers/atpeace/gui/RendezVousInterfaceM.fxml"));
                        Parent root = loader.load();
                        Scene scene = new Scene(root);
                        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        stage.setScene(scene);
                        stage.show();
                    } catch (IOException ex) {
                        Logger.getLogger(SignInController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

            } else if (email == "Admin" && password == "admin") {
                try {
                    // Si l'utilisateur est l'admin
                    role = "ROLE_ADMIN";
                    s = new Session(1, "Admin", role);
                    loader = new FXMLLoader(getClass().getResource("/com/changemakers/atpeace/gui/MenuInterface.fxml"));
                    Parent root = loader.load();
                    Scene scene = new Scene(root);
                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    stage.setScene(scene);
                    stage.show();
                } catch (IOException ex) {
                    Logger.getLogger(SignInController.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Mot de passe incorrect.");
                alert.showAndWait();
            }
            SessionService ss = new SessionService();
            System.out.println("user: " + user.getId());

            System.out.println("session : " + s.getId_user());
            ss.Delete();
            ss.Insert(s);
        }

    }

}

*/