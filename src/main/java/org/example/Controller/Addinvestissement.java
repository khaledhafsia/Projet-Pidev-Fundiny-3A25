package org.example.Controller;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.example.Entities.Owner;
import org.example.Entities.User;
import org.example.Entities.investissements;
import org.example.Services.serviceInvestissements;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
    public class Addinvestissement {

    serviceInvestissements sp = new serviceInvestissements();
    investissements invs = new investissements();

    @FXML
    private TextArea tfdescription;

    @FXML
    private TextField tfmontant;

    @FXML
    private TextField tfprojetid;

    @FXML
    private TextField tfuserid;

        private User currentUser;

        public void initData(User user) {this.currentUser = user;}
        private SignInController signInController;

        public void setSignInController(SignInController signInController) {this.signInController = signInController;}

        // private User getCurrentUser() { return signInController.getCurrentUser();}

        public void initialize(User user,SignInController signInController) {
            this.currentUser = user;
            this.signInController = signInController;
            this.currentUser = signInController.getCurrentUser();

        }    @FXML
        void ajouterBtn(ActionEvent event) throws SQLException {
            Timestamp currentTimestamp = Timestamp.valueOf(LocalDateTime.now());
            if (isInputValid() && currentUser != null) {
                try {


/*
                    sp.add(new investissements(currentUser.getId(),
                            Integer.parseInt(tfprojetid.getText()),
                            Double.parseDouble(tfmontant.getText()),
                            tfdescription.getText(),
                            currentTimestamp));

                    int

 */
                    Double montant = Double.parseDouble(tfmontant.getText());
                    String description = tfdescription.getText();
                    Timestamp date= currentTimestamp;

                    investissements invesment = new investissements( 1, montant, description, date);
                    //investissements invesment = new investissements( montant, description, date);
                    serviceInvestissements.add(invesment,currentUser.getId());

                    //investissements invesment = new investissements(user_id, project_id, montant, description, date);
                    //serviceInvestissements.add(invesment,currrentproejct.getId(),currentUser.getId());






                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Success");
                    alert.setContentText("Investissement ajoutée");
                    alert.showAndWait();
                } catch (NumberFormatException e) {
                    e.printStackTrace(); // Handle NumberFormatException appropriately
                }
                clearFields();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur de validation");
                alert.setHeaderText("Veuillez corriger les champs invalides.");
                alert.setContentText("Tous les champs sont requis !\n le montant doit être un chiffre ! ");
                alert.showAndWait();
            }
        }

        // Add this method to clear input fields
        private void clearFields() {
            tfuserid.clear();
            tfprojetid.clear();
            tfmontant.clear();
            tfdescription.clear();
        }


    @FXML
    void afficherBtn1(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/testC.fxml"));
        try {
            Parent root = loader.load();

            tfmontant.getScene().setRoot(root);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

    private boolean isInputValid() {
        return /*!tfuserid.getText().isEmpty() && !tfprojetid.getText().isEmpty() &&*/ !tfmontant.getText().isEmpty() &&
                !tfdescription.getText().isEmpty() &&/* isInteger(tfuserid.getText()) && isInteger(tfprojetid.getText()) &&*/
                isNumeric(tfmontant.getText());
    }

    private boolean isInteger(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }



}
