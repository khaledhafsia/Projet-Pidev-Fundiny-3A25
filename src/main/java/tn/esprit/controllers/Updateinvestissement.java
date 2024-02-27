package tn.esprit.controllers;

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

            sp.update(selectedInvestissement);
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Success");
            alert.setContentText("mise à jour terminée");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur de validation");
            alert.setHeaderText("Veuillez corriger les champs invalides.");
            alert.setContentText("Tous les champs sont requis !\n user ID, projet ID et montant doivent être des chiffres ! ");
            alert.showAndWait();
        }

    }

    public void initData(investissements selectedInvestissement) {
        this.selectedInvestissement = selectedInvestissement;
        // Populate form fields with selected investment data
        usertf2.setText(String.valueOf(selectedInvestissement.getUserID()));
        projettf2.setText(String.valueOf(selectedInvestissement.getProjetID()));
        montanttf2.setText(String.valueOf(selectedInvestissement.getMontant()));
        descriptiontf2.setText(selectedInvestissement.getDescription());
    }

    private boolean isInputValid() {
        return (!usertf2.getText().isEmpty() && !projettf2.getText().isEmpty() && !montanttf2.getText().isEmpty() &&
                !descriptiontf2.getText().isEmpty() && isInteger(usertf2.getText()) && isInteger(projettf2.getText()) &&
                isNumeric(montanttf2.getText()));
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
