package tn.esprit.controls;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import tn.esprit.models.Reclamation;
import tn.esprit.services.serviceReclamation;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

public class CardViewRec implements javafx.fxml.Initializable {

    private Connection cnx;

    @FXML
    private Label email_card;

    @FXML
    private Button delete_button;

    @FXML
    private Button update_button;

    @FXML
    private Label user_id_card;

    @FXML
    private Label project_id_card;

    @FXML
    private Label type_id_card;

    @FXML
    private Label admin_id_card;

    @FXML
    private Label object_card;

    @FXML
    private Label text_card;

    private int ID_Reclamation;

    @FXML
    private VBox reclamation_Vbox;

    public serviceReclamation servicesReclamation = new serviceReclamation();
    private Reclamation reclamation;

    @FXML
    void delete_card(javafx.event.ActionEvent event) {
        // Ask for confirmation before deleting the card

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Delete Confirmation");
        alert.setHeaderText("Are you sure you want to delete this reclamation?");
        alert.setContentText("This action is irreversible.");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            // Remove the card from the main display (card layout)

            // Remove the reclamation from the database
            servicesReclamation.delete(reclamation);

            // Display a confirmation message
            Alert confirmationAlert = new Alert(Alert.AlertType.INFORMATION);
            confirmationAlert.setTitle("Deletion Successful");
            confirmationAlert.setHeaderText(null);
            confirmationAlert.setContentText("The reclamation has been deleted successfully.");
            confirmationAlert.showAndWait();
        }
    }
    @FXML
    void update_card(javafx.event.ActionEvent event) {

        // Retrieve the reference of the reclamation from the card
        //int ID_Reclamation = this.ID_Reclamation;

        // Create a dialog
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setTitle("Update Reclamation");
        dialog.setHeaderText("Update reclamation information");

        // Create text fields for each attribute of the reclamation
        TextField emailField = new TextField(email_card.getText());
        TextField userIDField = new TextField(user_id_card.getText());
        TextField projectIDField = new TextField(project_id_card.getText());
        TextField typeIDField = new TextField(type_id_card.getText());
        TextField adminIDField = new TextField(admin_id_card.getText());
        TextField objectField = new TextField(object_card.getText());
        TextArea textField = new TextArea(text_card.getText());

        dialog.getDialogPane().setContent(new VBox(8,
                new Label("Email:"), emailField,
                new Label("User ID:"), userIDField,
                new Label("Project ID:"), projectIDField,
                new Label("Type ID:"), typeIDField,
                new Label("Admin ID:"), adminIDField,
                new Label("Object:"), objectField,
                new Label("Text:"), textField
        ));

        // Add OK and Cancel buttons
        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

        // Wait for the user's response
        Optional<ButtonType> result = dialog.showAndWait();

        // Update the CardView information if the user clicks OK
        if (result.isPresent() && result.get() == ButtonType.OK) {
            // Update the reclamation data in the card
            email_card.setText(emailField.getText());
            user_id_card.setText(userIDField.getText());
            project_id_card.setText(projectIDField.getText());
            type_id_card.setText(typeIDField.getText());
            admin_id_card.setText(adminIDField.getText());
            object_card.setText(objectField.getText());
            text_card.setText(textField.getText());


            //
        }
    }


    public void setData(Reclamation reclamation) {
        if (reclamation == null) {
            return;
        }

        this.reclamation = reclamation;
        this.ID_Reclamation = reclamation.getID_Reclamation();

        email_card.setText(reclamation.getEmail());
        user_id_card.setText(String.valueOf(reclamation.getID_Utilisateur()));
        project_id_card.setText(String.valueOf(reclamation.getID_Projet()));
        type_id_card.setText(String.valueOf(reclamation.getID_Type_Reclamation()));
        admin_id_card.setText(String.valueOf(reclamation.getID_Admin()));
        object_card.setText(reclamation.getObjet());
        text_card.setText(reclamation.getTexte());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
    @FXML
    public void cancel(ActionEvent actionEvent) {
        // Close the dialog or the current view without making any updates
        Stage stage = (Stage) reclamation_Vbox.getScene().getWindow();
        stage.close();
    }

}
