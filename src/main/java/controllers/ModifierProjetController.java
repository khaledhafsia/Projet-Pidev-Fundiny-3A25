package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import tn.esprit.models.Collaboration;
import tn.esprit.models.Projet;
import tn.esprit.services.ServiceProjet;

import java.io.IOException;
import java.text.SimpleDateFormat;

public class ModifierProjetController {

    @FXML
    private TextField nomPrT;

    @FXML
    private TextField nomPoT;

    @FXML
    private TextField CAT;
    // Déclarer une variable pour contenir le projet à modifier
    private Projet projet;

    // Méthode pour initialiser le projet à modifier
    public void initData(Projet projet) {
        this.projet = projet;

        nomPrT.setText(projet.getNomPr());
        nomPoT.setText(projet.getNomPo());
        CAT.setText(String.valueOf(projet.getCA()));
    }

    // Méthode pour valider la modification du projet
    @FXML
    void validerModification(ActionEvent event) throws IOException {
        if (projet != null) {
            // Vérifier la validité des champs en utilisant la classe InputValidator
            boolean fieldsValid = InputValidatorMp.validateFields(nomPrT, nomPoT,CAT);

            if (fieldsValid) {
                // Mettre à jour les données du projet
                projet.setNomPr(nomPrT.getText());
                projet.setNomPo(nomPoT.getText());
                try {
                    int CA = Integer.parseInt(CAT.getText());
                    projet.setCA(CA);
                    // Utilisez la valeur de CA ici
                } catch (NumberFormatException e) {
                    // Gérez l'exception ici (par exemple, affichez un message d'erreur)
                    System.err.println("Erreur : la valeur dans le champ CAT n'est pas un entier valide.");
                }

                // Appeler la méthode update pour mettre à jour le projet
                ServiceProjet sp = new ServiceProjet();
                sp.update(projet);

                // Fermer la fenêtre de modification
                ((Node) (event.getSource())).getScene().getWindow().hide();
            } else {
                System.out.println("Certains champs ne sont pas valides. Veuillez vérifier les saisies.");
            }
        } else {
            System.out.println("Aucun projet à modifier.");
        }
    }


}