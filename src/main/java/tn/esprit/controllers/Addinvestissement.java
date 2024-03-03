package tn.esprit.controllers;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import tn.esprit.models.investissements;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import tn.esprit.services.serviceInvestissements;
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

    @FXML
    void ajouterBtn(ActionEvent event) throws SQLException {

        Timestamp currentTimestamp = Timestamp.valueOf(LocalDateTime.now());
        if (isInputValid())
        {


            try {
                // Set your secret key here
                Stripe.apiKey = "sk_test_51Opa2DF0Qy9fQwwPNstG4UlPkc5crZ7biWlPecNH2TWInFRlz987gMGbseHxQiRVHmk6V0b91UXQsJIONpwVPYtH00qCLoux1d";

                // Create a PaymentIntent with other payment details
                PaymentIntentCreateParams params = PaymentIntentCreateParams.builder()
                        .setAmount(Long.parseLong(tfmontant.getText())*100) // Amount in cents (e.g., $10.00)
                        .setCurrency("usd")
                        .build();

                PaymentIntent intent = PaymentIntent.create(params);

                // If the payment was successful, display a success message
                System.out.println("Payment successful. PaymentIntent ID: " + intent.getId());
                sp.add(new investissements(1,22, 23, Double.parseDouble(tfmontant.getText()), tfdescription.getText(),currentTimestamp));
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Success");
                alert.setContentText("Investissement ajoutée");
                alert.showAndWait();
            } catch (StripeException e) {
                // If there was an error processing the payment, display the error message
                System.out.println("Payment failed. Error: " + e.getMessage());
            }

            tfuserid.clear();
            tfprojetid.clear();
            tfmontant.clear();
            tfdescription.clear();
        } else {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur de validation");
            alert.setHeaderText("Veuillez corriger les champs invalides.");
            alert.setContentText("Tous les champs sont requis !\n le montant doit être un chiffre ! ");
            alert.showAndWait();
        }

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
                !tfdescription.getText().isEmpty()/* && isInteger(tfuserid.getText()) && isInteger(tfprojetid.getText()) &&
                isNumeric(tfmontant.getText())*/;
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
