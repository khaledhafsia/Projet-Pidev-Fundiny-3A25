package tn.esprit.controllers;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import tn.esprit.models.investissements;
import tn.esprit.services.serviceInvestissements;

import java.io.IOException;
import java.sql.SQLException;

public class Updateinvestissement {

    @FXML
    private TextArea descriptiontf2;

    @FXML
    private TextField montanttf2;

    @FXML
    private TextField projettf2;

    @FXML
    private TextField usertf2;

    private investissements selectedInvestissement;

    @FXML
    void backBtn(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Showinvestissement.fxml"));
        try {
            Parent root = loader.load();

            usertf2.getScene().setRoot(root);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }


    }

    @FXML
    void miseajour(ActionEvent event) throws SQLException {

        if (isInputValid())
        {
        int userID = Integer.parseInt(usertf2.getText());
        int projetID = Integer.parseInt(projettf2.getText());
        double montant = Double.parseDouble(montanttf2.getText());
        String description = descriptiontf2.getText();

        selectedInvestissement.setUserID(userID);
        selectedInvestissement.setProjetID(projetID);
        selectedInvestissement.setMontant(montant);
        selectedInvestissement.setDescription(description);

        serviceInvestissements sp = new serviceInvestissements();
            try {
                // Set your secret key here
                Stripe.apiKey = "sk_test_51Opa2DF0Qy9fQwwPNstG4UlPkc5crZ7biWlPecNH2TWInFRlz987gMGbseHxQiRVHmk6V0b91UXQsJIONpwVPYtH00qCLoux1d";

                // Create a PaymentIntent with other payment details
                PaymentIntentCreateParams params = PaymentIntentCreateParams.builder()
                        .setAmount(Long.parseLong(montanttf2.getText())*100) // Amount in cents (e.g., $10.00)
                        .setCurrency("usd")
                        .build();

                PaymentIntent intent = PaymentIntent.create(params);

                // If the payment was successful, display a success message
                System.out.println("Payment successful. PaymentIntent ID: " + intent.getId());

                sp.update(selectedInvestissement);
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Success");
                alert.setContentText("mise à jour terminée");
                alert.showAndWait();
            } catch (StripeException e) {
                // If there was an error processing the payment, display the error message
                System.out.println("Payment failed. Error: " + e.getMessage());
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur de validation");
            alert.setHeaderText("Veuillez corriger les champs invalides.");
            alert.setContentText("Tous les champs sont requis !\n le montant doit être un chiffre ! ");
            alert.showAndWait();
        }

    }

    public void initData(investissements selectedInvestissement) {
        this.selectedInvestissement = selectedInvestissement;
        usertf2.setText(String.valueOf(selectedInvestissement.getUserID()));
        projettf2.setText(String.valueOf(selectedInvestissement.getProjetID()));
        montanttf2.setText(String.valueOf(selectedInvestissement.getMontant()));
        descriptiontf2.setText(selectedInvestissement.getDescription());
    }

    private boolean isInputValid() {
        return (!usertf2.getText().isEmpty() && !projettf2.getText().isEmpty() && !montanttf2.getText().isEmpty() &&
                !descriptiontf2.getText().isEmpty()/* && isInteger(usertf2.getText()) && isInteger(projettf2.getText()) &&
                isNumeric(montanttf2.getText()))*/);
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
