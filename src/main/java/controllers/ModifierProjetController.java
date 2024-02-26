package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import tn.esprit.models.Projet;
import tn.esprit.services.ServiceProjet;

import java.text.SimpleDateFormat;

public class ModifierProjetController {

    @FXML
    private TextField nomPrT;

    @FXML
    private TextField nomPoT;

    @FXML
    private TextField dateDT;

    @FXML
    private TextField CAT;
    // Déclarer une variable pour contenir le projet à modifier
    private Projet projet;

    // Méthode pour initialiser le projet à modifier
    public void initData(Projet projet) {
        this.projet = projet;

        nomPrT.setText(projet.getNomPr());
        nomPoT.setText(projet.getNomPo());
        dateDT.setText(new SimpleDateFormat("dd-MM-yyyy").format(projet.getDateD()));
        CAT.setText(String.valueOf(projet.getCA()));
    }

    // Méthode pour valider la modification du projet
    @FXML
    void validerModification(ActionEvent event) {
        if (projet != null) {
            // Mettre à jour les données du projet
            projet.setNomPr(nomPrT.getText());
            projet.setNomPo(nomPoT.getText());
            String.valueOf(projet.getDateD());
            try {
                int CA = Integer.parseInt(CAT.getText());
                CA = projet.getCA();
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
            System.out.println("Aucun projet à modifier.");
        }
    }
}