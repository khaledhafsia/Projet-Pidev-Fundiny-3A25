package org.example.Controller;
/*
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
import org.example.Entities.Collaboration;
import org.example.Entities.Projet;
import org.example.Services.ServiceCollaboration;
import  org.example.Services.ServiceProjet;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class BackController implements Initializable {

    @FXML
    private ListView<Projet> lv;

    @FXML
    private ListView<Collaboration> lvc;
    private final ServiceProjet sp = new ServiceProjet();
    private final ServiceCollaboration sc = new ServiceCollaboration();
    ArrayList<Projet> projets = sp.getAll();
    ArrayList<Collaboration> collaborations = sc.getAll();
    public void initialize(URL arg0, ResourceBundle arg1) {
        lv.setItems(FXCollections.observableArrayList(projets));
        lvc.setItems(FXCollections.observableArrayList(collaborations));
    }

    @FXML
    void Modifier(ActionEvent event) throws IOException {
        Projet projetAModifier = lv.getSelectionModel().getSelectedItem();
        Collaboration collaborationAModifier = lvc.getSelectionModel().getSelectedItem();

        if (projetAModifier != null) {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/ModifierProjet.fxml"));
                Parent root = (Parent) fxmlLoader.load();
                ModifierProjetController modifierProjetController = fxmlLoader.getController();
                modifierProjetController.initData(projetAModifier);
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
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/BackModifierC.fxml"));
                Parent root = (Parent) fxmlLoader.load();
                BackModifierCController backModifierCController = fxmlLoader.getController();
                backModifierCController.initData(collaborationAModifier);
                Stage stage = new Stage();
                stage.setTitle("Modifier Collaboration");
                stage.setScene(new Scene(root));
                stage.show();

            } catch (IOException e) {
                System.out.println("FXML file path: " + getClass().getResource("/BackModifierC.fxml"));
                e.printStackTrace();
            }
        } else {
            System.out.println("Aucun projet sélectionné pour la modification.");
        }
    }

    @FXML
    void Supprimer(MouseEvent event) {
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

}


 */