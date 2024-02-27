package controllers;

import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import java.time.LocalDate;

public class InputValidator {

    public static boolean validateFields(TextField nomPrT, TextField nomPoT, DatePicker dateDT, TextField CAT) {
        return isNotEmpty(nomPrT) &&
                isNotEmpty(nomPoT) &&
                containsOnlyLetters(nomPrT) &&
                containsOnlyLetters(nomPoT) &&
                isValidDate(dateDT) &&
                isInteger(CAT);
    }

    public static boolean isNotEmpty(TextField textField) {
        if (textField.getText().isEmpty()) {
            showAlert("Erreur de saisie", "Le champ " + textField.getId() + " ne doit pas être vide.");
            return false;
        }
        return true;
    }

    public static boolean containsOnlyLetters(TextField textField) {
        String text = textField.getText();
        if (!text.matches("^[a-zA-Z ]+$")) { // Ajout de l'espace dans l'expression régulière
            showAlert("Erreur de saisie", "Le champ " + textField.getId() + " ne doit contenir que des lettres et des espaces.");            return false;
        }
        return true;
    }
    public static void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static boolean isValidDate(DatePicker datePicker) {
        LocalDate date = datePicker.getValue();
        if (date == null) {
            showAlert("Erreur de saisie", "Le champ " + " ne doit pas être vide ou incorrecte.");
            return false;
        }
        return true;
    }

    public static boolean isInteger(TextField textField) {
        try {
            Integer.parseInt(textField.getText());
            return true;
        } catch (NumberFormatException e) {
            showAlert("Erreur de saisie", "Le champ " + textField.getId() + " doit contenir un entier valide.");            return false;
        }
    }
}
