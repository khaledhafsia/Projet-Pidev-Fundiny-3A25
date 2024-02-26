package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import tn.esprit.models.Collaboration;
import tn.esprit.services.ServiceCollaboration;

public class ModifierCollaborationController {

    @FXML
    private TextField nomC;

    @FXML
    private TextField typeC;

    @FXML
    private DatePicker dateC;
    private Collaboration collaboration;

    @FXML
    void validerModification(ActionEvent event) {
        if ( collaboration!= null) {
            // Mettre à jour les données du projet
            collaboration.setNomColl(nomC.getText());
            collaboration.setTypeColl(typeC.getText());


            // Appeler la méthode update pour mettre à jour le projet
            ServiceCollaboration sc = new ServiceCollaboration();
            sc.update(collaboration);



            // Fermer la fenêtre de modification
            ((Node) (event.getSource())).getScene().getWindow().hide();
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
