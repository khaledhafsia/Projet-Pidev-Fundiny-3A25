package controllers;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import tn.esprit.models.Projet;
import tn.esprit.services.ServiceProjet;
import javafx.fxml.FXMLLoader;

import java.io.IOException;
import java.lang.Float;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import javafx.stage.Stage;
import tn.esprit.test.MainFX;

public class AjouterProjetController {
    private Stage stage;
    private Scene scene;
    private Parent root;
    private final ServiceProjet sp = new ServiceProjet();

    @FXML
    private TextField nomPrT;

    @FXML
    private TextField nomPoT;

    @FXML
    private DatePicker dateDT;

    @FXML
    private TextField CAT;




    @FXML
    void Ajouter(ActionEvent event) throws SQLException {
        if (InputValidator.validateFields(nomPrT, nomPoT, dateDT, CAT)) {
            LocalDate localDate = dateDT.getValue();
            Date sqlDate = Date.valueOf(localDate);
            sp.add(new Projet(1, nomPrT.getText(), nomPoT.getText(), sqlDate, Integer.parseInt(CAT.getText())));
        } else {
            System.out.println("Veuillez remplir tous les champs correctement.");
        }
    }
    @FXML
    void Afficher(MouseEvent event)throws IOException {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/AfficherProjet.fxml"));
            Parent my_root = (Parent)fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Liste des Projets");
            stage.setScene(new Scene(my_root));
            stage.show();
        }
        catch (Exception e){
            System.out.println("FXML file path: " + getClass().getResource("/AfficherProjet.fxml"));
            e.printStackTrace();
        }


    }
    public static boolean validateFields(TextField nomPrT, TextField nomPoT, DatePicker dateDT, TextField CAT) {
        return isNotEmpty(nomPrT) &&
                isNotEmpty(nomPoT) &&
                containsOnlyLetters(nomPrT) &&
                containsOnlyLetters(nomPoT) &&
                isValidDate(dateDT) &&
                isInteger(CAT);
    }

    private static boolean isNotEmpty(TextField textField) {
        if (textField.getText().isEmpty()) {
            System.out.println("Le champ " + textField.getId() + " ne doit pas être vide.");
            return false;
        }
        return true;
    }

    private static boolean containsOnlyLetters(TextField textField) {
        String text = textField.getText();
        if (!text.matches("^[a-zA-Z]+$")) {
            System.out.println("Le champ " + textField.getId() + " ne doit contenir que des lettres.");
            return false;
        }
        return true;
    }

    private static boolean isValidDate(DatePicker datePicker) {
        LocalDate date = datePicker.getValue();
        if (date == null) {
            System.out.println("Veuillez sélectionner une date valide.");
            return false;
        }
        return true;
    }

    private static boolean isInteger(TextField textField) {
        try {
            Integer.parseInt(textField.getText());
            return true;
        } catch (NumberFormatException e) {
            System.out.println("Le champ " + textField.getId() + " doit contenir un entier valide.");
            return false;
        }
    }
}

