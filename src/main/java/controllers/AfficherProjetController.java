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
import tn.esprit.models.Collaboration;
import tn.esprit.models.Projet;
import tn.esprit.services.ServiceCollaboration;
import tn.esprit.services.ServiceProjet;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;


public class AfficherProjetController implements Initializable {
    private final ServiceProjet sp = new ServiceProjet();
    private final ServiceCollaboration sc = new ServiceCollaboration();
    private AjouterProjetController ajouterProjetController;

    @FXML
    private ListView<Projet> lv;
    @FXML
    private ListView<Collaboration> lvc;
    ArrayList<Projet> projets = sp.getAll();
    ArrayList<Collaboration> collaborations = sc.getAll();
    public void setAjouterProjetController(AjouterProjetController ajouterProjetController) {
        this.ajouterProjetController = ajouterProjetController;
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        lv.setItems(FXCollections.observableArrayList(projets));
        lvc.setItems(FXCollections.observableArrayList(collaborations));
    }

    @FXML
    void actualiser(ActionEvent event) {
        lv.refresh();
        lvc.refresh();

    }
    @FXML
    void modifier(MouseEvent event) throws IOException {
        Projet projetAModifier = lv.getSelectionModel().getSelectedItem();
        Collaboration collaborationAModifier = lvc.getSelectionModel().getSelectedItem();

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
        }
        if (collaborationAModifier != null) {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/ModifierCollaboration.fxml"));
                Parent root = (Parent) fxmlLoader.load();
                ModifierCollaborationController modifierCollaborationController = fxmlLoader.getController();
                modifierCollaborationController.initData(collaborationAModifier);// Passer le projet à modifier au contrôleur de la page de modification
                Stage stage = new Stage();
                stage.setTitle("Modifier Collaboration");
                stage.setScene(new Scene(root));
                stage.show();

            } catch (IOException e) {
                System.out.println("FXML file path: " + getClass().getResource("/ModifierCollaboration.fxml"));
                e.printStackTrace();
            }
        } else {
            System.out.println("Aucun projet sélectionné pour la modification.");
        }
    }







    @FXML
    void supprimer(MouseEvent event) {
        if (sp != null || sc != null) {
            Projet projetASupprimer = lv.getSelectionModel().getSelectedItem();
            Collaboration collaborationASupprimer = lvc.getSelectionModel().getSelectedItem();

            if (projetASupprimer != null) {
                boolean suppressionReussieProjet = sp.delete(projetASupprimer);
                if (suppressionReussieProjet) {
                    lv.getItems().remove(projetASupprimer);
                    System.out.println("Projet supprimé avec succès de la base de données et de la ListView.");
                } else {
                    System.out.println("Erreur lors de la suppression du projet de la base de données.");
                }
            } else if (collaborationASupprimer != null) {
                boolean suppressionReussieCollaboration = sc.delete(collaborationASupprimer);
                if (suppressionReussieCollaboration) {
                    lvc.getItems().remove(collaborationASupprimer);
                    System.out.println("Collaboration supprimée avec succès de la base de données et de la ListView.");
                } else {
                    System.out.println("Erreur lors de la suppression de la collaboration de la base de données.");
                }
            } else {
                System.out.println("Aucun projet ou collaboration sélectionné pour la suppression.");
            }
        } else {
            System.out.println("Erreur : ServiceProjet ou ServiceCollaboration est null.");
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




