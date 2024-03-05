package Controllers;

import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import tn.esprit.models.Projet;

public class CustomCard<T> extends VBox {

    private Projet projet;

    public CustomCard(Projet projet) {
        this.projet = projet;
        configureUI();
    }

    private void configureUI() {
        // Ajouter les éléments graphiques pour représenter le projet
        Label nomLabel = new Label("Nom du projet: " + projet.getNomPr());
        Label autreInfoLabel = new Label("Autre information du projet...");
        // Ajouter d'autres éléments si nécessaire

        // Ajouter les éléments à la carte personnalisée
        getChildren().addAll(nomLabel, autreInfoLabel);
    }
}
