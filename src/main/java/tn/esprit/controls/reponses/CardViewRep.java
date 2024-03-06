package tn.esprit.controls.reponses;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import tn.esprit.models.Reponse;
import tn.esprit.services.serviceReponse;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class CardViewRep implements javafx.fxml.Initializable {

    @FXML
    private Label email_card;

    @FXML
    private Button delete_button;

    @FXML
    private Button update_button;

    @FXML
    private Label user_id_card;

    @FXML
    private Label object_card;

    @FXML
    private Label text_card;

    private int ID_Reponse;

    @FXML
    private VBox reponse_Vbox;

    private serviceReponse servicesReponse = new serviceReponse();
    private Reponse reponse;

    @FXML
    void delete_card(javafx.event.ActionEvent event) {
        // Ask for confirmation before deleting the card
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Delete Confirmation");
        alert.setHeaderText("Are you sure you want to delete this response?");
        alert.setContentText("This action is irreversible.");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            // Remove the card from the main display (card layout)

            // Remove the response from the database
            if (servicesReponse.delete(reponse)) {
                // Display a confirmation message
                Alert confirmationAlert = new Alert(Alert.AlertType.INFORMATION);
                confirmationAlert.setTitle("Deletion Successful");
                confirmationAlert.setHeaderText(null);
                confirmationAlert.setContentText("The response has been deleted successfully.");
                confirmationAlert.showAndWait();

                // Close the dialog or the current view after successful deletion
                Stage stage = (Stage) reponse_Vbox.getScene().getWindow();
                stage.close();
            } else {
                // Handle deletion failure
                Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                errorAlert.setTitle("Deletion Error");
                errorAlert.setHeaderText("Error deleting response");
                errorAlert.showAndWait();
            }
        }
    }

    @FXML
    void update_card(javafx.event.ActionEvent event) {
        // Retrieve the reference of the response from the card
        int ID_Reponse = this.ID_Reponse;

        // Create a dialog
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setTitle("Update Response");
        dialog.setHeaderText("Update response information");

        // Create text fields for each attribute of the response
        TextField emailField = new TextField(email_card.getText());
        TextField userIDField = new TextField(user_id_card.getText());
        TextField objectField = new TextField(object_card.getText());
        TextArea textField = new TextArea(text_card.getText());

        dialog.getDialogPane().setContent(new VBox(8,
                new Label("Email:"), emailField,
                new Label("User ID:"), userIDField,
                new Label("Object:"), objectField,
                new Label("Text:"), textField
        ));

        // Add OK and Cancel buttons
        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

        // Wait for the user's response
        Optional<ButtonType> result = dialog.showAndWait();

        // Update the CardView information if the user clicks OK
        if (result.isPresent() && result.get() == ButtonType.OK) {
            // Update the response data in the card
            email_card.setText(emailField.getText());
            user_id_card.setText(userIDField.getText());
            object_card.setText(objectField.getText());
            text_card.setText(textField.getText());

            // Update the response data in the database
            Reponse updatedResponse = new Reponse(
                    ID_Reponse,
                    emailField.getText(),
                    Integer.parseInt(userIDField.getText()),
                    objectField.getText(),
                    textField.getText()
            );

            // Call the service method to update the response in the database
            servicesReponse.update(updatedResponse);
        }
    }

    public void setData(Reponse reponse) {
        if (reponse == null) {
            return;
        }

        this.reponse = reponse;
        this.ID_Reponse = reponse.getID_Reponse();

        email_card.setText(reponse.getemail());
        user_id_card.setText(String.valueOf(reponse.getID_Utilisateur()));
        object_card.setText(reponse.getobjet());
        text_card.setText(reponse.gettexte());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Initialization logic if needed
    }
}
