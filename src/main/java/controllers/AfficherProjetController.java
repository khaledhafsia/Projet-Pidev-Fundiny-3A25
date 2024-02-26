package controllers;


import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import tn.esprit.models.Projet;
import tn.esprit.services.ServiceCollaboration;
import tn.esprit.services.ServiceProjet;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;


public class AfficherProjetController implements Initializable {
    private final ServiceProjet sp = new ServiceProjet();
    private AjouterProjetController ajouterProjetController;

    @FXML
    private ListView<Projet> lv;
    ArrayList<Projet> projets = sp.  getAll();
    private ServiceCollaboration sc = new ServiceCollaboration();
    public void setAjouterProjetController(AjouterProjetController ajouterProjetController) {
        this.ajouterProjetController = ajouterProjetController;
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        lv.setItems(FXCollections.observableArrayList(projets));
    }

    @FXML
    void actualiser(ActionEvent event) {
        lv.refresh();

    }
    @FXML
    void modifier(MouseEvent event) throws IOException {
        Projet projetAModifier = lv.getSelectionModel().getSelectedItem();

        if (projetAModifier != null) {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/ModifierProjet.fxml"));
                Parent root = (Parent) fxmlLoader.load();
                ModifierProjetController modifierProjetController = fxmlLoader.getController();
                modifierProjetController.initData(projetAModifier);// Passer le projet à modifier au contrôleur de la page de modification
                Stage stage = new Stage();
                stage.setTitle("Modifier Projet");
                stage.setScene(new Scene(root));
                stage.show();

            } catch (IOException e) {
                System.out.println("FXML file path: " + getClass().getResource("/ModifierProjet.fxml"));
                e.printStackTrace();
            }
        } else {
            System.out.println("Aucun projet sélectionné pour la modification.");
        }
    }







    @FXML
    void supprimer(MouseEvent event) {
        if (sp != null) {
            Projet projetASupprimer = lv.getSelectionModel().getSelectedItem();

            if (projetASupprimer != null) {
                boolean suppressionReussie = sp.delete(projetASupprimer);

                if (suppressionReussie) {
                    lv.getItems().remove(projetASupprimer);
                    System.out.println("Projet supprimé avec succès de la base de données et de la ListView.");
                } else {
                    System.out.println("Erreur lors de la suppression du projet de la base de données.");
                }
            } else {
                System.out.println("Aucun projet sélectionné pour la suppression.");
            }
        } else {
            System.out.println("Erreur : ServiceProjet est null.");
        }
    }
    @FXML
    void collaborer(MouseEvent event) throws IOException {
        Projet projetACollaborer = lv.getSelectionModel().getSelectedItem();

        if (projetACollaborer != null) {
            int idProjet = projetACollaborer.getId(); // Récupérer l'ID du projet sélectionné

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/CollaborerProjet.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Collaborer avec un Projet");
            stage.setScene(new Scene(fxmlLoader.load()));
            CollaborerProjetController collaborerProjetController = fxmlLoader.getController();

            // Passer l'ID du projet sélectionné et le service de collaboration au contrôleur de collaboration
            collaborerProjetController.initData(idProjet, sc);

            stage.show();
        } else {
            System.out.println("Aucun projet sélectionné pour la modification.");
        }
    }
}




