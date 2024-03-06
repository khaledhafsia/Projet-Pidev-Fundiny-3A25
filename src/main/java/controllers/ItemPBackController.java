package controllers;

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
import tn.esprit.models.Projet;
import tn.esprit.services.ServiceCollaboration;

import java.io.IOException;

public class ItemPBackController {

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