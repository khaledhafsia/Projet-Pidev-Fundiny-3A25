package org.example.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import org.example.Entities.Projet;
import  org.example.Services.ServiceProjet;

import java.io.IOException;

public class ModifierProjetController {

    @FXML
    private TextField nomPrT;

    @FXML
    private TextField nomPoT;

    @FXML
    private TextField CAT;
    private Projet projet;
    private AllProjectsForFunder afficherProjetController;

    public void setAfficherProjetController(AllProjectsForFunder afficherProjetController) {
        this.afficherProjetController = afficherProjetController;
    }


    public void initData(Projet projet) {
        this.projet = projet;

        nomPrT.setText(projet.getNomPr());
        //nomPoT.setText(projet.getNomPo());
        CAT.setText(String.valueOf(projet.getCA()));
    }
/*
    @FXML
    void validerModification(ActionEvent event) throws IOException {
        if (projet != null) {
            boolean fieldsValid = InputValidatorMp.validateFields(nomPrT, nomPoT, CAT);

            if (fieldsValid) {
                projet.setNomPr(nomPrT.getText());
                // projet.setNomPo(nomPoT.getText());
                try {
                    int CA = Integer.parseInt(CAT.getText());
                    projet.setCA(CA);
                } catch (NumberFormatException e) {
                    System.err.println("Erreur : la valeur dans le champ CAT n'est pas un entier valide.");
                }

                ServiceProjet sp = new ServiceProjet();
                sp.update(projet);
                if (afficherProjetController != null) {
                    afficherProjetController.initialize();
                }

                ((Node) (event.getSource())).getScene().getWindow().hide();
            } else {
                System.out.println("Certains champs ne sont pas valides. Veuillez vérifier les saisies.");
            }
        } else {
            System.out.println("Aucun projet à modifier.");
        }
    }


 */
}
