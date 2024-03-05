package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import tn.esprit.models.Collaboration;

import java.io.IOException;

public class ItemController {

    @FXML
    private HBox itemC;

    @FXML
    private Label nom;

    @FXML
    private Label type;

    @FXML
    private Label date;

    @FXML
    private Button modifier;

    @FXML
    private Button supprimer;

    private Collaboration collaboration;

    // Méthode appelée lorsque le bouton "Modifier" est cliqué
    @FXML
    void modifier(ActionEvent event) {
        // Insérez ici le code pour gérer l'action du bouton "Modifier"
        // Par exemple, ouvrir la fenêtre de modification de la collaboration
        if (collaboration != null) {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/ModifierCollaboration.fxml"));
                Parent root = fxmlLoader.load();
                ModifierCollaborationController modifierCollaborationController = fxmlLoader.getController();
                modifierCollaborationController.initData(collaboration);
                Stage stage = new Stage();
                stage.setTitle("Modifier Collaboration");
                stage.setScene(new Scene(root));
                stage.show();

            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Aucune collaboration sélectionnée.");
        }
    }

    // Méthode pour définir la collaboration associée à cet élément
    public void setCollaboration(Collaboration collaboration) {
        this.collaboration = collaboration;
    }
    private void openModifierCollaborationWindow() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/ModifierCollaboration.fxml"));
            Parent root = fxmlLoader.load();
            ModifierCollaborationController modifierCollaborationController = fxmlLoader.getController();
            modifierCollaborationController.initData(collaboration);
            Stage stage = new Stage();
            stage.setTitle("Modifier Collaboration");
            stage.setScene(new Scene(root));
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
