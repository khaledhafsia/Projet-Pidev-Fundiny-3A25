package tn.esprit.controllers;
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
            sp.add(new investissements(1,Integer.parseInt(tfuserid.getText()), Integer.parseInt(tfprojetid.getText()), Double.parseDouble(tfmontant.getText()), tfdescription.getText(),currentTimestamp));
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Success");
            alert.setContentText("Investissement ajoutée");
            alert.showAndWait();
            tfuserid.clear();
            tfprojetid.clear();
            tfmontant.clear();
            tfdescription.clear();
        } else {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur de validation");
            alert.setHeaderText("Veuillez corriger les champs invalides.");
            alert.setContentText("Tous les champs sont requis !\n user ID, projet ID et montant doivent être des chiffres ! ");
            alert.showAndWait();
        }

    }

    @FXML
    void afficherBtn1(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/showinvestissement.fxml"));
        try {
            Parent root = loader.load();

            tfmontant.getScene().setRoot(root);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

    private boolean isInputValid() {
        return !tfuserid.getText().isEmpty() && !tfprojetid.getText().isEmpty() && !tfmontant.getText().isEmpty() &&
                !tfdescription.getText().isEmpty() && isInteger(tfuserid.getText()) && isInteger(tfprojetid.getText()) &&
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
