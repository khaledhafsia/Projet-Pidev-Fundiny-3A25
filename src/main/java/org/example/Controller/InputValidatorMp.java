package org.example.Controller;

import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

public class InputValidatorMp {

    public static boolean validateFields(TextField nomPrT, TextField nomPoT, TextField CAT) {
        return isNotEmpty(nomPrT) &&
                isNotEmpty(nomPoT) &&
                containsOnlyLetters(nomPrT) &&
                containsOnlyLetters(nomPoT) &&
                isInteger(CAT);
    }
    public static void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private static boolean isNotEmpty(TextField textField) {
        if (textField.getText().isEmpty()) {
            showAlert("Erreur de saisie", "Le champ " + textField.getId() + " ne doit pas etre vide.");
            return false;
        }
        return true;
    }

    private static boolean containsOnlyLetters(TextField textField) {
        String text = textField.getText();
        if (!text.matches("^[a-zA-Z ]+$")) {
            showAlert("Erreur de saisie", "Le champ " + textField.getId() + " ne doit contenir que des lettres et des espaces.");
            return false;
        }
        return true;
    }

    private static boolean isInteger(TextField textField) {
        try {
            Integer.parseInt(textField.getText());
            return true;
        } catch (NumberFormatException e) {
            showAlert("Erreur de saisie", "Le champ " + textField.getId() + " ne doit contenir entier valide.");
            return false;
        }
    }
}
