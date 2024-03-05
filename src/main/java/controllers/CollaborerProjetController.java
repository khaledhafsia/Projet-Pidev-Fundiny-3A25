
package controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import javafx.scene.layout.HBox;
import tn.esprit.models.Collaboration;
import tn.esprit.models.Projet;
import tn.esprit.services.ServiceCollaboration;
import tn.esprit.services.ServiceProjet;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;


public class CollaborerProjetController {

    @FXML
    private DatePicker dateColl;

    @FXML
    private TextField nomColl;

    @FXML
    private TextField TypeColl;
    private int idProjet;
    private Collaboration nouvelleCollaboration;
    ServiceCollaboration sc;
    private AfficherProjetController afficherProjetController;


    public void setAfficherProjetController(AfficherProjetController afficherProjetController) {
        this.afficherProjetController = afficherProjetController;
    }

    @FXML
    void validerModification(ActionEvent event) throws IOException {
        if (nomColl.getText().isEmpty() || TypeColl.getText().isEmpty() || dateColl.getValue() == null) {
            System.out.println("empty");
        } else {
            LocalDate localDate = dateColl.getValue();
            Date sqlDate = Date.valueOf(localDate);
            sc.add(new Collaboration(1, nomColl.getText(), TypeColl.getText(), sqlDate, idProjet));
            if (afficherProjetController != null) {
                afficherProjetController.affichage();
            }
            ((Node) (event.getSource())).getScene().getWindow().hide();

            // Appeler la méthode affichage() dans la classe AfficherProjetController pour mettre à jour l'affichage des collaborations
            afficherProjetController.affichage();
        }
    }

    public void initData(int idProjet, ServiceCollaboration sc) {
        this.idProjet = idProjet;
        this.sc = sc;
        System.out.println("ID du projet sélectionné : " + idProjet);
    }

    public Collaboration getNouvelleCollaboration() {
        return nouvelleCollaboration;
    }
}




