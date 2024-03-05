package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import tn.esprit.models.Collaboration;
import tn.esprit.services.ServiceCollaboration;
import controllers.InputValidator;

public class ModifierCollaborationController {

    @FXML
    private TextField nomC;

    @FXML
    private TextField typeC;

    @FXML
    private DatePicker dateC;
    private Collaboration collaboration;
    private AfficherProjetController afficherProjetController;

    public void setAfficherProjetController(AfficherProjetController afficherProjetController) {
        this.afficherProjetController = afficherProjetController;
    }

    @FXML
    void validerModification(ActionEvent event) {
        if (collaboration != null) {
            boolean nomValid = InputValidator.containsOnlyLetters(nomC);
            boolean typeValid = InputValidator.containsOnlyLetters(typeC);

            if (nomValid && typeValid) {
                collaboration.setNomColl(nomC.getText());
                collaboration.setTypeColl(typeC.getText());

                ServiceCollaboration sc = new ServiceCollaboration();
                sc.update(collaboration);
                if (afficherProjetController != null) {
                    afficherProjetController.affichage();
                }

                ((Node) (event.getSource())).getScene().getWindow().hide();
            } else {
                System.out.println("Certains champs ne contiennent pas que des lettres.");
            }
        } else {
            System.out.println("Aucune Collaboration à modifier.");
        }
    }


    public void initData(Collaboration collaboration) {
        this.collaboration=collaboration;
        nomC.setText(collaboration.getNomColl());
        typeC.setText(collaboration.getTypeColl());
    }
}
