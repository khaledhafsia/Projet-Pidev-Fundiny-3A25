package org.example.Controller;

//import com.example.demo2.CRUDEvent.EventService;
//import com.example.demo2.Event;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.example.Entities.Event;
import org.example.Services.EventService;

import java.io.IOException;

public class  FormController {
    @FXML
    private TextField nomField;

    @FXML
    private TextArea descriptionField;

    @FXML
    private DatePicker dateDebutField;

    @FXML
    private DatePicker dateFinField;

    @FXML
    private Spinner objectifFSpinner;

    @FXML
    private Spinner MontantFSpinner;

    @FXML
    private TextField CategorieField;

    @FXML
    private Button submitButton;
    private Label textField;

    @FXML
    public void initialize() {
        // Vous pouvez initialiser des valeurs par défaut ou configurer des gestionnaires d'événements ici
    }

    @FXML
    void back_button(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/ListEvent.fxml"));
        Scene scene = new Scene(root);
        Stage s = (Stage)((Node)event.getSource()).getScene().getWindow();
//            s.initStyle(StageStyle.TRANSPARENT);
        s.setTitle("Afficher Produit");
        s.setScene(scene);
        s.show();
    }
    @FXML
    void submit_action(ActionEvent event) {
        // Récupérer les valeurs des champs du formulaire
        String nom = nomField.getText();
        String description = descriptionField.getText();
        String dateDebut = String.valueOf(dateDebutField.getValue());
        String dateFin = String.valueOf(dateFinField.getValue());
        Integer objectifFinancement = (Integer) objectifFSpinner.getValue();
        Integer montantCollecte = (Integer) MontantFSpinner.getValue();
        String categorie = CategorieField.getText();

        // Vérifier si tous les champs obligatoires sont remplis
        if (nom.isEmpty() || description.isEmpty() || dateDebut.isEmpty() || dateFin.isEmpty() || categorie.isEmpty()) {
            // Afficher une alerte d'erreur si des champs sont vides
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez remplir tous les champs obligatoires !");
            alert.showAndWait();
        } else {
            // Si tous les champs obligatoires sont remplis, continuer avec l'ajout à la base de données
            Event nouvelEvent = new Event(nom, description, dateDebut, dateFin, objectifFinancement, montantCollecte, categorie);
            EventService eventService = new EventService();
            eventService.AjouterEvenement(nouvelEvent);

            // Afficher un message de succès
            Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
            successAlert.setTitle("Succès");
            successAlert.setHeaderText(null);
            successAlert.setContentText("Événement ajouté avec succès !");
            successAlert.showAndWait();

            // Retourner à la page de la card view (ListEvent.fxml)
            backToCardView(event);



            // Retourner à la page de la card view (ListEvent.fxml)
            backToCardView(event);
        }
        }


        private void backToCardView(ActionEvent event) {
//            try {
//                FXMLLoader loader = new FXMLLoader(getClass().getResource("/ListEvent.fxml"));
//                Parent root = loader.load();
//                ListEventController controller = loader.getController();
//                // Mettez en place votre contrôleur si nécessaire, par exemple, pour charger des données ou effectuer d'autres actions
//                // ...
//
//                Scene scene = new Scene(root);
//                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//                stage.setScene(scene);
//                stage.show();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
        }




    @FXML
    private void handleSubmit() {
        String input = textField.getText().trim();
        if (input.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Le champ de saisie ne peut pas être vide !");
            alert.showAndWait();
        } else {
            // Faire quelque chose avec l'entrée valide
            System.out.println("Entrée valide : " + input);
        }
    }
}
