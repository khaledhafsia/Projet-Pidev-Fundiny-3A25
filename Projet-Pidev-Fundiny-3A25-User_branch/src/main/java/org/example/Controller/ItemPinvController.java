package org.example.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.example.Entities.Projet;
import org.example.Services.ServiceCollaboration;

import java.io.IOException;

public class ItemPinvController {

    @FXML
    private HBox itemC;

    @FXML
    private Label nomPr;

    @FXML
    private Label nomPo;

    @FXML
    private Label dateD;

    @FXML
    private Label CA;

    @FXML
    private Button modifier;

    @FXML
    private Button supprimer;
    @FXML
    private Button collaborer;
    @FXML
    void investir(ActionEvent event) {

    }
    ServiceCollaboration sc;
    private AfficherProjetController afficherProjetController;

    public void setAfficherProjetController(AfficherProjetController afficherProjetController) {
        this.afficherProjetController = afficherProjetController;
    }
    public void initializeProjet(Projet projet) {
        nomPr.setText(projet.getNomPr());
        nomPo.setText(projet.getNomPo());
        dateD.setText(String.valueOf(projet.getDateD()));
        CA.setText(String.valueOf(projet.getCA()));
        // Initialiser d'autres éléments graphiques avec les données du projet
    }
    @FXML
    void collaborer(ActionEvent event) {
        // Récupérer l'élément VBox sur lequel le bouton a été cliqué
        Node source = (Node) event.getSource();
        VBox projetVBox = findParentVBox(source);

        if (projetVBox != null) {
            // Récupérer le projet associé à cet élément VBox
            Projet projetACollaborer = (Projet) projetVBox.getUserData();

            if (projetACollaborer != null) {
                int idProjet = projetACollaborer.getId();

                try {
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/CollaborerProjet.fxml"));
                    Parent root = fxmlLoader.load();

                    // Récupérer le contrôleur CollaborerProjetController dans la même ligne
                    CollaborerProjetController collaborerProjetController = fxmlLoader.getController();

                    // Initialiser les données du contrôleur CollaborerProjetController
                    collaborerProjetController.initData(idProjet, sc);

                    // Créez une référence à l'instance actuelle de AfficherProjetController
                    AfficherProjetController afficherProjetController = this.afficherProjetController;
                    collaborerProjetController.setAfficherProjetController(afficherProjetController);

                    Stage stage = new Stage();
                    stage.setTitle("Collaborer avec un Projet");
                    stage.setScene(new Scene(root));
                    stage.showAndWait(); // Attendre la fermeture de la fenêtre CollaborerProjet
                } catch (IOException e) {
                    System.out.println("Erreur lors du chargement du fichier FXML de la collaboration avec le projet.");
                    e.printStackTrace();
                }
            } else {
                System.out.println("Le projet associé est null.");
            }
        } else {
            System.out.println("Impossible de récupérer le VBox parent.");
        }
    }


    // Méthode utilitaire pour trouver le VBox parent
    private VBox findParentVBox(Node node) {
        // Parcourir les parents de la source jusqu'à trouver un VBox
        while (node != null) {
            if (node instanceof VBox) {
                return (VBox) node;
            }
            node = node.getParent();
        }
        return null; // Si aucun VBox parent n'est trouvé
    }



    @FXML
    void modifier(ActionEvent event) {

    }

}